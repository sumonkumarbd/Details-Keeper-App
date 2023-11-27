package com.sumonkmr.detailskeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    HashMap<String,String> noteTemp;
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
        String title,massage;
        title = noteTemp.get("title");
        massage = noteTemp.get("massage");


        holder.nameTV.setText(title);
        holder.phoneTV.setText(massage);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV,phoneTV;

        public ViewHolder(View view) {
            super(view);
            nameTV = view.findViewById(R.id.titleTV);
            phoneTV = view.findViewById(R.id.massageTV);
        }
    }
}