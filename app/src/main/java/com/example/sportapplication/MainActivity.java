package com.example.sportapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sportapplication.adapter.ApdateListener;
import com.example.sportapplication.adapter.CategoryAdapter;
import com.example.sportapplication.adapter.DiscountedProductAdapter;
import com.example.sportapplication.adapter.ProductAdminAdapter;
import com.example.sportapplication.adapter.RecentlyViewedAdapter;
import com.example.sportapplication.dao.ProduitDAO;
import com.example.sportapplication.database.SportDataBase;
import com.example.sportapplication.entity.Produit;
import com.example.sportapplication.model.BestSeller;
import com.example.sportapplication.model.Category;
import com.example.sportapplication.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ApdateListener {

    RecyclerView discountRecyclerView,categoryRecyclerView,recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<BestSeller> discountedProductsList;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;
    ImageView allCategory,imageView;




    private SportDataBase sportDataBase;
    private ProduitDAO produitDAO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sportDataBase = SportDataBase.getDatabase(this);
        produitDAO=sportDataBase.produitDAO();

      // discountRecyclerView = findViewById(R.id.productRecycler);
        categoryRecyclerView=findViewById(R.id.categoryRecycler);
        allCategory=findViewById(R.id.AllCategoryImage);


        recentlyViewedAdapter = new RecentlyViewedAdapter(this,this);
        recentlyViewedRecycler = findViewById(R.id.recently_item);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);




        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AllCategory.class);
                startActivity(i);
            }
        });


        // adding data to model
       /* discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new BestSeller("AirForce 1","Sneakers","250 DT","2.4",R.drawable.shoes1));
        discountedProductsList.add(new BestSeller("AirForce 2","Sneakers","250 DT","5.4",R.drawable.shoes3));*/



        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, R.drawable.homme));
        categoryList.add(new Category(2, R.drawable.femme));
        categoryList.add(new Category(3, R.drawable.enfant));


        // adding data to model
       /* recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("AirForce 1","180 DT",R.drawable.shoes1,"4.5"));
        recentlyViewedList.add(new RecentlyViewed("AirForce 2","180 DT", R.drawable.shoes1,"4.3"));
        recentlyViewedList.add(new RecentlyViewed("AirForce 2","250 DT", R.drawable.sho2,"4.3"));*/


       // setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);
       // setRecentlyViewedRecycler(recentlyViewedList);

    }

   /* private  void setDiscountedRecycler(List<BestSeller> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }*/
    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,  categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
     }
   /* private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this, recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }*/

    private void fetchData() {
        recentlyViewedAdapter.clearData();
        List<Produit> produitList = produitDAO.getAllProduit();
        for (int i = 0; i < produitList.size(); i++) {
            Produit produit = produitList.get(i);
            recentlyViewedAdapter.aadProduit(produit);
        }
    }

    @Override
    public void OnUpdate(Produit produit) {

    }

    @Override
    public void OnDelete(int id, int pos) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}