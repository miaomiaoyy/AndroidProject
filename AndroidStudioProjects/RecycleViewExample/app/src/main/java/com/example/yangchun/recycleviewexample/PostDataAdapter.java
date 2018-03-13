package com.example.yangchun.recycleviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yangchun on 2/27/18.
 */

public class PostDataAdapter extends RecyclerView.Adapter<PostHolder>{
    Context c;
    ArrayList<Post> posts;

    public PostDataAdapter(Context c, ArrayList<Post> posts){
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
        Post post = posts.get(position);
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
