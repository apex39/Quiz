package bak.mateusz.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import bak.mateusz.quiz.models.quiz.Answer;
import bak.mateusz.quiz.models.quiz.Question;
import bak.mateusz.quiz.models.quiz.Quiz;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

import static bak.mateusz.quiz.QuizDetailFragment.ARG_ITEM_ID;

public class QuizActivity extends AppCompatActivity {

    @BindView(R.id.question_text)
    TextView questionTextView;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Quiz quiz;
    private Integer questionsNumber;
    private Integer correctAnswersNumber = 0;
    private RealmList<Question> questions;
    private int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        loadQuiz();
        if (quiz.getCurrentQuestion() > 0) {
            setQuestion(questions.get(quiz.getCurrentQuestion()));
            correctAnswersNumber = quiz.getCorrectAnswers();
        } else
            setQuestion(questions.first());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });
    }

    private void loadQuiz() {
        Realm realm = Realm.getDefaultInstance();
        this.quiz = realm.where(Quiz.class).equalTo("id", getIntent().getExtras().getLong(ARG_ITEM_ID)).findFirst();
        this.questionsNumber = quiz.getQuestions().size();
        this.questions = quiz.getQuestions();
    }

    private void setQuestion(Question question) {
        RealmList<Answer> answers = question.getAnswers();
        Integer testProgress = Math.round((float) question.getOrder() / (float) questionsNumber * 100);
        progressBar.setProgress(testProgress);
        currentQuestion = question.getOrder();
        questionTextView.setText(question.getText());

        if (radioGroup.getChildCount() > 0) radioGroup.removeAllViewsInLayout();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                showFloatingActionButton(fab);

            }
        });
        for (short i = 0; i < answers.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answers.get(i).getText());
            radioButton.setPadding(10, 10, 0, 0);
            radioButton.setLayoutParams(
                    new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,
                            RadioGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioButton.setTag(R.id.correct_answer, answers.get(i).getIsCorrect());

            radioGroup.addView(radioButton);
        }
        hideFloatingActionButton(fab);
    }

    private void nextQuestion() {
        RadioButton checkedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        Realm realm = Realm.getDefaultInstance();
        if ((Boolean) checkedRadioButton.getTag(R.id.correct_answer))
            correctAnswersNumber++;
        if (currentQuestion < questionsNumber) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    quiz.setCorrectAnswers(correctAnswersNumber);
                    quiz.setCurrentQuestion(currentQuestion);
                }
            });
            setQuestion(questions.get(currentQuestion));
        }
        else {

            Intent intent = new Intent(this, ResultsActivity.class);
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    quiz.setCorrectAnswers(correctAnswersNumber);
                    quiz.setCurrentQuestion(0);
                }
            });

            intent.putExtra(ARG_ITEM_ID, quiz.getId());
            startActivity(intent);
            finish();
        }

    }

    private void hideFloatingActionButton(FloatingActionButton fab) {
        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        FloatingActionButton.Behavior behavior =
                (FloatingActionButton.Behavior) params.getBehavior();

        if (behavior != null) {
            behavior.setAutoHideEnabled(false);
        }

        fab.hide();
    }

    private void showFloatingActionButton(FloatingActionButton fab) {
        fab.show();
        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        FloatingActionButton.Behavior behavior =
                (FloatingActionButton.Behavior) params.getBehavior();

        if (behavior != null) {
            behavior.setAutoHideEnabled(true);
        }
    }
}
