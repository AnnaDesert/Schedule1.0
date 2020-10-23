package com.example.schedule.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedule.Lesson;
import com.example.schedule.MainActivity;
import com.example.schedule.R;
import com.example.schedule.Table;

import java.util.ArrayList;

public class RecyclerTable extends RecyclerView.Adapter<RecyclerTable.TableViewHolder>{
    ArrayList<Lesson> Lesson = new ArrayList<>(8);
    int page;

    public RecyclerTable(ArrayList<Lesson> Lesson, int page){
        this.Lesson = Lesson;
        this.page = page;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        holder.textTime.setText("Время");

        if(true) {
            holder.textLesson.setText("");
            Log.i("myTag","Set text on position " + position);
        }
        else holder.textLesson.setText("Предмет");

        holder.textKorpus.setText("Корпус");
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    //////////////////////////////////////////////////////
    public class TableViewHolder extends RecyclerView.ViewHolder{
        TextView textTime;
        TextView textLesson;
        TextView textKorpus;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            textTime = (TextView) itemView.findViewById(R.id.textTime);
            textLesson = (TextView) itemView.findViewById(R.id.textLesson);
            textKorpus = (TextView) itemView.findViewById(R.id.textKorpus);
        }

    }
   ///////////////////////////////////////////////////////////


}
