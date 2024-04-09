package com.example.myapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Categories extends AppCompatActivity {

    //click back arrow
    private ImageView back;

    //clickable

    private CardView hoho;
    private TextView ctops;
    private TextView cbottoms;

    private FrameLayout popUpTops;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);

        back = findViewById(R.id.back_arrow);
        //
        //ctops = findViewById(R.id.categories_tops);
        popUpTops = findViewById(R.id.pop_up_tops);
        hoho = findViewById(R.id.filled_tops);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        hoho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopsSection();
            }
        });



    }
    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void showTopsSection(){
        Intent intent = new Intent(this, MainActivity.class);
        //View popUpView = getLayoutInflater().inflate(R.layout.pop_up_layout_tops, null);
        //popUpTops.setVisibility(View.VISIBLE);
    }
   /* private void toggleCategories(){
        boolean isClicked = clickedTops.contains("section");

        if(isClicked){
            favoriteItems.remove("item");
            imageView2.setImageResource(R.drawable.heart_no_color);
        } else {
            favoriteItems.add("item");
            imageView2.setImageResource(R.drawable.filled_heart);
        }
    }   */

}