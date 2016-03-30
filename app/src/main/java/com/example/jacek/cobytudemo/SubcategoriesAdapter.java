package com.example.jacek.cobytudemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SubcategoriesAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private boolean isGrid;
    private String[] list;

    public SubcategoriesAdapter(Context context, boolean isGrid, String[] list) {
        layoutInflater = LayoutInflater.from(context);
        this.isGrid = isGrid;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;
        viewHolder = new ViewHolder();
        if (view == null) {
            if (isGrid) {
                view = layoutInflater.inflate(R.layout.subclass_grid_item, parent, false);
                viewHolder.textView = (TextView) view.findViewById(R.id.tv_subclass_item_grid);
            } else {
                view = layoutInflater.inflate(R.layout.subclass_list_item, parent, false);
                viewHolder.textView = (TextView) view.findViewById(R.id.tv_subclass_item_list);
            }




            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(list[position]);

        return view;
    }

    static class ViewHolder {
        TextView textView;

    }
}