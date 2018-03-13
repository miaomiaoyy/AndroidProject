package com.example.miaomiao.recyclerviewexample;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miaomiao.myapplication.R;

import java.util.ArrayList;

public class PostDataAdapter extends RecyclerView.Adapter<PostHolder>{
    Context c;
    ArrayList<com.example.miaomiao.recyclerviewexample.Post> posts;

    public PostDataAdapter(Context c, ArrayList<com.example.miaomiao.recyclerviewexample.Post> posts){
        this.c = c;
        this.posts = posts;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a space to hold things
        View view = LayoutInflater.from(c).inflate(R.layout.layout, parent, false);//layout.xml
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        com.example.miaomiao.recyclerviewexample.Post post = posts.get(position);
        //Bind data
        holder.title.setText(post.getTitle());//holder is the container for the posts
        holder.detail.setText(post.getDetail());
        holder.image.setImageResource(post.getImage());

    }

    @Override
    public int getItemCount() {//when you scroll, you need to how many items you have to go
        return posts.size();
    }
}
