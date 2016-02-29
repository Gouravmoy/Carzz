package com.example.lenovo.carzz.pojo;

import java.util.ArrayList;

/**
 * Created by lenovo on 2/28/2016.
 */
public class Features {
    private String carName;
    private String featureId;
    ArrayList<Catagory> catagoryArrayList;

    public Features() {
        carName = "";
        featureId = "";
        catagoryArrayList = new ArrayList<Catagory>();
    }

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
}
