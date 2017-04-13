package bak.mateusz.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bak.mateusz.quiz.models.Item;
import bak.mateusz.quiz.models.quiz.Quiz;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A fragment representing a single Quiz detail screen.
 * This fragment is either contained in a {@link QuizListActivity}
 * in two-pane mode (on tablets) or a {@link QuizDetailActivity}
 * on handsets.
 */
public class QuizDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_PIC_URL = "pic_url";
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.result)
    TextView result;
    private Item mItem;
    private Quiz quiz;
    private Unbinder bind;
    private CollapsingToolbarLayout appBarLayout;

    public QuizDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Activity activity = this.getActivity();
            appBarLayout =
                    (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Realm realm = Realm.getDefaultInstance();
            mItem = realm.where(Item.class).equalTo("id", getArguments().getLong(ARG_ITEM_ID)).findFirst();
            quiz = realm.where(Quiz.class).equalTo("id", getArguments().getLong(ARG_ITEM_ID)).findFirst();
        }
        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.getCategory().getName().toUpperCase());
        } else {
            message.setText(mItem.getContent());
        }
        if (quiz != null) {
            if (quiz.getCurrentQuestion() == 0) {
                message.setText("Twój wynik: ");
                result.setText(Integer.toString(quiz.getResultPercentage()) + "%");
            } else if (quiz.getCurrentQuestion() > 0) {
                message.setText("Quiz rozwiązany w : ");
                Integer percentage = Math.round((float) quiz.getCurrentQuestion() / (float) quiz.getQuestions().size() * 100);
                result.setText(Integer.toString(percentage) + "%");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quiz_detail, container, false);

        bind = ButterKnife.bind(this, rootView);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.quiz_detail)).setText(mItem.getContent());

        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
