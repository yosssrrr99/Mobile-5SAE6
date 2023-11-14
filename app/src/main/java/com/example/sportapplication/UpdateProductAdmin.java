package com.example.sportapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.example.sportapplication.adapter.ProductAdminAdapter;
import com.example.sportapplication.dao.ProduitDAO;
import com.example.sportapplication.database.SportDataBase;
import com.example.sportapplication.entity.Produit;
import com.example.sportapplication.entity.Size;
import com.example.sportapplication.entity.SizeListConverter;

import java.security.PrivateKey;
import java.util.List;

public class UpdateProductAdmin extends AppCompatActivity {



    EditText namepro,typepro,descpro,pricepro;
    private static final int PICK_IMAGE_REQUEST = 1;
    Button update;
    private Produit produit;
    private SportDataBase sportDataBase;
    private ProduitDAO produitDAO;

    ProductAdminAdapter productAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product_admin);

        sportDataBase = SportDataBase.getDatabase(this);
        produitDAO=sportDataBase.produitDAO();

        namepro=findViewById(R.id.name_product);
        typepro=findViewById(R.id.type_product);
        descpro=findViewById(R.id.desc_product);
        pricepro=findViewById(R.id.price_prodcut);
        update=findViewById(R.id.update);



        MultiAutoCompleteTextView sizeMultiSpinner = findViewById(R.id.size_multi_spinner);

        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Size.values());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeMultiSpinner.setAdapter(sizeAdapter);
        sizeMultiSpinner.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        produit=(Produit) getIntent().getSerializableExtra("model");







        namepro.setText(produit.getName());
        descpro.setText(produit.getDes());
        pricepro.setText(produit.getPrice());
        typepro.setText(produit.getType());
        // Convertir la liste de tailles en une chaîne formatée pour l'affichage
        StringBuilder sizesText = new StringBuilder();
        List<Size> sizes = produit.getSizes();
        for (Size size : sizes) {
            sizesText.append(size.toString()).append(", "); // Ajouter chaque taille suivie d'une virgule
        }

        // Supprimer la virgule finale et définir le texte dans le sizeMultiSpinner
        if (sizesText.length() > 2) {
            sizesText.setLength(sizesText.length() - 2); // Supprimer la virgule et l'espace après la dernière taille
        }

        Log.d("SizesText", sizesText.toString());
        sizeMultiSpinner.setText(sizesText.toString());



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedSizes = sizeMultiSpinner.getText().toString();
                Log.d("SizeList1", selectedSizes);
                List<Size> selectedSizeList = SizeListConverter.fromString(selectedSizes);
                for(Size a:selectedSizeList){
                    Log.d("SizeList", ""+a);
                }
              Produit  produitModel=new Produit(produit.getId(),produit.getName(),produit.getPrice(),produit.getDes(),produit.getType(),selectedSizeList);
              produitDAO.update(produitModel);
              finish();


            }
        });







    }
}