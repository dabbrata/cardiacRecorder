package com.example.cardiacrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    ArrayList<Model> dataHolder;
    public MyAdapter( ArrayList<Model> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.username.setText(dataHolder.get(position).getUsername());
        holder.bpm.setText("Heart Rate: "+dataHolder.get(position).getBpm()+" BPM");
        holder.systolic.setText("Systolic: "+dataHolder.get(position).getSystolic()+" mmHg");
        holder.dyastolic.setText("Dyastolic: "+dataHolder.get(position).getDyastolic()+" mmHg");
        holder.date.setText(dataHolder.get(position).getDate());
        holder.time.setText(dataHolder.get(position).getTime());
//        holder.bpmComment.setText(dataHolder.get(position).getBpmcomment());
//        holder.sysComment.setText(dataHolder.get(position).getSyscomment());
//        holder.dyasComment.setText(dataHolder.get(position).getDyascomment());

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView username,bpm,systolic,dyastolic,date,time,bpmComment,sysComment,dyasComment;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.textView20);
            bpm = (TextView) itemView.findViewById(R.id.textView15);
            systolic = (TextView) itemView.findViewById(R.id.textView18);
            dyastolic = (TextView) itemView.findViewById(R.id.textView16);
            date = (TextView) itemView.findViewById(R.id.textView19);
            time = (TextView) itemView.findViewById(R.id.textView17);
//            bpmComment = (TextView) itemView.findViewById(R.id.textView21);
//            sysComment = (TextView) itemView.findViewById(R.id.textView22);
//            dyasComment = (TextView) itemView.findViewById(R.id.textView23);
        }
    }
}
