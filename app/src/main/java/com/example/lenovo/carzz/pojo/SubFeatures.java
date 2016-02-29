package com.example.lenovo.carzz.pojo;

/**
 * Created by lenovo on 2/28/2016.
 */
public class SubFeatures {
    private String title;
    private String synopsis;
    private Image image;

    public SubFeatures() {
        title = "";
        synopsis = "";
        image = new Image();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
