package bak.mateusz.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import bak.mateusz.quiz.helpers.GridAdapter;
import bak.mateusz.quiz.network.NetworkCalls;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizCategoriesActivity extends AppCompatActivity {

    @BindView(R.id.gridView)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);
        ButterKnife.bind(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCategoryNames(List<String> categories) {
        gridView.setAdapter(new GridAdapter(this, categories));
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
        NetworkCalls.getQuizzes();

    }

}
