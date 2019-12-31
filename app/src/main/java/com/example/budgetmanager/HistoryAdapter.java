package com.example.budgetmanager;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<History> item;
    private OnTapListener onTapListener;
    Activity activity;
    public HistoryAdapter (Activity activity,ArrayList<History> list)
    {
        item=list;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.historylist,parent,false);
        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.tvdate.setText(item.get(position).getDate());
        holder.tvamt.setText(item.get(position).getAmount());
        String cat = item.get(position).getCategory();
        holder.tvcategory.setText(cat);

        if(cat.equals("Award") ||cat.equals("Salary")
                ||cat.equals("Investment")||cat.equals("Gift")||
        cat.equals("Voucher")||cat.equals("Lottery")
                ||cat.equals("Refund")) {
            holder.ivtype.setImageResource(R.drawable.plus);
        } else {
            holder.ivtype.setImageResource(R.drawable.ic_negative);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onTapListener!=null){
                    onTapListener.OnTapViewe(position);
                }

            }
        });
    }





    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setOnTapListener(OnTapListener onTapListener){
        this.onTapListener=onTapListener;
    }

}

