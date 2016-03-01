package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lenovo on 3/1/2016.
 */
public class Specs implements Parcelable {

    private String specId;
    private Image image;
    private ArrayList<SpecCatagory> specCatagoryList;

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList<SpecCatagory> getSpecCatagoryList() {
        return specCatagoryList;
    }

    public void setSpecCatagoryList(ArrayList<SpecCatagory> specCatagoryList) {
        this.specCatagoryList = specCatagoryList;
    }

    public Specs() {
        specId = "";
        image = new Image();
        specCatagoryList = new ArrayList<SpecCatagory>();
    }

    protected Specs(Parcel in) {
    }

    public static final Creator<Specs> CREATOR = new Creator<Specs>() {
        @Override
        public Specs createFromParcel(Parcel in) {
            return new Specs(in);
        }

        @Override
        public Specs[] newArray(int size) {
            return new Specs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(specId);
        dest.writeValue(image);
        dest.writeTypedList(specCatagoryList);
    }
}
