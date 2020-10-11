package com.example.schedule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{
    static ArrayList<Catalogue> catalogue;

    public ListAdapter(ArrayList<Catalogue> catalogue){
        this.catalogue = catalogue;
    }
//////////////////////////////////////////////////////////////
    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    Button button;

    float x;
    float y;
    String sDown;
    String sMove;
    String sUp;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        ArrayList<Catalogue> catalogue;
        button = (Button) itemView.findViewById(R.id.button_of_list);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        button.setBackgroundResource(R.drawable.my_button_touch);
        button.setTextColor(Color.parseColor("#FFFFFF"));
        CreateNewListOfButton.CreateButton(
                ListAdapter.catalogue.
                        get(getAdapterPosition()).
                        getIdForButton() + "/");
    }

}
////////////////////////////////////////////////////////////////////
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
