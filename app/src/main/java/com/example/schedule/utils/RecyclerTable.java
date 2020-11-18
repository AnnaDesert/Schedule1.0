package com.example.schedule.utils;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedule.Lesson;
import com.example.schedule.R;
import com.example.schedule.Table;

import java.util.ArrayList;

public class RecyclerTable extends RecyclerView.Adapter<RecyclerTable.TableViewHolder>{
    ArrayList<Lesson> Lesson;
    int i;
    int sizeDay;
    public RecyclerTable(ArrayList<Lesson> Lesson, int i, int sizeDay){
        this.Lesson = Lesson;
        this.i = i;
        this.sizeDay = sizeDay;

    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        if(position%2 != 0){
            holder.textTime.setBackgroundResource(R.color.TextTime2);
            holder.textLesson.setBackgroundResource(R.color.TextTime2);
            holder.textKorpus.setBackgroundResource(R.color.TextTime2);
        }
        if((Lesson.get(position).getFull_name_lesson().equals(Table.Lesson_first.getFull_name_lesson()))&&(position!=0)) {
            holder.textLesson.setText("");
            holder.textKorpus.setText("");
        }
        else{
            holder.textLesson.setText(Html.fromHtml(Lesson.get(position).getFull_name_lesson()));
            holder.textKorpus.setText(Html.fromHtml(Lesson.get(position).getStation()));
        }
        if(position == 0){
            holder.textLesson.setText("       Предмет");
        }
        holder.textTime.setText(Table.Time[position]);
    }

    @Override
    public int getItemCount() {
        return sizeDay+2;
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
