package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView, imageView2;
    private FrameLayout popUpContainer;
    private ArrayList<String> favoriteItems;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.heart);
        imageView2 = findViewById(R.id.memaheart2);
        popUpContainer = findViewById(R.id.pop_up_container);
        favoriteItems = new ArrayList<>();



        //clicked heart
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite();
            }
        });

        //favorites
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUpContainer.getVisibility() == View.GONE){
                    showPopUp();
                    imageView.setImageResource(R.drawable.fav_filled_heart);

                }else {
                    hidePopUp();
                    imageView.setImageResource(R.drawable.heart_icon);
                }
            }
        });
    }
    private void hidePopUpCategories(){
        popUpContainer.setVisibility(View.GONE);
    }

    //once item is clicked - filled heart
    private void toggleCategories(){
        boolean isFavorited = favoriteItems.contains("item");

        if(isFavorited){
            favoriteItems.remove("item");
            imageView2.setImageResource(R.drawable.heart_no_color);
        } else {
            favoriteItems.add("item");
            imageView2.setImageResource(R.drawable.filled_heart);
        }
    }

    //Homepage Heart -- clicked
    private void showPopUp(){
        View popUpView = getLayoutInflater().inflate(R.layout.pop_up_layout, null);

        popUpContainer.removeAllViews();
        popUpContainer.addView(popUpView);

        popUpContainer.setVisibility(View.VISIBLE);
    }
    private void hidePopUp(){
        popUpContainer.setVisibility(View.GONE);
    }

    //once item is clicked - filled heart
    private void toggleFavorite() {
        boolean isFavorited = favoriteItems.contains("item");

        if (isFavorited) {
            favoriteItems.remove("item");
            imageView2.setImageResource(R.drawable.heart_no_color);
        } else {
            favoriteItems.add("item");
            imageView2.setImageResource(R.drawable.filled_heart);
        }
    }


}