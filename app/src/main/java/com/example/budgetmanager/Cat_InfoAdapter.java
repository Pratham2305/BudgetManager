package com.example.budgetmanager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cat_InfoAdapter extends RecyclerView.Adapter<Cat_InfoAdapter.ViewHolder> {

    private ArrayList<Cat_Info> item;



    public Cat_InfoAdapter (Context context, ArrayList<Cat_Info>list)

    {
        item = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivtype;
        TextView tvdate;
        TextView tvamount,tvcategory;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvdate = itemView.findViewById(R.id.tvdate);
            ivtype = itemView.findViewById(R.id.ivtype);
            tvamount=itemView.findViewById(R.id.tvamt);
            tvcategory=itemView.findViewById(R.id.tvcategory);

        }

    }
    @NonNull
    @Override
    public Cat_InfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.historylist,parent,false);
        return new Cat_InfoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Cat_InfoAdapter.ViewHolder holder, final int position)
    {

        holder.tvdate.setText(item.get(position).getDate());
        holder.tvamount.setText(item.get(position).getAmount());
        String cat = item.get(position).getCategory();
        holder.tvcategory.setText(cat);

        if(item.get(position).getCategory().equals("auto"))
        {
            holder.ivtype.setImageResource(R.drawable.auto);
        }
        else if (item.get(position).getCategory().equals("bill"))
        {
            holder.ivtype.setImageResource(R.drawable.bill);
        }
        else if(item.get(position).getCategory().equals("entertainment"))
        {
            holder.ivtype.setImageResource(R.drawable.entertainment);
        }
        else if(item.get(position).getCategory().equals("food"))
        {
            holder.ivtype.setImageResource(R.drawable.food);
        }
        else if(item.get(position).getCategory().equals("fuel"))
        {
            holder.ivtype.setImageResource(R.drawable.fuel);
        }
        else if(item.get(position).getCategory().equals("general"))
        {
            holder.ivtype.setImageResource(R.drawable.general);
        }
        else if(item.get(position).getCategory().equals("gift"))
        {
            holder.ivtype.setImageResource(R.drawable.gift);
        }
        else if(item.get(position).getCategory().equals("gym"))
        {
            holder.ivtype.setImageResource(R.drawable.gym);
        }
        else if(item.get(position).getCategory().equals("health"))
        {
            holder.ivtype.setImageResource(R.drawable.health);
        }
        else if (item.get(position).getCategory().equals("holidays"))
        {
            holder.ivtype.setImageResource(R.drawable.holidays);
        }
        else if (item.get(position).getCategory().equals("house"))
        {
            holder.ivtype.setImageResource(R.drawable.house);
        }
        else if (item.get(position).getCategory().equals("clothes"))
        {
            holder.ivtype.setImageResource(R.drawable.ic_clothes);
        }
        else if (item.get(position).getCategory().equals("rent"))
        {
            holder.ivtype.setImageResource(R.drawable.rent);
        }
        else if (item.get(position).getCategory().equals("sport"))
        {
            holder.ivtype.setImageResource(R.drawable.sport);
        }
       else if(item.get(position).getCategory().equals("Award"))
        {
            holder.ivtype.setImageResource(R.drawable.award);
        }
        else if(item.get(position).getCategory().equals("Gift"))
       {
        holder.ivtype.setImageResource(R.drawable.gift_income);
       }
    else if(item.get(position).getCategory().equals("Investment"))
      {
        holder.ivtype.setImageResource(R.drawable.investment);
      }
    else if(item.get(position).getCategory().equals("Lottery"))
    {
        holder.ivtype.setImageResource(R.drawable.lottery);

    }
    else if(item.get(position).getCategory().equals("Salary"))
    {
        holder.ivtype.setImageResource(R.drawable.salary);
    }
    else if(item.get(position).getCategory().equals("Refund"))
    {
        holder.ivtype.setImageResource(R.drawable.refund);
    }
    else if(item.get(position).getCategory().equals("Voucher"))
    {
        holder.ivtype.setImageResource(R.drawable.voucher);
    }


    }



    @Override
    public int getItemCount() {
        return item.size();
    }








}
