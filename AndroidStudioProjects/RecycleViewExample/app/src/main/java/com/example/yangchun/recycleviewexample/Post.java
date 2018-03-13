package com.example.yangchun.recycleviewexample;

/**
 * Created by yangchun on 2/27/18.
 */
//to every single post, we have setters and getters
public class Post {
    String title, detail;
    int image;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }

}
