package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lenovo on 2/28/2016.
 */
public class Catagory implements Parcelable{
    private String catagoryName;
    private ArrayList<SubFeatures> subFeaturesArrayList;

    public Catagory() {
        catagoryName = "";
        subFeaturesArrayList = new ArrayList<SubFeatures>();
    }

    protected Catagory(Parcel in) {
        catagoryName = in.readString();
        subFeaturesArrayList = in.createTypedArrayList(SubFeatures.CREATOR);
    }

    public static final Creator<Catagory> CREATOR = new Creator<Catagory>() {
        @Override
        public Catagory createFromParcel(Parcel in) {
            return new Catagory(in);
        }

        @Override
        public Catagory[] newArray(int size) {
            return new Catagory[size];
        }
    };

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public ArrayList<SubFeatures> getSubFeaturesArrayList() {
        return subFeaturesArrayList;
    }

    public void setSubFeaturesArrayList(ArrayList<SubFeatures> subFeaturesArrayList) {
        this.subFeaturesArrayList = subFeaturesArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(catagoryName);
        dest.writeTypedList(subFeaturesArrayList);
    }
}
