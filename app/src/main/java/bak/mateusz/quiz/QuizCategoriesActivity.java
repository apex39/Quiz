package bak.mateusz.quiz;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Random;

import bak.mateusz.quiz.helpers.GridAdapter;
import bak.mateusz.quiz.network.NetworkCalls;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizCategoriesActivity extends AppCompatActivity {

    public static final String CATEGORY_NAMES = "category_names";
    @BindView(R.id.gridView)
    GridView gridView;
    ArrayList<GridListItem> gridListItems;

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable gridListItemsParceled = Parcels.wrap(gridListItems);
        outState.putParcelable(CATEGORY_NAMES, gridListItemsParceled);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);
        ButterKnife.bind(this);
        if (savedInstanceState != null)
            gridListItems = Parcels.unwrap(savedInstanceState.getParcelable(CATEGORY_NAMES));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCategoryNames(ArrayList<String> categories) {
        setItemsColors(categories);
        gridView.setAdapter(new GridAdapter(this, gridListItems));
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
        if (gridListItems == null)
            NetworkCalls.getQuizzes();
        else
            gridView.setAdapter(new GridAdapter(this, gridListItems));
    }

    private void setItemsColors(ArrayList<String> categories) {
        int[] pastelColors;
        pastelColors = this.getResources().getIntArray(R.array.pastels);
        ArrayList<GridListItem> gridListItems;

        gridListItems = new ArrayList<>();
        for (String item : categories) {
            gridListItems.add(new GridListItem(item, getRandom(pastelColors)));
        }
        this.gridListItems = gridListItems;
    }

    @Parcel
    public static class GridListItem {
        public String text;
        public int color;

        public GridListItem() {
        }

        public GridListItem(String text, int color) {
            this.text = text;
            this.color = color;
        }
    }
}
