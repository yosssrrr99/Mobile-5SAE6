package com.example.sportapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sportapplication.adapter.ProductAdminAdapter;
import com.example.sportapplication.dao.ProduitDAO;
import com.example.sportapplication.database.SportDataBase;
import com.example.sportapplication.entity.Produit;

import java.util.List;

public class ListProductAdmin extends AppCompatActivity {

    ProductAdminAdapter productAdminAdapter;
    RecyclerView discountRecyclerView;

    private SportDataBase sportDataBase;
    private ProduitDAO produitDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_admin);

       /* sportDataBase = SportDataBase.getDatabase(this);
        produitDAO = sportDataBase.produitDAO();


        discountRecyclerView = findViewById(R.id.allproductRecycle);
        discountRecyclerView.setAdapter(productAdminAdapter);
        discountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fetchData();


    }
    private void fetchData() {
        List<Produit> produitList = produitDAO.getAllProduit();
        for (int i = 0; i < produitList.size(); i++) {
            Produit produit = produitList.get(i);
            productAdminAdapter.aadProduit(produit);
        }
    }*/

    }
}
