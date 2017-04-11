package bak.mateusz.quiz.network;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import bak.mateusz.quiz.models.Item;
import bak.mateusz.quiz.models.QuizzesSet;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by timo on 11.04.17.
 */

public class NetworkCalls {
    private QuizClient client;

    public void getQuizzes() {

        client = ClientGenerator.createService();

        Call<QuizzesSet> quizzesSetCall =
                client.getQuizzes();
        Callback<QuizzesSet> loginCallCallback = new Callback<QuizzesSet>() {
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

            }
        };
        quizzesSetCall.enqueue(loginCallCallback);
    }

}
