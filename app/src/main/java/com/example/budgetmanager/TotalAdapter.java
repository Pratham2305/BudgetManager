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

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.ViewHolder>{

    private ArrayList<Total_Category> item;
    private OnTapListener1 onTapListener;



    public TotalAdapter (Context context, ArrayList<Total_Category>list)

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
    public TotalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_list,parent,false);
        return new TotalAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalAdapter.ViewHolder holder, final int position)
    {
        holder.tvtitle.setText(item.get(position).getCategory_name());
        holder.tvamount.setText(item.get(position).getAmount());

        if(item.get(position).getCategory_name().equals("Auto"))
        {
            holder.ivcat.setImageResource(R.drawable.auto);
        }
        else if (item.get(position).getCategory_name().equals("Bill"))
        {
            holder.ivcat.setImageResource(R.drawable.bill);
        }
        else if(item.get(position).getCategory_name().equals("Entertainment"))
        {
            holder.ivcat.setImageResource(R.drawable.entertainment);
        }
        else if(item.get(position).getCategory_name().equals("Food"))
        {
            holder.ivcat.setImageResource(R.drawable.food);
        }
        else if(item.get(position).getCategory_name().equals("Fuel"))
        {
            holder.ivcat.setImageResource(R.drawable.fuel);
        }
        else if(item.get(position).getCategory_name().equals("General"))
        {
            holder.ivcat.setImageResource(R.drawable.general);
        }
        else if(item.get(position).getCategory_name().equals("Gift"))
        {
            holder.ivcat.setImageResource(R.drawable.gift);
        }
        else if(item.get(position).getCategory_name().equals("Gym"))
        {
            holder.ivcat.setImageResource(R.drawable.gym);
        }
        else if(item.get(position).getCategory_name().equals("Health"))
        {
            holder.ivcat.setImageResource(R.drawable.health);
        }
        else if (item.get(position).getCategory_name().equals("Holidays"))
        {
            holder.ivcat.setImageResource(R.drawable.holidays);
        }
        else if (item.get(position).getCategory_name().equals("House"))
        {
            holder.ivcat.setImageResource(R.drawable.house);
        }
        else if (item.get(position).getCategory_name().equals("Clothes"))
        {
            holder.ivcat.setImageResource(R.drawable.ic_clothes);
        }
        else if (item.get(position).getCategory_name().equals("Rent"))
        {
            holder.ivcat.setImageResource(R.drawable.rent);
        }
        else if (item.get(position).getCategory_name().equals("Sport"))
        {
            holder.ivcat.setImageResource(R.drawable.sport);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onTapListener != null) {
                    onTapListener.OnTapViewe(position);


                }
            }
        });


    }



    @Override
    public int getItemCount() {
        return item.size();
    }
    public void setOnTapListener(OnTapListener1 onTapListener){
        this.onTapListener=onTapListener;
    }
}
