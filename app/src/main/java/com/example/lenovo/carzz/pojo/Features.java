package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lenovo on 2/28/2016.
 */
public class Features implements Parcelable {
    private String carName;
    private String featureId;
    ArrayList<Catagory> catagoryArrayList;

    public Features() {
        carName = "";
        featureId = "";
        catagoryArrayList = new ArrayList<Catagory>();
    }

    protected Features(Parcel in) {
        carName = in.readString();
        featureId = in.readString();
        catagoryArrayList = in.createTypedArrayList(Catagory.CREATOR);
    }

    public static final Creator<Features> CREATOR = new Creator<Features>() {
        @Override
        public Features createFromParcel(Parcel in) {
            return new Features(in);
        }

        @Override
        public Features[] newArray(int size) {
            return new Features[size];
        }
    };

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public ArrayList<Catagory> getCatagoryArrayList() {
        return catagoryArrayList;
    }

    public void setCatagoryArrayList(ArrayList<Catagory> catagoryArrayList) {
        this.catagoryArrayList = catagoryArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(carName);
        dest.writeString(featureId);
        dest.writeTypedList(catagoryArrayList);

    }
}
