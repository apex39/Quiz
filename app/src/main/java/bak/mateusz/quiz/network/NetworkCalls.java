package bak.mateusz.quiz.network;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import bak.mateusz.quiz.models.Item;
import bak.mateusz.quiz.models.QuizzesSet;
import bak.mateusz.quiz.models.quiz.Quiz;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {
    private static QuizClient client = ClientGenerator.createService();

    public static void getQuizzes() {
        Call<QuizzesSet> quizzesSetCall =
                client.getQuizzes();
        Callback<QuizzesSet> quizzesSetCallback = new Callback<QuizzesSet>() {
            @Override
            public void onResponse(Call<QuizzesSet> call, Response<QuizzesSet> response) {
                Realm realm = Realm.getDefaultInstance();

                QuizzesSet quizzesSet = response.body();
                realm.beginTransaction();
                List<Item> realmQuizzes = realm.copyToRealmOrUpdate(quizzesSet.getItems());
                realm.commitTransaction();
                EventBus.getDefault().post(realmQuizzes);
            }

            @Override
            public void onFailure(Call<QuizzesSet> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        };
        quizzesSetCall.enqueue(quizzesSetCallback);
    }

    public static void getQuiz(long quizId) {
        Call<Quiz> quizCall =
                client.getQuiz(quizId);
        Callback<Quiz> quizCallCallback = new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                Realm realm = Realm.getDefaultInstance();

                Quiz quiz = response.body();
                realm.beginTransaction();
                Quiz realmQuiz = realm.copyToRealmOrUpdate(quiz);
                realm.commitTransaction();
                EventBus.getDefault().post(realmQuiz);
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        };
        quizCall.enqueue(quizCallCallback);
    }

}
