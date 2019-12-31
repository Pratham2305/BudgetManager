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

public class TotalAdapter01 extends RecyclerView.Adapter<TotalAdapter01.ViewHolder> {


    private ArrayList<Total_Category01> item;



    public TotalAdapter01(Context context, ArrayList<Total_Category01>list)

    {
        item = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivcat;
        TextView tvtitle;
        TextView tvamount;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvtitle = itemView.findViewById(R.id.tvtitle);
            ivcat = itemView.findViewById(R.id.ivcat);
            tvamount=itemView.findViewById(R.id.tvamount);



        }

    }
    @NonNull
    @Override
    public TotalAdapter01.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_list,parent,false);
        return new TotalAdapter01.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalAdapter01.ViewHolder holder,int position)
    {
        holder.tvtitle.setText(item.get(position).getCategory_name());
        holder.tvamount.setText(item.get(position).getAmount());
        String c = item.get(position).getCategory_name();

        if(c.equals("Award"))
        {
            holder.ivcat.setImageResource(R.drawable.award);
        }
        else if(c.equals("Gift"))
        {
            holder.ivcat.setImageResource(R.drawable.gift_income);
        }
        else if(c.equals("Investment"))
        {
            holder.ivcat.setImageResource(R.drawable.investment);
        }
        else if(c.equals("Lottery"))
        {
            holder.ivcat.setImageResource(R.drawable.lottery);

        }
        else if(c.equals("Salary"))
        {
            holder.ivcat.setImageResource(R.drawable.salary);
        }
        else if(c.equals("Refund"))
        {
            holder.ivcat.setImageResource(R.drawable.refund);
        }
        else if(c.equals("Voucher"))
        {
            holder.ivcat.setImageResource(R.drawable.voucher);
        }

    }



    @Override
    public int getItemCount() {
        return item.size();
    }
}
