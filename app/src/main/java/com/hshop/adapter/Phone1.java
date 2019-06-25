package com.hshop.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nilay on 10/18/2017.
 */

public class Phone1 implements Parcelable {
    private String name;

    public Phone1(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Phone1(String name) {
        this.name = name;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Phone1> CREATOR = new Creator<Phone1>() {
        @Override
        public Phone1 createFromParcel(Parcel in) {
            return new Phone1(in);
        }

        @Override
        public Phone1[] newArray(int size) {
            return new Phone1[size];
        }
    };
}
