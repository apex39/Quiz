package bak.mateusz.quiz.network;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import bak.mateusz.quiz.models.Item;
import bak.mateusz.quiz.models.QuizzesSet;
import bak.mateusz.quiz.models.quiz.Quiz;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {
    private static QuizClient client = ClientGenerator.createService();
    private static Realm realm = Realm.getDefaultInstance();

    public static void getQuizzes() {
        RealmResults<Item> realmQuizzes = realm.where(Item.class).findAll();
        if (realmQuizzes.size() > 0) {
            ArrayList<String> categoryNames = getCategoryNames(realmQuizzes);
            EventBus.getDefault().post(categoryNames);
        } else {
        Call<QuizzesSet> quizzesSetCall =
                client.getQuizzes();
        Callback<QuizzesSet> quizzesSetCallback = new Callback<QuizzesSet>() {
            @Override
            public void onResponse(Call<QuizzesSet> call, Response<QuizzesSet> response) {
                QuizzesSet quizzesSet = response.body();
                realm.beginTransaction();
                List<Item> realmQuizzes = realm.copyToRealmOrUpdate(quizzesSet.getItems());
                realm.commitTransaction();
                ArrayList<String> categoryNames = getCategoryNames(realmQuizzes);
                EventBus.getDefault().post(categoryNames);
            }

            @Override
            public void onFailure(Call<QuizzesSet> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        };
        quizzesSetCall.enqueue(quizzesSetCallback);
    }
    }

    public static void getQuiz(long quizId) {
        Quiz quiz = realm.where(Quiz.class).equalTo("id", quizId).findFirst();
        if (quiz != null) {
            EventBus.getDefault().post(quiz);
        } else {
            Call<Quiz> quizCall =
                    client.getQuiz(quizId);
            Callback<Quiz> quizCallCallback = new Callback<Quiz>() {
                @Override
                public void onResponse(Call<Quiz> call, Response<Quiz> response) {
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

    private static ArrayList<String> getCategoryNames(List<Item> realmQuizzes) {
        ArrayList<String> categories = new ArrayList<>();
        for (Item quiz : realmQuizzes) {
            if (!categories.contains(quiz.getCategory().getName().toUpperCase()))
                categories.add(quiz.getCategory().getName().toUpperCase());
        }
        return categories;
    }
}
