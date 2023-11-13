package com.example.projectcitysport;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.projectcitysport.databinding.FragmentFirstBinding;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText editTextSearch;
    private List<Integer> Images;
    private List<Integer> Ids;
    private List<String> Titles;
    private int currentImageIndex = 0;
    private View view;
    DBHelper dbHelper;

    ArrayList<Event> Events ;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        //return binding.getRoot();

        dbHelper = new DBHelper(binding.getRoot().getContext()); // Initialize the database helper
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Get a writable database

//        dbHelper.insertData();
        
        Events = (ArrayList<Event>) dbHelper.getAllData();

//        dbHelper.deleteAllData();

        dbHelper.close();

        view = binding.getRoot();
        editTextSearch = view.findViewById(R.id.searchEditText);

        ////// List of Images
        Images = new ArrayList<>(10);
        Ids = new ArrayList<>();
        Titles = new ArrayList<>();

        for (Event eve : Events){
            Ids.add(eve.id);
            Titles.add(eve.Title);
            Images.add(getResources().getIdentifier(eve.Image, "drawable", view.getContext().getPackageName()));
        }
        ImageView imageView = view.findViewById(R.id.imageView1);
        imageView.setImageResource(Images.get(currentImageIndex));
        TextView textView = view.findViewById(R.id.textview_first);
        textView.setText(Titles.get(currentImageIndex));

        return view;
    }

    private void showNextImage() {
        if (currentImageIndex < Images.size() - 1) {
            currentImageIndex++;
            updateImageViewWithCurrentImage();
        }
    }

    private void showPreviousImage() {
        if (currentImageIndex > 0) {
            currentImageIndex--;
            updateImageViewWithCurrentImage();
        }
    }
    private void updateImageViewWithCurrentImage() {
        ImageView imageView = view.findViewById(R.id.imageView1);
        imageView.setImageResource(Images.get(currentImageIndex));
        TextView textView = view.findViewById(R.id.textview_first);
        textView.setText(Titles.get(currentImageIndex));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment );
            }
        });

        binding.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("event",Ids.get(currentImageIndex)+"");
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment , bundle);
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextImage();
            }
        });

        binding.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPreviousImage();
            }
        });
        editTextSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if(editTextSearch.getText().toString().equals("")){

                    Events = (ArrayList<Event>) dbHelper.getAllData();

                    //dbHelper.deleteAllData();

                    dbHelper.close();


                    ////// List of Images
                    Images = new ArrayList<>(10);
                    Ids = new ArrayList<>();
                    Titles = new ArrayList<>();

                    for (Event eve : Events){
                        Ids.add(eve.id);
                        Titles.add(eve.Title);
                        Images.add(getResources().getIdentifier(eve.Image, "drawable", view.getContext().getPackageName()));
                    }
                    ImageView imageView = view.findViewById(R.id.imageView1);
                    imageView.setImageResource(Images.get(0));
                    TextView textView = view.findViewById(R.id.textview_first);
                    textView.setText(Titles.get(0));
                }else {
                    Events= (ArrayList<Event>)   Events.stream()
                            .filter(c -> c.Title.toLowerCase().contains(editTextSearch.getText().toString().toLowerCase()))
                            .collect(Collectors.toList());

                    Images.clear();
                    Ids.clear();
                    Titles.clear();

                    for (Event eve : Events){
                        Titles.add(eve.Title);
                        Ids.add(eve.id);
                        Images.add(getResources().getIdentifier(eve.Image, "drawable", view.getContext().getPackageName()));
                    }


                    if(Titles.size()> 0){
                        ImageView imageView = view.findViewById(R.id.imageView1);
                        imageView.setImageResource(Images.get(0));
                        TextView textView = view.findViewById(R.id.textview_first);
                        textView.setText(Titles.get(0));
                    }else{

                        ImageView imageView = view.findViewById(R.id.imageView1);
                        imageView.setImageResource(R.drawable.noresullts);
                        TextView textView = view.findViewById(R.id.textview_first);
                        textView.setText("No corresponding results");

                    }
                }



            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}