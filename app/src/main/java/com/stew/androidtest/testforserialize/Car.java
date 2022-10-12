package com.stew.androidtest.testforserialize;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by stew on 3/20/22.
 * mail: stewforani@gmail.com
 */
public class Car implements Parcelable {
    public String name;
    public String brand;
    public String type;

    public Car() {
        Log.d("stew_car_parcel", "Car: init");
    }

    protected Car(Parcel in) {
        Log.d("stew_car_parcel", "Car: in 1");
        name = in.readString();
        Log.d("stew_car_parcel", "Car: in 2");
        brand = in.readString();
        Log.d("stew_car_parcel", "Car: in 3");
        type = in.readString();
        Log.d("stew_car_parcel", "Car: in 4");
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            Log.d("stew_car_parcel", "Car: in 0");
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d("stew_car_parcel", "writeToParcel: 0");
        dest.writeString(name);
        Log.d("stew_car_parcel", "writeToParcel: 1");
        dest.writeString(brand);
        Log.d("stew_car_parcel", "writeToParcel: 2");
        dest.writeString(type);
        Log.d("stew_car_parcel", "writeToParcel: 3");
    }
}
