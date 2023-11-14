package com.example.greclamation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greclamation.Room.Reclamation;
import com.example.greclamation.Room.ReclamationAdapter;
import com.example.greclamation.Room.ReclamationDAO;
import com.example.greclamation.Room.ReclamationDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText sujet,description;
    Button insertBtn;
    RecyclerView myrecycler;

    private ReclamationDatabase reclamationDatabase;
    private ReclamationDAO reclamationDAO;

    private ReclamationAdapter reclamationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sujet=findViewById(R.id.editTextSujet);
        description=findViewById(R.id.editTextDescription);
        insertBtn=findViewById(R.id.buttonSubmit);

        myrecycler=findViewById(R.id.reclamationRecycler);

        reclamationAdapter=new ReclamationAdapter(this);
        myrecycler.setAdapter(reclamationAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));
        reclamationDAO=reclamationDatabase.getDao();

        fetchdata();

        reclamationDatabase=ReclamationDatabase.getInstance(this);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sujets = sujet.getText().toString();
                String descriptions=description.getText().toString();
                Reclamation recs =new Reclamation(0,sujets,descriptions);
                sujet.setText("");
                description.setText("");

                Reclamation newReclamation = new Reclamation();
                newReclamation.setSujet(sujets);
                newReclamation.setDescription(descriptions);
                reclamationDAO.InsertReclamation(newReclamation);





                Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();



            }
        });



    }
    private void fetchdata(){
        List<Reclamation>reclamationList=reclamationDAO.getAllReclamation();
        for (int i=0;i< reclamationList.size();i++){
            Reclamation recs=reclamationList.get(i);
            reclamationAdapter.addRec(recs);
        }
    }
}