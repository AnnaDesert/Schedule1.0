package com.example.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{
    ArrayList<Catalogue> catalogue;

    public ListAdapter(ArrayList<Catalogue> catalogue){
        this.catalogue = catalogue;
    }
//////////////////////////////////////////////////////////////
    class ListViewHolder extends RecyclerView.ViewHolder{
        Button button;
        public ListViewHolder(@NonNull View view) {
            super(view);
            button = (Button) view.findViewById(R.id.button_of_list);
        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
            holder.button.setText(catalogue.get(position).getTextForButton());//Добавление текста в кнопку

    }

    @Override
    public int getItemCount() {
        return catalogue.size();
    }




}
