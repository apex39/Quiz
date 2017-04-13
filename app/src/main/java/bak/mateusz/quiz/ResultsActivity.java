package bak.mateusz.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eralp.circleprogressview.CircleProgressView;
import com.eralp.circleprogressview.ProgressAnimationListener;

import bak.mateusz.quiz.models.quiz.Quiz;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import static bak.mateusz.quiz.QuizDetailFragment.ARG_ITEM_ID;

public class ResultsActivity extends AppCompatActivity {
    @BindView(R.id.result)
    TextView resultTextView;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.againButton)
    Button againButton;
    @BindView(R.id.backToMenuButton)
    Button backToMenuButton;
    @BindView(R.id.circle_progress_view)
    CircleProgressView circle;
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Realm realm = Realm.getDefaultInstance();
        this.quiz = realm.where(Quiz.class).equalTo("id", getIntent().getExtras().getLong(ARG_ITEM_ID)).findFirst();
        resultTextView.setText(quiz.getRate());
        circle.setTextEnabled(true);
        circle.setInterpolator(new AccelerateDecelerateInterpolator());
        circle.setStartAngle(45);
        circle.setProgressWithAnimation(quiz.getResultPercentage(), 2500);
        circle.addAnimationListener(new ProgressAnimationListener() {
            @Override
            public void onValueChanged(float value) {

            }

            @Override
            public void onAnimationEnd() {
                TransitionManager.beginDelayedTransition(layout);
                resultTextView.setVisibility(View.VISIBLE);
                againButton.setVisibility(View.VISIBLE);
                backToMenuButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick(R.id.againButton)
    public void startQuizAgain(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizDetailFragment.ARG_ITEM_ID,
                quiz.getId());
        startActivity(intent);
    }

    @OnClick(R.id.backToMenuButton)
    public void openQuizListActivity(View view) {
        Intent intent = new Intent(this, QuizListActivity.class);
        startActivity(intent);
    }
}
