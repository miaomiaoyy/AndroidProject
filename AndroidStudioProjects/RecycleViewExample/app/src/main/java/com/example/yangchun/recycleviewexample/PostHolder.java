package com.example.yangchun.recycleviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yangchun on 2/27/18.
 */

//hold the imageview and two textview
public class PostHolder extends RecyclerView.ViewHolder {
    TextView title, detail;
    ImageView image;


    public PostHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_title);//find from the layout.xml
        detail = (TextView) itemView.findViewById(R.id.item_detail);
        image = (ImageView) itemView.findViewById(R.id.item_image);


    }
}
