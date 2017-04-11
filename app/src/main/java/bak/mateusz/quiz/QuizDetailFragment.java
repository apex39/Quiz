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

    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuizDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Realm realm = Realm.getDefaultInstance();
            mItem = realm.where(Item.class).equalTo("id", getArguments().getLong(ARG_ITEM_ID)).findFirst();

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getCategory().getName().toUpperCase());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quiz_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.quiz_detail)).setText(mItem.getContent());
        }

        return rootView;
    }
}
