package bak.mateusz.quiz;

import android.app.Application;

import bak.mateusz.quiz.network.NetworkCalls;
import io.realm.Realm;

/**
 * Created by timo on 11.04.17.
 */

public class QuizApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        NetworkCalls.getQuizzes();
    }
}
