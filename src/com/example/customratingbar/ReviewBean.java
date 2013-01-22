package com.example.customratingbar;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewBean implements Parcelable {

	private String reviews;
	private int reviewCount;
	private String reviewerName;
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(reviewerName);
		dest.writeInt(reviewCount);
		dest.writeString(reviews);
		
	}
	/**
	 * It will be required during un-marshaling data stored in a Parcel
	 * @author 
	 */
	public static final Parcelable.Creator<ReviewBean> CREATOR = new Parcelable.Creator<ReviewBean>() {
        public ReviewBean createFromParcel(Parcel in) {
            return new ReviewBean(in);
        }

        public ReviewBean[] newArray(int size) {
            return new ReviewBean[size];
        }
    };

	
	public ReviewBean(Parcel source){
        /*
         * Reconstruct from the Parcel
         */
        //Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
		reviewerName=source.readString();
		reviewCount=source.readInt();
		reviews=source.readString();
  }
	public ReviewBean() {
		// TODO Auto-generated constructor stub
	}
	
}


