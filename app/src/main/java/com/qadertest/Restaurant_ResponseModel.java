package com.qadertest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ITP on 30-Nov-17.
 */

public class Restaurant_ResponseModel implements Parcelable {

    String name;
    String category;
    String lat;
    String lon;

    public Restaurant_ResponseModel() {
    }

    protected Restaurant_ResponseModel(Parcel in) {
        name = in.readString();
        category = in.readString();
        lat = in.readString();
        lon = in.readString();
    }

    public static final Creator<Restaurant_ResponseModel> CREATOR = new Creator<Restaurant_ResponseModel>() {
        @Override
        public Restaurant_ResponseModel createFromParcel(Parcel in) {
            return new Restaurant_ResponseModel(in);
        }

        @Override
        public Restaurant_ResponseModel[] newArray(int size) {
            return new Restaurant_ResponseModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(category);
        parcel.writeString(lat);
        parcel.writeString(lon);
    }
}
