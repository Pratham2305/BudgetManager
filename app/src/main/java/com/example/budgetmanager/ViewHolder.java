package com.example.budgetmanager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView ivtype;
    TextView tvdate;
    TextView tvcategory;
    TextView tvamt;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        tvamt = itemView.findViewById(R.id.tvamt);
        tvcategory = itemView.findViewById(R.id.tvcategory);
        tvdate = itemView.findViewById(R.id.tvdate);
        ivtype = (ImageView) itemView.findViewById(R.id.ivtype );




    }
}