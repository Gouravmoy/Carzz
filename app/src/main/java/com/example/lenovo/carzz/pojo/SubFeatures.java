package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2/28/2016.
 */
public class SubFeatures implements Parcelable {
    private String title;
    private String synopsis;
    private Image image;

    public SubFeatures() {
        title = "";
        synopsis = "";
        image = new Image();
    }

    protected SubFeatures(Parcel in) {
        title = in.readString();
        synopsis = in.readString();
        image = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Creator<SubFeatures> CREATOR = new Creator<SubFeatures>() {
        @Override
        public SubFeatures createFromParcel(Parcel in) {
            return new SubFeatures(in);
        }

        @Override
        public SubFeatures[] newArray(int size) {
            return new SubFeatures[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(synopsis);
        dest.writeValue(image);
    }
}
