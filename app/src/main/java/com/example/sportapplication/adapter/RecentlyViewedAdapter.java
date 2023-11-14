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

import com.example.sportapplication.entity.Produit;
import com.example.sportapplication.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedViewHolder> {
    private Context context;
    private List<Produit> produitList;

    private ApdateListener apdateListener;
    public RecentlyViewedAdapter(Context context,ApdateListener listener ) {
        this.context = context;
        produitList = new ArrayList<>();
        this.apdateListener=listener;
    }
    public void aadProduit(Produit produit){
        produitList.add(produit);
        notifyDataSetChanged();
    }

    public void removeProduit(int position){
        produitList.remove(position);
        notifyDataSetChanged();
    }
    public void clearData(){
        produitList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecentlyViewedAdapter.RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_row_items, parent, false);
        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedAdapter.RecentlyViewedViewHolder holder, int position) {

        Produit p = produitList.get(position);
        holder.chaussname.setText(p.getName());
        holder.price.setText(p.getPrice());
        holder.itemView.setOnClickListener((view)-> {

            Intent i = new Intent(context, ProductDetails.class);
            i.putExtra("name",produitList.get(position).getName());
            i.putExtra("price",produitList.get(position).getPrice());
            i.putExtra("desc",produitList.get(position).getDes());
            i.putExtra("sizes",produitList.get(position).getSizes().toString());
            context.startActivity(i);


        });




    }

    @Override
    public int getItemCount() {
        return produitList.size();
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
