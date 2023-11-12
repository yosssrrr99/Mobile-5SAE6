package com.example.sportapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportapplication.ProductDetails;
import com.example.sportapplication.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportapplication.model.RecentlyViewed;

import java.util.List;

public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedViewHolder> {
    Context context;
    List<RecentlyViewed> recentlyViewedList;

    public RecentlyViewedAdapter(Context context, List<RecentlyViewed> recentlyViewedList) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
    }

    @NonNull
    @Override
    public RecentlyViewedAdapter.RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_row_items, parent, false);
        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedAdapter.RecentlyViewedViewHolder holder, int position) {

         holder.categoryImage.setImageResource(recentlyViewedList.get(position).getImageUrl());
         holder.price.setText(recentlyViewedList.get(position).getPrice());
         holder.chaussname.setText(recentlyViewedList.get(position).getName());
        holder.itemView.setOnClickListener((view)-> {

            Intent i = new Intent(context, ProductDetails.class);
            i.putExtra("name",recentlyViewedList.get(position).getName());
            i.putExtra("price",recentlyViewedList.get(position).getPrice());
            context.startActivity(i);


        });

         Log.d("RecyclerView", "Item count: " + recentlyViewedList.size());

    }

    @Override
    public int getItemCount() {
        return recentlyViewedList.size();
    }

    public static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        ConstraintLayout bgC;
        TextView price,chaussname;


        public RecentlyViewedViewHolder(@NonNull View itemView) {

            super(itemView);


            categoryImage = itemView.findViewById(R.id.imageView);
            price=itemView.findViewById(R.id.type);
            chaussname=itemView.findViewById(R.id.chauss_name);

        }
    }
}
