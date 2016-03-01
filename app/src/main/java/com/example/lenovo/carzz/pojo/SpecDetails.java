package com.example.lenovo.carzz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 3/1/2016.
 */
public class SpecDetails implements Parcelable {

    private String description;
    private String hint;
    private String specName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    protected SpecDetails(Parcel in) {
    }

    public SpecDetails() {
        description = "";
        hint = "";
        specName = "";
    }

    public static final Creator<SpecDetails> CREATOR = new Creator<SpecDetails>() {
        @Override
        public SpecDetails createFromParcel(Parcel in) {
            return new SpecDetails(in);
        }

        @Override
        public SpecDetails[] newArray(int size) {
            return new SpecDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(hint);
        dest.writeString(specName);
    }
}
