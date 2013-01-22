package com.example.customratingbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ReviewCardAdapter extends ArrayAdapter<ReviewBean> {

	private final Context context;
	private final ReviewBean[] values;
	//private ArrayList<Restuarant> resultsList;

	
	
	public ReviewCardAdapter(Context context,ReviewBean[] objects) {
		super(context, R.layout.selectedcard, objects);
		this.context=context;
		//this.resultsList=objects;
		this.values=objects;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		int leftMargin = 50;
        int rightMargin = 50;
        
		View row = inflater.inflate(R.layout.reviewcard, null);
		
		 //RelativeLayout infoLayout = (RelativeLayout) row.findViewById(R.id.resultCardRelativeLayout);
		 //AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.MATCH_PARENT);
		 //params.leftMargin = leftMargin;
	     //params.rightMargin = rightMargin;

	     //infoLayout.setLayoutParams(params);
		 
		 
	      
		TextView name=(TextView)row.findViewById(R.id.review_reviewer_name);
		TextView review=(TextView)row.findViewById(R.id.review_review_text);
		RatingBar ratings=(RatingBar)row.findViewById(R.id.review_review_rating);
		/*TextView distance=(TextView)row.findViewById(R.id.resultCarddistance);
		TextView phonenumber=(TextView)row.findViewById(R.id.resultCardPhonenumber);
		TextView review_count=(TextView)row.findViewById(R.id.resultCardNoOfReviews);
		RatingBar ratings=(RatingBar)row.findViewById(R.id.resultCardratingsBar);
		ImageView image=(ImageView)row.findViewById(R.id.resultCardImage);*/
		
		ReviewBean review_data=values[position];
		
		if(review_data!=null){
			name.setText(review_data.getReviewerName());
			review.setText(review_data.getReviews()+"\"");
			ratings.setRating((float)review_data.getReviewCount());
		}
		
		
		
			
		/*row.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 
				//  setContentView(R.layout.selectedcard);
			        
			        
			   //   startActivity(new Intent(getApplicationContext(), SelectedCard.class));

			}
		});*/
		/*ArrayList<ReviewBean> reviews=restuarant.getRestuarant_reviews();
		Iterator<ReviewBean> review_iterator=reviews.iterator();
		
		while(review_iterator.hasNext()){
			
		}*/
		
		
		
		//return super.getView(position, convertView, parent);
		
		return row;
		
	}


	
}