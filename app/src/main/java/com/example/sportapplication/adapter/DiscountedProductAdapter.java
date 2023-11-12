package com.example.sportapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sportapplication.ProductDetails;
import com.example.sportapplication.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportapplication.model.BestSeller;

import java.util.List;

public class DiscountedProductAdapter extends RecyclerView.Adapter<DiscountedProductAdapter.DiscountedProductViewHolder> {
    Context context;
    List<BestSeller> bestSellers;

    public DiscountedProductAdapter(Context context, List<BestSeller> bestSellers) {
        this.context = context;
        this.bestSellers = bestSellers;
    }

    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bestseller_row_items, parent, false);
        return new DiscountedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {
        holder.discountImageView.setImageResource(bestSellers.get(position).getImageUrl());
        holder.name.setText(bestSellers.get(position).getName());
        holder.type.setText(bestSellers.get(position).getType());
        holder.price.setText(bestSellers.get(position).getPrice());
        holder.rating.setText(bestSellers.get(position).getRating());
        holder.itemView.setOnClickListener((view)-> {

            Intent i = new Intent(context, ProductDetails.class);
            i.putExtra("name",bestSellers.get(position).getName());
            i.putExtra("rating",bestSellers.get(position).getRating());
            i.putExtra("price",bestSellers.get(position).getPrice());
            context.startActivity(i);


        });


    }

    @Override
    public int getItemCount() {
        return bestSellers.size();

    }

    public static class DiscountedProductViewHolder extends  RecyclerView.ViewHolder {

        ImageView discountImageView;
        TextView price,rating,name,type;
        LinearLayout linearLayout;

        public DiscountedProductViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout=itemView.findViewById(R.id.best_seller);
            discountImageView = itemView.findViewById(R.id.shoeImageView);
            price=itemView.findViewById(R.id.price);
            type=itemView.findViewById(R.id.type);
            name=itemView.findViewById(R.id.name);
            rating=itemView.findViewById(R.id.rating);


        }


    }

}
