package com.example.customratingbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.AlertDialog;
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
import android.widget.Toast;

public class ResultCardAdapter extends ArrayAdapter<Restuarant> {

	private final Context context;
	private final Restuarant[] values;
	//private ArrayList<Restuarant> resultsList;

	
	
	public ResultCardAdapter(Context context,Restuarant[] objects) {
		super(context, R.layout.resultlist, objects);
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
        
		View row = inflater.inflate(R.layout.resultcard, null);
		
		 //RelativeLayout infoLayout = (RelativeLayout) row.findViewById(R.id.resultCardRelativeLayout);
		 //AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.MATCH_PARENT);
		 //params.leftMargin = leftMargin;
	     //params.rightMargin = rightMargin;

	     //infoLayout.setLayoutParams(params);
		 
		 
	      
		TextView name=(TextView)row.findViewById(R.id.resultCardname);
		TextView address=(TextView)row.findViewById(R.id.resultCardaddress);
		TextView distance=(TextView)row.findViewById(R.id.resultCarddistance);
		TextView phonenumber=(TextView)row.findViewById(R.id.resultCardPhonenumber);
		TextView review_count=(TextView)row.findViewById(R.id.resultCardNoOfReviews);
		RatingBar ratings=(RatingBar)row.findViewById(R.id.resultCardratingsBar);
		ImageView image=(ImageView)row.findViewById(R.id.resultCardImage);
		
		Restuarant restuarant=values[position];
		if(restuarant!=null){
		Bitmap image_restuarant=fetchImage(restuarant.getRestuarant_image());
		if(image_restuarant!=null)
		image.setImageBitmap(image_restuarant);
		name.setText(restuarant.getRestuarant_name());
		double distanceInMiles=Math.round(Double.parseDouble(restuarant.getRestuarant_distance()) * 0.00062137);
		distance.setText("Distance : "+distanceInMiles+" miles");
		address.setText(restuarant.getRestuarant_address());
		phonenumber.setText("PhoneNumber : "+restuarant.getRestuarant_phonenumber());
		review_count.setText("("+restuarant.getRestuarant_noofreviews()+")");
		
		ratings.setRating((float)restuarant.getRestuarant_rating());
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
		
		
		}
		//return super.getView(position, convertView, parent);
		
		return row;
		
	}
	private Bitmap fetchImage( String urlstring )
	 {
		    try
		    {
		        URL url;
		        if(urlstring==null){
		        	return null;
		        }
		        url = new URL( urlstring );

		        HttpURLConnection c = ( HttpURLConnection ) url.openConnection();
		        c.setDoInput( true );
		        c.connect();
		        InputStream is = c.getInputStream();
		        Bitmap img;
		        img = BitmapFactory.decodeStream( is );
		        return getRoundedCornerBitmap(img);
		    }
		    catch ( MalformedURLException e )
		    {
		        Log.d( "RemoteImageHandler", "fetchImage passed invalid URL: " + urlstring );
		    }
		    catch ( IOException e )
		    {
		        Log.d( "RemoteImageHandler", "fetchImage IO exception: " + e );
		    }
		    return null;
		}


	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	        bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);
	
	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	    final float roundPx = 12;
	
	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	
	    return output;
  }


	
}