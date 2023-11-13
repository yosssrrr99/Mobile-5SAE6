package com.example.projectcitysport;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.projectcitysport.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    ArrayList<Event> Events ;
    private View view;
    Event evento;

    DBHelper dbHelper ;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        dbHelper = new DBHelper(binding.getRoot().getContext()); // Initialize the database helper
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Get a writable database

        //dbHelper.insertData();

        Events = (ArrayList<Event>) dbHelper.getAllData();

        //dbHelper.deleteAllData();

        dbHelper.close();

        evento = Events.stream().filter(eve-> eve.id ==Integer.parseInt(getArguments().get("event").toString())).collect(Collectors.toList()).get(0);

        ImageView imageView = view.findViewById(R.id.imageView1);
        imageView.setImageResource(getResources().getIdentifier(evento.Image, "drawable", view.getContext().getPackageName()));

        binding.textviewSecond.setText(evento.Title);
        binding.textviewThird.setText(evento.Date);
        binding.textviewFourth.setText(evento.nbPlace+"");


        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateData(evento.id , evento.nbPlace--);
                binding.textviewFourth.setText(evento.nbPlace-- + "");

                sendEmail();
              }
        });


    }

    private void sendEmail() {
        PackageManager packageManager = getContext().getPackageManager();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+"eya.dhamna@esprit.tn"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subscription done successfully !");
        emailIntent.putExtra(Intent.EXTRA_TEXT, " your subscription to "+evento.Title+" on " +evento.Date+" was done successfully ! Congrats");


        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(emailIntent);
        }
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}