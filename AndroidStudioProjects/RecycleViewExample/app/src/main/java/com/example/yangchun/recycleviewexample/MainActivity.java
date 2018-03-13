package com.example.yangchun.recycleviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new PostDataAdapter(this, getPosts()));

    }

    private ArrayList<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        Post post = new Post();

        post.setTitle("Colosseum");
        post.setDetail("This is Colosseum");
        post.setImage(R.drawable.colosseum);
        posts.add(post);

        post = new Post();
        post.setTitle("Empire State");
        post.setDetail("This is Empire State");
        post.setImage(R.drawable.empire_state);
        posts.add(post);

        post = new Post();
        post.setTitle("Golden Gate");
        post.setDetail("This is Golden Gate");
        post.setImage(R.drawable.golden_gate);
        posts.add(post);

        post = new Post();
        post.setTitle("Great Wall");
        post.setDetail("This is Great Wall");
        post.setImage(R.drawable.great_wall);
        posts.add(post);

        post = new Post();
        post.setTitle("Machu Picchu");
        post.setDetail("This is Machu Picchu");
        post.setImage(R.drawable.machu_picchu);
        posts.add(post);

        post = new Post();
        post.setTitle("Pyramid");
        post.setDetail("This is Pyramid");
        post.setImage(R.drawable.pyramid);
        posts.add(post);

        post = new Post();
        post.setTitle("StoneHedge");
        post.setDetail("This is StoneHedge");
        post.setImage(R.drawable.stonehedge);
        posts.add(post);

        post = new Post();
        post.setTitle("Taj Mahal");
        post.setDetail("This is Taj Mahal");
        post.setImage(R.drawable.taj_mahal);
        posts.add(post);

        return posts;
    }

}
