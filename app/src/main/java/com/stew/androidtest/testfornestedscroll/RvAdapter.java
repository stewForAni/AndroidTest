package com.stew.androidtest.testfornestedscroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stew.androidtest.R;

import java.util.List;

/**
 * Created by stew on 8/14/22.
 * mail: stewforani@gmail.com
 */
public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> data;

    public void setData(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyVH) holder).textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MyVH extends RecyclerView.ViewHolder {

        TextView textView;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tx_rv);
        }
    }
}
