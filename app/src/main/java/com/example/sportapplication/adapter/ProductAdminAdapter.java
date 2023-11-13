package com.example.sportapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportapplication.R;
import com.example.sportapplication.entity.Produit;

import java.util.ArrayList;
import java.util.List;

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.MyViewHolder>  {


    private Context context;
     private List<Produit> produitList;

     private ApdateListener apdateListener;

    public ProductAdminAdapter(Context context,ApdateListener listener ) {
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


    @NonNull
    @Override
    public ProductAdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bestseller_row_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdminAdapter.MyViewHolder holder, int position) {
         Produit p = produitList.get(position);
         holder.name.setText(p.getName());
        holder.type.setText(p.getType());
        holder.price.setText(p.getPrice());

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apdateListener.OnUpdate(p.getId(),position);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apdateListener.OnDelete(p.getId(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return produitList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView discountImageView,update,delete;
        TextView price,rating,name,type;
        LinearLayout linearLayout;
         public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             linearLayout=itemView.findViewById(R.id.best_seller);
             discountImageView = itemView.findViewById(R.id.shoeImageView);
             price=itemView.findViewById(R.id.price);
             type=itemView.findViewById(R.id.type);
             name=itemView.findViewById(R.id.name);
             rating=itemView.findViewById(R.id.rating);
             update=itemView.findViewById(R.id.update);
             delete=itemView.findViewById(R.id.delete);
        }
    }
}
