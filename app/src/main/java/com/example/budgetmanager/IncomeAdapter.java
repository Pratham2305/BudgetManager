package com.example.budgetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder>
{
    private ArrayList<Income> item;

    ItemClicked activity;


    public interface ItemClicked
    {
        void onItemClicked(int index);
    }


    public IncomeAdapter (Context context, ArrayList<Income>list)
    {
        item = list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivitem;
        TextView tvitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvitem = itemView.findViewById(R.id.tvitem);
            ivitem = itemView.findViewById(R.id.ivitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onItemClicked(item.indexOf((Income) v.getTag()));

                }
            });
        }
    }



    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.incomelist,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(item.get(position));
        holder.tvitem.setText(item.get(position).getItemname());

        if(item.get(position).getCategory().equals("Award"))
        {
            holder.ivitem.setImageResource(R.drawable.award);
        }
        else if(item.get(position).getCategory().equals("Gift"))
        {
            holder.ivitem.setImageResource(R.drawable.gift_income);
        }
        else if(item.get(position).getCategory().equals("Investment"))
        {
            holder.ivitem.setImageResource(R.drawable.investment);
        }
        else if(item.get(position).getCategory().equals("Lottery"))
        {
            holder.ivitem.setImageResource(R.drawable.lottery);

        }
        else if(item.get(position).getCategory().equals("Salary"))
        {
            holder.ivitem.setImageResource(R.drawable.salary);
        }
        else if(item.get(position).getCategory().equals("Refund"))
        {
            holder.ivitem.setImageResource(R.drawable.refund);
        }
        else if(item.get(position).getCategory().equals("Voucher"))
        {
            holder.ivitem.setImageResource(R.drawable.voucher);
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
