package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2/22/2016.
 */
public class Image implements Parcelable {
    private String thumnail;
    private String profile;

    protected Image(Parcel in) {
        thumnail = in.readString();
        profile = in.readString();
    }

    public Image() {
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getThumnail() {
        return thumnail;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumnail);
        dest.writeString(profile);
    }
}
