package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
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


    //RecyclerView Adapter
    private MyItemAdapter adapter;
    private ImageView imageView;
    private ImageView imageView2;
    private FrameLayout popUpContainer;
    private ArrayList<String> favoriteItems;

    //tops & bottoms
    private TextView tops;
    private TextView bottoms;
    private FrameLayout popUpTops;
    private FrameLayout popUpBottoms;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //BottomNavigation
   /*     BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.home);

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    return true;
                case R.id.android:
                    startActivity(new Intent(getApplicationContext(), Categories.class));
                    finish();
                    return true;
                case R.id.add:
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    finish();
                    return true;
            }
            return false;
        });
*/
        imageView = findViewById(R.id.heart);
        imageView2 = findViewById(R.id.heart2);
        popUpContainer = findViewById(R.id.pop_up_container);
        favoriteItems = new ArrayList<>();



        //tops & bottoms
        popUpTops = findViewById(R.id.pop_up_tops);
        popUpBottoms = findViewById(R.id.pop_up_bottoms);
        tops = findViewById(R.id.tops);
        bottoms = findViewById(R.id.bottoms);

        tops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openTopsSection();
                openCategories();
                //showPopUpTops();
            }
        });
        //TRY ---------------------------------------------------------------------------------
        bottoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openTopsSection();
                openCategories();

            }
        });


        //RecyclerView Adapter - not yet finished
// RecyclerView recyclerView = findViewById(R.id.recycler_view);
// recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
// List<MyDataItem> data = new ArrayList<>();
// data.add(new MyDataItem("Item 1"));
// data.add(new MyDataItem("Item 2"));
//
// adapter = new MyItemAdapter(data);
// recyclerView.setAdapter(adapter);
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
    //Categories -- when tops and bottoms clicked

    private void showPopUpTops(){
        View popUpView = getLayoutInflater().inflate(R.layout.pop_up_layout_tops, null);

        popUpTops.removeAllViews();
        popUpTops.addView(popUpView);

        popUpTops.setVisibility(View.VISIBLE);
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
    private void toggleFavorite(){
        boolean isFavorited = favoriteItems.contains("item");

        if(isFavorited){
            favoriteItems.remove("item");
            imageView2.setImageResource(R.drawable.heart_no_color);
        } else {
            favoriteItems.add("item");
            imageView2.setImageResource(R.drawable.filled_heart);
        }
    }
    private void updateFavoritesPage(){
        //RecyclerView recyclerView =
    }
    //tops & bottoms
    private void openCategories(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

}