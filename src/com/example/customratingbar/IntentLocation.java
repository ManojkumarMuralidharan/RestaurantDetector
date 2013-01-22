package com.example.customratingbar;

import java.io.Serializable;


import android.os.Parcel;
import android.os.Parcelable;

public class IntentLocation implements Parcelable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	double mLatitude;
	double mLongitude;
	String mProvider;
	
	
	
	
	public String getmProvider() {
		return mProvider;
	}
	public void setmProvider(String mProvider) {
		this.mProvider = mProvider;
	}
	public double getmLatitude() {
		return mLatitude;
	}
	public void setmLatitude(double mLatitude) {
		this.mLatitude = mLatitude;
	}
	public double getmLongitude() {
		return mLongitude;
	}
	public void setmLongitude(double mLongitude) {
		this.mLongitude = mLongitude;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeDouble(mLatitude);
		dest.writeDouble(mLongitude);
		dest.writeString(mProvider);
		
	}
	/**
	 * It will be required during un-marshaling data stored in a Parcel
	 * @author 
	 */
	public static final Parcelable.Creator<IntentLocation> CREATOR = new Parcelable.Creator<IntentLocation>() {
        public IntentLocation createFromParcel(Parcel in) {
            return new IntentLocation(in);
        }

        public IntentLocation[] newArray(int size) {
            return new IntentLocation[size];
        }
    };

	
	public IntentLocation(Parcel source){
        /*
         * Reconstruct from the Parcel
         */
        //Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
        mLatitude = source.readDouble();
        mLongitude = source.readDouble();
        mProvider=source.readString();
        
  }
	public IntentLocation() {
		// TODO Auto-generated constructor stub
	}


}
