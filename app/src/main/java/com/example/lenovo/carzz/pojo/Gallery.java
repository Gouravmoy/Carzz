package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.lenovo.carzz.logging.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2/22/2016.
 */
public class Gallery implements Parcelable {

    private String id;
    private String carName;
    private List<Image> imageList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Gallery(Parcel in) {
        carName = in.readString();
    }

    public Gallery() {
        id = "";
        carName = "";
        imageList = new ArrayList<>();
    }

    public static final Creator<Gallery> CREATOR = new Creator<Gallery>() {
        @Override
        public Gallery createFromParcel(Parcel in) {
            return new Gallery(in);
        }

        @Override
        public Gallery[] newArray(int size) {
            return new Gallery[size];
        }
    };

    @Override
    public int describeContents() {
        L.m("Describes Contents of Movie");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(carName);
        dest.writeTypedList(imageList);
    }
}
