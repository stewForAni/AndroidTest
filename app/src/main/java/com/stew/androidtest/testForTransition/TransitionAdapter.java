package com.stew.androidtest.testForTransition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stew.androidtest.R;

import java.util.List;

/**
 * Created by stew on 2023/8/30.
 * mail: stewforani@gmail.com
 */
public class TransitionAdapter extends RecyclerView.Adapter<TransitionAdapter.MyVH> {

   List<String> data;
   Context context;
   ItemClickListener listener;

   public TransitionAdapter(Context context) {
      this.context = context;
   }

   public void setData(List<String> list) {
      this.data = list;
   }

   public void setListener(ItemClickListener listener) {
      this.listener = listener;
   }

   @NonNull
   @Override
   public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.item_transition, parent, false);
      MyVH vh = new MyVH(view);
      vh.itemView.setOnClickListener(view1 -> listener.ItemClick(view,vh.getLayoutPosition()));
      return vh;
   }

   @Override
   public void onBindViewHolder(@NonNull MyVH holder, int position) {
      holder.tv.setText(data.get(position));
   }

   @Override
   public int getItemCount() {
      return data == null ? 0 : data.size();
   }

   class MyVH extends RecyclerView.ViewHolder{
      TextView tv;

      public MyVH(@NonNull View itemView) {
         super(itemView);
         tv = itemView.findViewById(R.id.avatartitle);
      }
   }
}
