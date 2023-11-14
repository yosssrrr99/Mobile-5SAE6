package com.example.sportapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.sportapplication.adapter.ApdateListener;
import com.example.sportapplication.adapter.ProductAdminAdapter;
import com.example.sportapplication.dao.ProduitDAO;
import com.example.sportapplication.database.SportDataBase;
import com.example.sportapplication.entity.Produit;
import com.example.sportapplication.entity.Size;
import com.example.sportapplication.entity.SizeListConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class ProduitAdmin extends AppCompatActivity implements ApdateListener {

    EditText namepro,typepro,descpro,pricepro;
    private static final int PICK_IMAGE_REQUEST = 1;
    Button insertbtn, selectImageButton;
    ImageView imageView, selectedImageView;
    private Uri selectedImageUri;
    private SportDataBase sportDataBase;
    private ProduitDAO produitDAO;
    ImageView allProduct;
    ProductAdminAdapter productAdminAdapter;
    RecyclerView discountRecyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_admin);



        sportDataBase = SportDataBase.getDatabase(this);
        produitDAO=sportDataBase.produitDAO();

        namepro=findViewById(R.id.name_product);
        typepro=findViewById(R.id.type_product);
        descpro=findViewById(R.id.desc_product);
        pricepro=findViewById(R.id.price_prodcut);
        insertbtn=findViewById(R.id.insert_product);
        allProduct=findViewById(R.id.imageView7);


     
        productAdminAdapter = new ProductAdminAdapter(this,this);

        discountRecyclerView = findViewById(R.id.allprodRecycle);
        discountRecyclerView.setAdapter(productAdminAdapter);
        discountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        allProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProduitAdmin.this, ListProductAdmin.class);
                startActivity(i);
            }
        });


        MultiAutoCompleteTextView sizeMultiSpinner = findViewById(R.id.size_multi_spinner);

        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Size.values());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeMultiSpinner.setAdapter(sizeAdapter);
        sizeMultiSpinner.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        insertbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String selectedSizes = sizeMultiSpinner.getText().toString();
                Log.d("SizeList1", selectedSizes);
                List<Size> selectedSizeList = SizeListConverter.fromString(selectedSizes);
                for(Size a:selectedSizeList){
                    Log.d("SizeList", ""+a);
                }

                Produit produit = new Produit();
                produit.setName(namepro.getText().toString());
                produit.setDes(descpro.getText().toString());
                produit.setPrice(pricepro.getText().toString());
                produit.setType(typepro.getText().toString());
                produit.setSizes(selectedSizeList);
                // Set the image URI to the produit
              /*  try {
                    byte[] imageData = getBytesFromUri(selectedImageUri);
                    produit.setImageData(imageData);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }*/
                productAdminAdapter.aadProduit(produit);

                    produitDAO.insert(produit);

                        Toast.makeText(ProduitAdmin.this, "Inserted", Toast.LENGTH_SHORT).show();



            }
        });


    }

    private void fetchData() {
        productAdminAdapter.clearData();
        List<Produit> produitList = produitDAO.getAllProduit();
        for (int i = 0; i < produitList.size(); i++) {
            Produit produit = produitList.get(i);
            productAdminAdapter.aadProduit(produit);
        }
    }

    @Override
    public void OnUpdate(Produit produit) {
        Intent intent = new Intent(ProduitAdmin.this, UpdateProductAdmin.class);
        intent.putExtra("model",produit);
        startActivity(intent);

    }

    @Override
    public void OnDelete(int id, int pos) {
         produitDAO.delete(id);
         productAdminAdapter.removeProduit(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }


}