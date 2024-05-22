package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private final ArrayList<TopItem> topItems;
    private final Context context;
    private FavDB favDB;

    public TopAdapter(ArrayList<TopItem> topItems, Context context){
        this.topItems = topItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        //create table on first
        SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean("firstStart", true);
        if (firstStart) {
             createTableOnFirstStart();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {
            final TopItem topItem = topItems.get(position);
            readCursorData(topItem, holder);
            holder.imageView.setImageResource(topItem.getImageResource());
            holder.titleTextView.setText(topItem.getTitle());

    }



    @Override
    public int getItemCount() {
        return topItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favBtn);

            //add to fav btn
            favBtn.setOnClickListener(this::onClick);
        }

        private void onClick(View v) {
            int position = getAdapterPosition();
            TopItem topItem = topItems.get(position);

            if (topItem.getFavStatus().equals("0")) {
                topItem.setFavStatus("1");
                favDB.insertIntoTheDatabase(topItem.getTitle(), topItem.getImageResource(),
                        topItem.getKey_id(), topItem.getFavStatus());
                favDB.setBackgroundResource(R.drawable.filled_heart);
            } else {
                topItem.setFavStatus("0");
                favDB.remove_fav(topItem.getKey_id());
                favDB.setBackgroundResource(R.drawable.heart_no_color);
            }

        }
    }
    private void createTableOnFirstStart() {
        favDB.insertEmpty();
        SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

    }
    private void readCursorData(TopItem topItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(topItem.getKey_id());
        try (SQLiteDatabase ignored = favDB.getReadableDatabase()) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAV_STATUS));
                topItem.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.filled_heart);

                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.heart_no_color);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
        }

    }
}
