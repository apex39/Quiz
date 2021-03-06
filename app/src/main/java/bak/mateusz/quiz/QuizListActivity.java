package bak.mateusz.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import bak.mateusz.quiz.models.Item;
import bak.mateusz.quiz.network.NetworkCalls;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizListActivity extends AppCompatActivity {

    @BindView(R.id.quiz_list)
    View recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private boolean mTwoPane;
    private List<Item> quizzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.quiz_detail_container) != null) {
            mTwoPane = true;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onQuizzesReceived(List<Item> realmQuizzes) {
        this.quizzes = realmQuizzes;
        setupRecyclerView((RecyclerView) recyclerView);
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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(quizzes));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                getResources().getConfiguration().orientation);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        public SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.quiz_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getCategory().getName().toUpperCase());
            holder.mContentView.setText(mValues.get(position).getContent());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putLong(QuizDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                        arguments.putString(QuizDetailFragment.ARG_ITEM_PIC_URL, holder.mItem.getMainPhoto().url);
                        QuizDetailFragment fragment = new QuizDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.quiz_detail_container, fragment)
                                .commit();
                        toolbar.setTitle(holder.mItem.getCategory().getName().toUpperCase() + ": " + holder.mItem.getTitle());
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, QuizDetailActivity.class);
                        intent.putExtra(QuizDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                        intent.putExtra(QuizDetailFragment.ARG_ITEM_PIC_URL, holder.mItem.getMainPhoto().url);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public Item mItem;
            @BindView(R.id.id)
            TextView mIdView;
            @BindView(R.id.content)
            TextView mContentView;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                mView = view;
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
