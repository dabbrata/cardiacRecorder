package com.example.cardiacrecorder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This class is created to show data in recyler view items through adapter.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    ArrayList<Model> dataHolder;
    public MyAdapter( ArrayList<Model> dataHolder) {
        this.dataHolder = dataHolder;
    }
    //Context context;

    /**
     * @param parent
     * @param viewType
     * @return
     */

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myViewHolder(view);
    }

    /**
     *
     * @param holder
     * @param position
     */

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

       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           /**
            *
            * @param view
            * @return
            */
           @Override
           public boolean onLongClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
               builder.setMessage("Do you want to delete "+dataHolder.get(position).getUsername()+"'s record?")
                       .setCancelable(true)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               DatabaseHelper db = new DatabaseHelper(view.getContext());
                               db.deleteOne(dataHolder.get(position).getId());

                               dataHolder.remove(position);
                               notifyItemRemoved(position);
                               Toast.makeText(view.getContext(), "Record is deleted", Toast.LENGTH_SHORT).show();

                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.cancel();
                           }
                       });
               AlertDialog alertDialog = builder.create();
               alertDialog.show();
               return false;
           }
       });

        /**
         * after clicking recyler view items the value will be passed to the profile activity.
         */
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i =new Intent(view.getContext(),ProfileActivity.class);
               Bundle bundle = new Bundle();
               bundle.putInt("id",dataHolder.get(position).getId());
               bundle.putString("username",dataHolder.get(position).getUsername());
               bundle.putString("bpm",dataHolder.get(position).getBpm());
               bundle.putString("sys",dataHolder.get(position).getSystolic());
               bundle.putString("dyas",dataHolder.get(position).getDyastolic());
               bundle.putString("date",dataHolder.get(position).getDate());
               bundle.putString("time",dataHolder.get(position).getTime());

//               bundle.putString("bpm_com",dataHolder.get(position).getBpmcomment());
//               bundle.putString("sys_com",dataHolder.get(position).getSyscomment());
//               bundle.putString("dyas_com",dataHolder.get(position).getDyascomment());

               i.putExtras(bundle);
               view.getContext().startActivity(i);
           }
       });
    }

    /**
     * return the number of total items in recycler view.
     * @return
     */
    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView username,bpm,systolic,dyastolic,date,time,bpmComment,sysComment,dyasComment;

        /**
         *
         * @param itemView
         */
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
