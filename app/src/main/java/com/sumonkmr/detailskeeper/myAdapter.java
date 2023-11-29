package com.sumonkmr.detailskeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    HashMap<String, String> noteTemp;
    List<HashMap<String, String>> notes;
    private Context context;

    public myAdapter(Context context, List<HashMap<String, String>> notes) {
        this.context = context;
        this.notes = notes;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        noteTemp = notes.get(position);
        String title, massage;
        title = noteTemp.get("title");
        massage = noteTemp.get("massage");
        MainActivity.editor.putString("title", title);
        MainActivity.editor.putString("massage", massage);
        holder.titleTV.setText(title);
        holder.massageTV.setText(massage);
//        holder.titleTV.setText(MainActivity.sharedPreferences.getString("title", "This is not found"));
//        holder.massageTV.setText(MainActivity.sharedPreferences.getString("massage", "This is not found"));

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