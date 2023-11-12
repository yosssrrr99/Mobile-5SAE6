package com.example.sportapplication.model;

public class RecentlyViewed {
    int backgroundcolor;
    String name;



    String description;

    public int getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(int backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    String price;
    String gender;

    int imageUrl;
    String rating;
    public RecentlyViewed(int backgroundcolor, String name, String price, int imageUrl) {
        this.backgroundcolor = backgroundcolor;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public RecentlyViewed(String name, String price, int imageUrl,String rating) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating=rating;    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public RecentlyViewed( int imageUrl) {

        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }





    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
