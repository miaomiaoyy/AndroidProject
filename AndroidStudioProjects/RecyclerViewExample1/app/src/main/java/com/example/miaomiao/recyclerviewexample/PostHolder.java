package com.example.miaomiao.recyclerviewexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



    public class PostHolder extends RecyclerView.ViewHolder {
        TextView title, detail;
        ImageView image;


        public PostHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_image);//find from the layout.xml
            detail = (TextView) itemView.findViewById(R.id.item_detail);
            image = (ImageView) itemView.findViewById(R.id.item_image);


        }
    }

