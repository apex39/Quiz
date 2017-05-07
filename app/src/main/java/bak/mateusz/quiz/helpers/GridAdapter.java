package bak.mateusz.quiz.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bak.mateusz.quiz.QuizCategoriesActivity.GridListItem;
import bak.mateusz.quiz.R;

public class GridAdapter extends ArrayAdapter<GridListItem> {

    private final LayoutInflater layoutInflater;
    ArrayList<GridListItem> categories;

    public GridAdapter(Context context, ArrayList<GridListItem> categories) {
        super(context, 0, categories);
        this.categories = categories;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View itemView = convertView;

        if (itemView == null) {
            itemView = layoutInflater.inflate(R.layout.grid_view_item, parent, false);

            holder = new ViewHolder();
            holder.textView = (TextView) itemView.findViewById(android.R.id.text1);
            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }
        holder.textView.setText(categories.get(position).text);
        itemView.setBackgroundColor(categories.get(position).color);
        return itemView;
    }

    private static class ViewHolder {
        TextView textView;
    }


}