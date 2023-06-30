package com.stew.androidtest.testforrv;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stew.androidtest.R;

import java.util.List;

/**
 * Created by stew on 2023/6/29.
 * mail: stewforani@gmail.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVH> {
    List<Integer> data;
    Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Integer> list) {
        this.data = list;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
        MyVH vh = new MyVH(view);
        view.setOnClickListener(view1 -> {
            Log.d("MyAdapter", "onclick: "+vh.getLayoutPosition());
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        holder.tv.setText(data.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MyVH extends RecyclerView.ViewHolder {
        TextView tv;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tx_rv);
        }
    }

//    @Override
//    public void onClick(View view) {
//
//    }
}
