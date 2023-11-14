package com.example.sportapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportapplication.dao.ProduitDAO;
import com.example.sportapplication.database.SportDataBase;
import com.example.sportapplication.entity.Size;
import com.example.sportapplication.entity.SizeListConverter;

import java.util.List;

public class ProductDetails extends AppCompatActivity {


    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        back = findViewById(R.id.back_button);
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String price = intent.getStringExtra("price");
            String desc = intent.getStringExtra("desc");
            String selectedSizes = intent.getStringExtra("sizes");
            List<Size> sizesList = SizeListConverter.fromString(selectedSizes);


            TextView nameTextView = findViewById(R.id.textView);
            TextView priceTextView = findViewById(R.id.price_detail);
            TextView descTextView = findViewById(R.id.detailText);


            if (name != null && price != null && desc != null ) {

                nameTextView.setText(name);
                priceTextView.setText(price);
                descTextView.setText(desc);

            }
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent back = new Intent(ProductDetails.this, MainActivity.class);
                    startActivity(back);
                    finish();
                }
            });
        }

    }

}















