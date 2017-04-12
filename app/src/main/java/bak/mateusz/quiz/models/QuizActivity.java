package bak.mateusz.quiz.models;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import bak.mateusz.quiz.R;
import bak.mateusz.quiz.models.quiz.Quiz;
import io.realm.Realm;

import static bak.mateusz.quiz.QuizDetailFragment.ARG_ITEM_ID;

public class QuizActivity extends AppCompatActivity {

    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadQuiz();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadQuiz() {
        Realm realm = Realm.getDefaultInstance();
        this.quiz = realm.where(Quiz.class).equalTo("id", getIntent().getExtras().getLong(ARG_ITEM_ID)).findFirst();

    }
}
