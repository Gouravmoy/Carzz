package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 3/1/2016.
 */
public class SpecCatagory implements Parcelable {

    private String catagoryName;
    private List<SpecDetails> specDetailsList;

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public List<SpecDetails> getSpecDetailsList() {
        return specDetailsList;
    }

    public void setSpecDetailsList(List<SpecDetails> specDetailsList) {
        this.specDetailsList = specDetailsList;
    }

    public SpecCatagory() {
        catagoryName = "";
        specDetailsList = new ArrayList<SpecDetails>();
    }

    protected SpecCatagory(Parcel in) {
    }

    public static final Creator<SpecCatagory> CREATOR = new Creator<SpecCatagory>() {
        @Override
        public SpecCatagory createFromParcel(Parcel in) {
            return new SpecCatagory(in);
        }

        @Override
        public SpecCatagory[] newArray(int size) {
            return new SpecCatagory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(catagoryName);
        dest.writeTypedList(specDetailsList);
    }
}
