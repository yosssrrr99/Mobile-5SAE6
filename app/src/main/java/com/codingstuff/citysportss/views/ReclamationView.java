package com.codingstuff.citysportss.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codingstuff.citysportss.R;
import com.codingstuff.citysportss.dao.ReclamationDAO;
import com.codingstuff.citysportss.database.CartDatabase;
import com.codingstuff.citysportss.utils.model.Reclamation;
import com.google.android.material.textfield.TextInputEditText;

public class ReclamationView extends AppCompatActivity {

    private TextInputEditText editTextSubject;
    private TextInputEditText editTextDescription;
    private Button submitReclamationButton;
    private ReclamationDAO reclamationDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation_view);

        editTextSubject = findViewById(R.id.editTextSubject);
        editTextDescription = findViewById(R.id.editTextDescription);
        submitReclamationButton = findViewById(R.id.submitReclamationButton);

        CartDatabase cartDatabase = CartDatabase.getInstance(this);
        reclamationDAO = cartDatabase.reclamationDAO();

        submitReclamationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the submission logic here
                submitReclamation();
            }
        });
    }
    private void submitReclamation() {
        String subject = editTextSubject.getText().toString();
        String description = editTextDescription.getText().toString();

        if (subject.isEmpty() || description.isEmpty()) {
            // Handle validation failure
            return;
        }

        // Insert the reclamation into the database using AsyncTask
        new InsertReclamationTask(reclamationDAO).execute(new Reclamation(subject, description));
    }
    private static class InsertReclamationTask extends AsyncTask<Reclamation, Void, Void> {
        private ReclamationDAO reclamationDAO;

        InsertReclamationTask(ReclamationDAO reclamationDAO) {
            this.reclamationDAO = reclamationDAO;
        }


        @Override
        protected Void doInBackground(Reclamation... reclamations) {
            return null;
        }
    }
}