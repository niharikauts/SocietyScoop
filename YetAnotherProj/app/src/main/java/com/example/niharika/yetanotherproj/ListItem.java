package com.example.niharika.yetanotherproj;

import android.graphics.Bitmap;

/**
 * Created by NIHARIKA on 17-03-2016.
 */
public class ListItem {

    private String imageUrl;
    private String title;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private String desc;
    private Bitmap image;



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
