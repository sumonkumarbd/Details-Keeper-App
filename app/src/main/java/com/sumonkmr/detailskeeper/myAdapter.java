package com.sumonkmr.detailskeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    HashMap<String, String> noteTemp;
    List<HashMap<String, String>> notes;
    private Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public myAdapter(Context context, SharedPreferences preferences, SharedPreferences.Editor editor, List<HashMap<String, String>> notes) {
        this.context = context;
        this.notes = notes;
        this.sharedPreferences = preferences;
        this.editor = editor;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        noteTemp = notes.get(position);
        String title, massage;
        title = noteTemp.get("title");
        massage = noteTemp.get("massage");
        editor.putString("title", title);
        editor.putString("massage", massage);
        editor.apply();
//        holder.titleTV.setText(title);
//        holder.massageTV.setText(massage);
        String savedTitle = sharedPreferences.getString("title", "Title is not Found!");
        String savedMassage = sharedPreferences.getString("massage", "Massage is not Found!");
        Log.d("AdapterMsg", "onBindViewHolder: "+savedTitle+" "+ savedMassage);
        holder.titleTV.setText(savedTitle);
        holder.massageTV.setText(savedMassage);


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTV, massageTV;

        public ViewHolder(View view) {
            super(view);
            titleTV = view.findViewById(R.id.titleTV);
            massageTV = view.findViewById(R.id.massageTV);


        }

    }
}