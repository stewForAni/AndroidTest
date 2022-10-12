package com.stew.androidtest.testforlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stew.androidtest.R;

import java.util.ArrayList;

/**
 * Created by stew on 3/31/22.
 * mail: stewforani@gmail.com
 */
public class MyAdapter extends BaseAdapter {

    private static final String TAG = MyAdapter.class.getSimpleName();
    ArrayList<String> my_list;
    Context context;

    public void setData(ArrayList<String> list, Context context) {
        this.my_list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return my_list == null ? 0 : my_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder viewHolder;
        View itemView;

        if (convertView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
            viewHolder = new MyViewHolder();
            viewHolder.textView = itemView.findViewById(R.id.text);
            itemView.setTag(viewHolder);

        } else {
            itemView = convertView;
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(my_list.get(position));

        return itemView;
    }

    class MyViewHolder {
        TextView textView;
    }

}
