package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lenovo on 2/27/2016.
 */
public class Models implements Parcelable {
    private String model_id;
    private String model_name;
    private double cost;
    private List<String> description;
    private Image image;

    protected Models(Parcel in) {
        model_id = in.readString();
        model_name = in.readString();
        cost = in.readDouble();
        description = in.createStringArrayList();
        image = in.readParcelable(Image.class.getClassLoader());
    }

    public Models() {
    }

    public static final Creator<Models> CREATOR = new Creator<Models>() {
        @Override
        public Models createFromParcel(Parcel in) {
            return new Models(in);
        }

        @Override
        public Models[] newArray(int size) {
            return new Models[size];
        }
    };

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
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
        dest.writeString(model_id);
        dest.writeString(model_name);
        dest.writeList(description);
        dest.writeValue(image);
    }
}
