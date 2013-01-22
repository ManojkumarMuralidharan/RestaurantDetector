package com.example.customratingbar;

import android.os.Parcel;
import android.os.Parcelable;


public class Restuarant implements Parcelable{
	private String restuarant_yelp_id;
	private String restuarant_name ;
	 private String restuarant_cityState ;
	 private String restuarant_distance;
	 private String restuarant_image;
	 private String restuarant_phonenumber;
	 private String restuarant_website;
	 private double restuarant_rating;
	 private String restuarant_type;
	 private double restuarant_price ;
	 private double restuarant_latitude;
	 private double restuarant_longitude;
	 private String restuarant_address;
	 private String restuarant_noofreviews;
	 private String restuarant_categories; 
	 //private ArrayList<ReviewBean> restuarant_reviews;
	 
	 
	public String getRestuarant_yelp_id() {
		return restuarant_yelp_id;
	}
	public void setRestuarant_yelp_id(String restuarant_yelp_id) {
		this.restuarant_yelp_id = restuarant_yelp_id;
	}
	/*public ArrayList<ReviewBean> getRestuarant_reviews() {
		return restuarant_reviews;
	}
	public void setRestuarant_reviews(ArrayList<ReviewBean> test) {
		this.restuarant_reviews = test;
	}*/
	public String getRestuarant_categories() {
		return restuarant_categories;
	}
	public void setRestuarant_categories(String restuarant_categories) {
		this.restuarant_categories = restuarant_categories;
	}
	public String getRestuarant_noofreviews() {
		return restuarant_noofreviews;
	}
	public void setRestuarant_noofreviews(String restuarant_noofreviews) {
		this.restuarant_noofreviews = restuarant_noofreviews;
	}
	public String getRestuarant_address() {
		return restuarant_address;
	}
	public void setRestuarant_address(String restuarant_address) {
		this.restuarant_address = restuarant_address;
	}
	public double getRestuarant_latitude() {
		return restuarant_latitude;
	}
	public void setRestuarant_latitude(double restuarant_latitude) {
		this.restuarant_latitude = restuarant_latitude;
	}
	public double getRestuarant_longitude() {
		return restuarant_longitude;
	}
	public void setRestuarant_longitude(double restuarant_longitude) {
		this.restuarant_longitude = restuarant_longitude;
	}
	 
	public void setRestuarant_name(String restuarant_name) {
		this.restuarant_name = restuarant_name;
	}
	public String getRestuarant_name() {
		return restuarant_name;
	}
	public void setRestuarant_distance(String restuarant_distance) {
		this.restuarant_distance = restuarant_distance;
	}
	public String getRestuarant_distance() {
		return restuarant_distance;
	}
	public void setRestuarant_cityState(String restuarant_cityState) {
		this.restuarant_cityState = restuarant_cityState;
	}
	public String getRestuarant_cityState() {
		return restuarant_cityState;
	}
	public void setRestuarant_image(String restuarant_image) {
		this.restuarant_image = restuarant_image;
	}
	public String getRestuarant_image() {
		return restuarant_image;
	}
	public void setRestuarant_phonenumber(String restuarant_phonenumber) {
		this.restuarant_phonenumber = restuarant_phonenumber;
	}
	public String getRestuarant_phonenumber() {
		return restuarant_phonenumber;
	}
	public void setRestuarant_website(String restuarant_website) {
		this.restuarant_website = restuarant_website;
	}
	public String getRestuarant_website() {
		return restuarant_website;
	}
	public void setRestuarant_rating(double restuarant_rating) {
		this.restuarant_rating = restuarant_rating;
	}
	public double getRestuarant_rating() {
		return restuarant_rating;
	}
	public void setRestuarant_type(String restuarant_type) {
		this.restuarant_type = restuarant_type;
	}
	public String getRestuarant_type() {
		return restuarant_type;
	}
	public void setRestuarant_price(double restuarant_price) {
		this.restuarant_price = restuarant_price;
	}
	public double getRestuarant_price() {
		return restuarant_price;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(restuarant_yelp_id);
		dest.writeString(restuarant_name);
		dest.writeString(restuarant_cityState);
		dest.writeString(restuarant_distance);
		dest.writeString(restuarant_image);
		dest.writeString(restuarant_phonenumber);
		dest.writeString(restuarant_website);
		 dest.writeDouble(restuarant_rating);
		 dest.writeString(restuarant_type);
		 dest.writeDouble(restuarant_price);
		 dest.writeDouble(restuarant_latitude);
		 dest.writeDouble(restuarant_longitude);
		 dest.writeString(restuarant_address);
		 dest.writeString(restuarant_noofreviews);
		 dest.writeString(restuarant_categories); 
		
		
	}
	/**
	 * It will be required during un-marshaling data stored in a Parcel
	 * @author 
	 */
	public static final Parcelable.Creator<Restuarant> CREATOR = new Parcelable.Creator<Restuarant>() {
        public Restuarant createFromParcel(Parcel in) {
            return new Restuarant(in);
        }

        public Restuarant[] newArray(int size) {
            return new Restuarant[size];
        }
    };

	
	public Restuarant(Parcel source){
        /*
         * Reconstruct from the Parcel
         */
        //Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
		restuarant_yelp_id = source.readString();
		restuarant_name = source.readString();
		restuarant_cityState = source.readString();
		restuarant_distance = source.readString();
		restuarant_image = source.readString();
		restuarant_phonenumber = source.readString();
		restuarant_website = source.readString();
		restuarant_rating = source.readDouble();
		restuarant_type = source.readString();
		restuarant_price = source.readDouble();
		restuarant_latitude = source.readDouble();
		restuarant_longitude = source.readDouble();
		restuarant_address = source.readString();
		restuarant_noofreviews = source.readString();
		restuarant_categories = source.readString(); 
                
  }
	public Restuarant() {
		// TODO Auto-generated constructor stub
	}
	 

}
