package com.example.greclamation.Room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greclamation.R;

import java.util.ArrayList;
import java.util.List;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.MyViewHolder>{


    private Context context;
    private List<Reclamation> ReclamationList;

    public ReclamationAdapter(Context context) {
        this.context = context;
        ReclamationList=new ArrayList<>();
    }

    public void addRec(Reclamation reclamation){
        ReclamationList.add(reclamation);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reclamation_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
   Reclamation reclamation=ReclamationList.get(position);
   holder.sujet.setText(reclamation.getSujet());
   holder.description.setText(reclamation.getDescription());
    }

    @Override
    public int getItemCount() {
        return ReclamationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

     private TextView sujet,description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sujet=itemView.findViewById(R.id.textViewSujet);
            description=itemView.findViewById(R.id.textViewDescription);
        }
    }
}
