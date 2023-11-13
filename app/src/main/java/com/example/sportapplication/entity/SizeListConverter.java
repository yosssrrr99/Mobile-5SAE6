package com.example.sportapplication.entity;

import android.util.Log;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;


public class SizeListConverter {
    @TypeConverter
    public static List<Size> fromString(String value) {
        List<Size> sizeList = new ArrayList<>();

        if (value != null) {
            // Split the string by comma
            String[] sizeStrings = value.split(",");

            for (String sizeString : sizeStrings) {
                // Remove leading and trailing whitespaces
                sizeString = sizeString.trim();

                // Skip empty strings
                if (!sizeString.isEmpty()) {
                    try {
                        // Attempt to convert the non-empty string to a Size enum
                        Size size = Size.valueOf(sizeString);

                        // Check if the size is not already in the list
                        if (!sizeList.contains(size)) {
                            sizeList.add(size);
                        }
                    } catch (IllegalArgumentException e) {
                        // Handle invalid sizeString (not a valid enum constant)
                        // Log an error, display a message, or skip the invalid value
                        Log.e("SizeList", "Invalid Size: " + sizeString);
                    }
                }
            }
        }

        return sizeList;
    }




    @TypeConverter
    public static String toString(List<Size> sizes) {
        return new Gson().toJson(sizes);
    }
}
