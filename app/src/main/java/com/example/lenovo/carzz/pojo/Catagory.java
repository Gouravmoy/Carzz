package com.example.lenovo.carzz.pojo;

import java.util.ArrayList;

/**
 * Created by lenovo on 2/28/2016.
 */
public class Catagory {
    private String catagoryName;
    private ArrayList<SubFeatures> subFeaturesArrayList;

    public Catagory() {
        catagoryName = "";
        subFeaturesArrayList = new ArrayList<SubFeatures>();
    }

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
}
