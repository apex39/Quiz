package bak.mateusz.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import bak.mateusz.quiz.models.quiz.Quiz;
import bak.mateusz.quiz.network.NetworkCalls;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a single Quiz detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link QuizListActivity}.
 */
public class QuizDetailActivity extends AppCompatActivity {

    @BindView(R.id.quiz_image)
    ImageView quizImage;
    String picUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        picUrl = getIntent().getStringExtra(QuizDetailFragment.ARG_ITEM_PIC_URL);
        Glide.with(this).load(picUrl).centerCrop().crossFade()
                .into(quizImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkCalls.getQuiz(getIntent().getLongExtra(QuizDetailFragment.ARG_ITEM_ID, 0));
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putLong(QuizDetailFragment.ARG_ITEM_ID,
                    getIntent().getLongExtra(QuizDetailFragment.ARG_ITEM_ID, 0));
            QuizDetailFragment fragment = new QuizDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.quiz_detail_container, fragment)
                    .commit();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onQuizReceived(Quiz quiz) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizDetailFragment.ARG_ITEM_ID,
                getIntent().getLongExtra(QuizDetailFragment.ARG_ITEM_ID, 0));

        startActivity(intent);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }
}
