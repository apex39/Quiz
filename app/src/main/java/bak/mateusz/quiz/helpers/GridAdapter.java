package bak.mateusz.quiz.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import bak.mateusz.quiz.R;

public class GridAdapter extends ArrayAdapter<String> {

    private final LayoutInflater layoutInflater;
    int[] pastelColors;

    public GridAdapter(Context context, List<String> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
        pastelColors = context.getResources().getIntArray(R.array.pastels);
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
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
            holder.color = getRandom(pastelColors);
            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }
        holder.textView.setText(getItem(position));
        itemView.setBackgroundColor(holder.color);
        return itemView;
    }

    private static class ViewHolder {
        TextView textView;
        int color;
    }

}