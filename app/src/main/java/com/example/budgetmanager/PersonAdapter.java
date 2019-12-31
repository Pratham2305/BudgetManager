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

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{
private ArrayList<Person> item;
ItemClicked activity;


public interface ItemClicked
{
    void onItemClicked(int index);
}

public PersonAdapter (Context context,ArrayList<Person>list)

{
  item = list;
  activity = (ItemClicked) context;
}

public class ViewHolder extends RecyclerView.ViewHolder
{
    ImageView ivitem;
    TextView tvitem;

    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        tvitem = itemView.findViewById(R.id.tvitem);
        ivitem = itemView.findViewById(R.id.ivitem);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.onItemClicked(item.indexOf((Person)v.getTag()));


            }
        });
    }
}


    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenselist,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position)
    {
        holder.itemView.setTag(item.get(position));
         holder.tvitem.setText(item.get(position).getItemname());

         if(item.get(position).getCategory().equals("auto"))
         {
             holder.ivitem.setImageResource(R.drawable.auto);
         }
         else if (item.get(position).getCategory().equals("bill"))
         {
             holder.ivitem.setImageResource(R.drawable.bill);
         }
         else if(item.get(position).getCategory().equals("entertainment"))
         {
             holder.ivitem.setImageResource(R.drawable.entertainment);
         }
         else if(item.get(position).getCategory().equals("food"))
         {
             holder.ivitem.setImageResource(R.drawable.food);
         }
         else if(item.get(position).getCategory().equals("fuel"))
         {
             holder.ivitem.setImageResource(R.drawable.fuel);
         }
         else if(item.get(position).getCategory().equals("general"))
         {
             holder.ivitem.setImageResource(R.drawable.general);
         }
         else if(item.get(position).getCategory().equals("gift"))
         {
             holder.ivitem.setImageResource(R.drawable.gift);
         }
         else if(item.get(position).getCategory().equals("gym"))
         {
             holder.ivitem.setImageResource(R.drawable.gym);
         }
         else if(item.get(position).getCategory().equals("health"))
         {
             holder.ivitem.setImageResource(R.drawable.health);
         }
         else if (item.get(position).getCategory().equals("holidays"))
         {
             holder.ivitem.setImageResource(R.drawable.holidays);
         }
         else if (item.get(position).getCategory().equals("house"))
         {
             holder.ivitem.setImageResource(R.drawable.house);
         }
         else if (item.get(position).getCategory().equals("clothes"))
         {
             holder.ivitem.setImageResource(R.drawable.ic_clothes);
         }
         else if (item.get(position).getCategory().equals("rent"))
         {
             holder.ivitem.setImageResource(R.drawable.rent);
         }
         else if (item.get(position).getCategory().equals("sport"))
         {
           holder.ivitem.setImageResource(R.drawable.sport);
         }

         }



    @Override
    public int getItemCount() {
        return item.size();
    }
}
