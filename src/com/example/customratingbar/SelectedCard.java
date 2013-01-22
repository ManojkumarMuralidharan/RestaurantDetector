package com.example.customratingbar;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;

import org.json.JSONException;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class SelectedCard extends FragmentActivity {

	Location mCurrentLocation;
	int  mCheck_Price;
	int  mCheck_Proximity;
	int  mCheck_Ratings;
	String mSearchString;
	
	
	
	public int getmCheck_Price() {
		return mCheck_Price;
	}

	public void setmCheck_Price(int mCheck_Price) {
		this.mCheck_Price = mCheck_Price;
	}

	public int getmCheck_Proximity() {
		return mCheck_Proximity;
	}

	public void setmCheck_Proximity(int mCheck_Proximity) {
		this.mCheck_Proximity = mCheck_Proximity;
	}

	public int getmCheck_Ratings() {
		return mCheck_Ratings;
	}

	public void setmCheck_Ratings(int mCheck_Ratings) {
		this.mCheck_Ratings = mCheck_Ratings;
	}

	public String getmSearchString() {
		return mSearchString;
	}

	public void setmSearchString(String mSearchString) {
		this.mSearchString = mSearchString;
	}

	public Location getmCurrentLocation() {
		return mCurrentLocation;
	}

	public void setmCurrentLocation(Location mCurrentLocation) {
		this.mCurrentLocation = mCurrentLocation;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.selectedcard);
		ActionBar actionBar = getActionBar();
		  // add the custom view to the action bar
		
		 actionBar.setCustomView(R.layout.empty_actionbar_view);
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		  | ActionBar.DISPLAY_SHOW_HOME );
		 actionBar.setDisplayHomeAsUpEnabled(true);
		  TextView activityLabel=(TextView)findViewById(R.id.activityId);
		
		
		 
		 
		 Bundle extras = getIntent().getExtras();
		 if(extras!=null){
			 Restuarant selectedRestuarant=(Restuarant)extras.get("resturantDetails");
			  activityLabel.setText(selectedRestuarant.getRestuarant_name().toUpperCase()+" Info");
			 call_for_reviews(selectedRestuarant.getRestuarant_yelp_id());
			 
			 ImageView image=(ImageView)findViewById(R.id.selected_resutcard_Image);
			 Bitmap image_restuarant=ResultCardAdapter.getRoundedCornerBitmap(fetchImage(selectedRestuarant.getRestuarant_image()));
			 image.setImageBitmap(image_restuarant);
			 
//			 ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map)).setUserVisibleHint(false);
			 mCheck_Price=extras.getInt("price");
		     mCheck_Proximity=extras.getInt("proximity");
		     mCheck_Ratings=extras.getInt("ratings");
		     mSearchString=extras.getString("search");
			 
			IntentLocation location = (IntentLocation)extras.get("location");
		    mCurrentLocation=new Location(location.getmProvider());
		    mCurrentLocation.setLatitude(location.getmLatitude());
		    mCurrentLocation.setLongitude(location.getmLongitude());
		    
			 
			 TextView name=(TextView)findViewById(R.id.selected_resutcard_name);
			 TextView address=(TextView)findViewById(R.id.selected_resutcard_address);
			 TextView distance=(TextView)findViewById(R.id.selected_resutcard_distance);
			 TextView phonenumber=(TextView)findViewById(R.id.selected_resutcard_phone);
				//TextView review_count=(TextView)row.findViewById(R.id.resultCardNoOfReviews);
			 RatingBar ratings=(RatingBar)findViewById(R.id.selected_resutcard_ratings);
			 TextView website=(TextView)findViewById(R.id.selected_resutcard_website);
			
			 ArrayList<ReviewBean> review_list = new ArrayList<ReviewBean>();
			 review_list = call_for_reviews(selectedRestuarant.getRestuarant_yelp_id());
			 ListIterator<ReviewBean> itr= review_list.listIterator();
			 ReviewBean[] review_array=new ReviewBean[review_list.size()];
			 int index=0;
			 while(itr.hasNext()){
				 review_array[index++]=itr.next();
			 }
			 
			 
			 
			ListView customList=(ListView)findViewById(R.id.review_list);
			ListAdapter adapter=new ReviewCardAdapter(this, review_array);
		    customList.setAdapter(adapter);
		    customList.setDivider(this.getResources().getDrawable(R.drawable.transperent_color));
		    customList.setDividerHeight(10);
		    
		    ToggleButton reviewListToggle = (ToggleButton)findViewById(R.id.selected_resutcard_reviews_ToggleButton);
		    reviewListToggle.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ListView customList=(ListView)findViewById(R.id.review_list);

					if(v.getId()==R.id.selected_resutcard_reviews_ToggleButton){
						if(((ToggleButton)findViewById(R.id.selected_resutcard_reviews_ToggleButton)).isChecked()){
							customList.setVisibility(View.VISIBLE);
						}else{
							customList.setVisibility(View.GONE);
							RelativeLayout reviewListRelativeLayout = (RelativeLayout) findViewById(R.id.review_list_relativelayout);
							RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//							params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
							//reviewListRelativeLayout.setLayoutParams(params);
							
						}
					}
					
				}
			});
	/*	    ToggleButton directionsListToggle = (ToggleButton)findViewById(R.id.selected_resutcard_directions_ToggleButton);
		    directionsListToggle.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SupportMapFragment customList= (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map);
					
					if(v.getId()==R.id.selected_resutcard_directions_ToggleButton){
						if(((ToggleButton)findViewById(R.id.selected_resutcard_directions_ToggleButton)).isChecked()){
							customList.setUserVisibleHint(true);
							//setVisibility(View.VISIBLE);
						}else{
							customList.setUserVisibleHint(false);
							//RelativeLayout reviewListRelativeLayout = (RelativeLayout) findViewById(R.id.review_list_relativelayout);
							//RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//							params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
							//reviewListRelativeLayout.setLayoutParams(params);
							
						}
					}
					
				}
			});*/
		    
		    
				
			 name.setText(selectedRestuarant.getRestuarant_name());
			 address.setText(selectedRestuarant.getRestuarant_address());
			 website.setText(selectedRestuarant.getRestuarant_website());
			 double distanceInMiles=Math.round(Double.parseDouble(selectedRestuarant.getRestuarant_distance()) * 0.00062137);
			 distance.setText("Distance : "+distanceInMiles+" miles");
			 phonenumber.setText("PhoneNumber : "+selectedRestuarant.getRestuarant_phonenumber());
			 ratings.setRating((float)selectedRestuarant.getRestuarant_rating());
			 
			GoogleMap map = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map))
		               .getMap();
			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			map.addMarker(new MarkerOptions().position(new LatLng(selectedRestuarant.getRestuarant_latitude(), selectedRestuarant.getRestuarant_longitude())).title("Marker"));
			
			map.setMyLocationEnabled(true);
			   // ... get a map.
			   // Add a thin red line from London to New York.
			 Polyline line = map.addPolyline(new PolylineOptions()
			       .add(new LatLng(selectedRestuarant.getRestuarant_latitude(), selectedRestuarant.getRestuarant_longitude()), new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude()))			       
			       .width(5)
			       .color(Color.RED));
			
			//map.setLocationSource(new LocationSource());
			//map.animateCamera(CameraUpdateFactory.zoomIn());
			LatLng selectedRestuarantLatLng = new LatLng(selectedRestuarant.getRestuarant_latitude(), selectedRestuarant.getRestuarant_longitude());
			//map.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedRestuarantLatLng, 12));
			CameraPosition cameraPosition = new CameraPosition.Builder()
		    .target(selectedRestuarantLatLng)      // Sets the center of the map to Mountain View
		    .zoom(12)                   // Sets the zoom
		    .bearing(90)                // Sets the orientation of the camera to east
		    .tilt(30)        		    // Sets the tilt of the camera to 30 degrees
		    .build(); 
			map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			//map.setMyLocationEnabled(true);	 
		 }else{
			 
			 GoogleMap map = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map))
		               .getMap();
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			 
		 }
		 
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
	   // inflater.inflate(R.menu.mainmenu, menu);
	    //return true;
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		 if(item.getItemId()==R.id.menuitem1){
			    Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT).show();
			      
		 }else if(item.getItemId()==R.id.menuitem2){
			  Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT).show();
		      Intent intent = new Intent(Intent.ACTION_MAIN);
		      intent.addCategory(Intent.CATEGORY_HOME);
		      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		      startActivity(intent);
		      
		 }else if(item.getItemId()==android.R.id.home){
			 Intent intent = new Intent(this,ResultListActivity.class);
			 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			 intent.putExtra("price", this.getmCheck_Price());
		     intent.putExtra("proximity", this.getmCheck_Proximity());
		     intent.putExtra("ratings", this.getmCheck_Ratings());
		     intent.putExtra("search", this.getmSearchString());
		     IntentLocation location = new IntentLocation();
		     location.setmLatitude(this.getmCurrentLocation().getLatitude());
		     location.setmLongitude(this.getmCurrentLocation().getLongitude());
		     intent.putExtra("location", location);
		    	//mCurrentLocation=new Location(location.getmProvider());
		    	//mCurrentLocation.setLatitude(location.getmLatitude());
		     
			 startActivity(intent);
	         return true;
		     //Nothing to do
		 }

		    return true;	
	}
	private Bitmap fetchImage( String urlstring )
	 {
		    try
		    {
		        URL url;
		        url = new URL( urlstring );

		        HttpURLConnection c = ( HttpURLConnection ) url.openConnection();
		        c.setDoInput( true );
		        c.connect();
		        InputStream is = c.getInputStream();
		        Bitmap img;
		        img = BitmapFactory.decodeStream( is );
		        return img;
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
	public ArrayList<ReviewBean> call_for_reviews(String id){
		 APIStaticKeys api_keys = new APIStaticKeys();
		    
		    Yelp yelp = new Yelp(api_keys.getYelpConsumerKey(), api_keys.getYelpConsumerSecret(), 
		    		api_keys.getYelpToken(), api_keys.getYelpTokenSecret());
	/*	    if(mSearchString==null){
		    	mSearchString="hotels";
		    }*/
		    String response;
	    	response= yelp.searchBussiness(id);
	    	
	    	YelpParser yParser = new YelpParser();
		    yParser.setResponse(response);
		    try {
				return yParser.parseforReviews();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Do whatever you want with the error, like throw a Toast error report
			}
		    return new ArrayList<ReviewBean>();
		
	}

	public void alert(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
	}

}
