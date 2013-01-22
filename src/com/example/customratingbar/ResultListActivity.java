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
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultListActivity extends ListActivity implements OnItemClickListener{


	/*
	 * Variables to store filter values 
	 */
	int  mCheck_Price;
	int  mCheck_Proximity;
	double  mCheck_Ratings;
	Location mCurrentLocation;
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


	public double getmCheck_Ratings() {
		return mCheck_Ratings;
	}


	public void setmCheck_Ratings(double mCheck_Ratings) {
		this.mCheck_Ratings = mCheck_Ratings;
	}


	public Location getmCurrentLocation() {
		return mCurrentLocation;
	}


	public void setmCurrentLocation(Location mCurrentLocation) {
		this.mCurrentLocation = mCurrentLocation;
	}


	public String getmSearchString() {
		return mSearchString;
	}


	public void setmSearchString(String mSearchString) {
		this.mSearchString = mSearchString;
	}


	private ArrayList<Restuarant> resultsList;
	Restuarant objects[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resultsList=new ArrayList<Restuarant>();
		ActionBar actionBar = getActionBar();
		  // add the custom view to the action bar
		
		 actionBar.setCustomView(R.layout.empty_actionbar_view);
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		  | ActionBar.DISPLAY_SHOW_HOME );
		 actionBar.setDisplayHomeAsUpEnabled(true);
		  TextView activityLabel=(TextView)findViewById(R.id.activityId);
		  activityLabel.setText("Search Results");
		
		  
		  Bundle extras = getIntent().getExtras();
	        if(extras == null) {
	        
	        }
	        
	        //setContentView(R.layout.resultlist);
	        //LinearLayout list_relative_layout=(LinearLayout)findViewById(R.id.resultsList);
	        //LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	        //lay.addRule(LinearLayout.);
	        
	        
	        
	        //list_relative_layout.addView(list,lay);
	        setContentView(R.layout.resultlist);
	        //ListView list=(ListView)findViewById(R.id.result_listview);
	        
	        mCheck_Price=extras.getInt("price");
	    	mCheck_Proximity=extras.getInt("proximity");
	    	mCheck_Ratings=extras.getInt("ratings");
	    	IntentLocation location = (IntentLocation)extras.get("location");
	    	mCurrentLocation=new Location(location.getmProvider());
	    	mCurrentLocation.setLatitude(location.getmLatitude());
	    	mCurrentLocation.setLongitude(location.getmLongitude());
	    			
	    	mSearchString=extras.getString("search");
	    	//LinearLayout policyTable = (LinearLayout)findViewById(R.id.resultsList);      
	        //LayoutInflater inflater = getLayoutInflater();
	        //LinearLayout resultList = (LinearLayout)inflater.inflate(R.layout.resultlist, null);
//	    	FragmentManager fragmentManager = getFragmentManager();
//	    	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    	
	    	call_Yelp();
	    	ListIterator<Restuarant> itr= resultsList.listIterator();

	    	objects=new Restuarant[resultsList.size()];
	    	int index=0;
	    	while(itr.hasNext()){
	    		objects[index++]=itr.next();
	    	//	objects[index-1].setRestuarant_reviews(call_for_reviews(objects[index-1].getRestuarant_yelp_id()));
	    	/*	String[] categories=(((Restuarant)objects[index-1]).getRestuarant_categories().split(","));
	    		ArrayList<String> categories_unique=new ArrayList<String>();
	    		for(int k=0;k<categories.length;k++){
	    			if(!categories_unique.contains(categories[k].toLowerCase())){
	    				categories_unique.add(categories[k].toLowerCase());
	    			}
	    		}*/
	    		//alert("Latitude :"+objects[index-1].getRestuarant_latitude()+"Longitude :"+objects[index-1].getRestuarant_longitude());
	    		//alert(categories_unique.toArray().toString());
	    	}
	    	
	    	//ListView customList=(ListView)findViewById(R.id.custom_list);
	    	ListAdapter adapter=new ResultCardAdapter(this, objects);
	    	setListAdapter(adapter);
	    	ListView list=getListView();
	    	LayoutInflater inflator=getLayoutInflater();
	    	
	    	//View emptyListView = inflator.inflate(R.layout.emptylist, null);
	    	//LinearLayout emptyListLayout = (LinearLayout)emptyListView.findViewById(R.id.emptylist_layout);
	    	
	    	
	    	//list.setEmptyView(emptyListView);
	    	//list.setVisibility()
	        list.setDivider(this.getResources().getDrawable(R.drawable.transperent_color));
	        list.setDividerHeight(20);
	        getListView().setOnItemClickListener(this);
	        
	       /* TextView Nodata=(TextView)emptyListLayout.findViewById(R.id.resultlist_nodata);
	        Nodata.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent myIntent = new Intent(getApplicationContext(), SelectedCard.class);
			        startActivity(myIntent);
				}
			});*/
	        /*list.setOnItemSelectedListener(new OnItemSelectedListener(){
	            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id){
	          	  Toast.makeText(ResultListActivity.this,"Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
	            }
	            public void onNothingSelected(AdapterView<?> arg0){
	          	  Toast.makeText(ResultListActivity.this,"Nothing selected", Toast.LENGTH_LONG).show();
	            }
	        });*/
	        
	        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	              @Override
	              public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	            	  Toast.makeText(ResultListActivity.this,"Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
	            	  setContentView(R.layout.selectedcard);
	                  
	                  
	                  startActivity(new Intent(getApplicationContext(), SelectedCard.class));
	                  
	              }
	            });*/
	     
	        
	       //list.setOnItemClickListener(this);
	    	//customList.setAdapter(adapter);
	    	
	    	//list.setAdapter(new ResultCardAdapter(this, objects));
	    	//setListAdapter(new ResultCardAdapter(this, objects));
	    	/*int index=0;
	    	//ListView resultListScollview=(ListView)resultList.findViewById(R.id.scroll_content);
	    	while(itr.hasNext()){
	    		View row = inflater.inflate(R.layout.resultcard, null);
	    		row.setId(index++);
	    		//RelativeLayout row = 
	    		TextView name=(TextView)row.findViewById(R.id.resultCardname);
	    		TextView address=(TextView)row.findViewById(R.id.resultCardaddress);
	    		TextView distance=(TextView)row.findViewById(R.id.resultCarddistance);
	    		RatingBar ratings=(RatingBar)row.findViewById(R.id.resultCardratingsBar);
	    		ImageView image=(ImageView)row.findViewById(R.id.resultCardImage);
	    		
	    		Restuarant restuarant=itr.next();
//	    		ResultCardFragment results=ResultCardFragment.newInstance(index++);
//	    		fragmentTransaction.add(R.id.fragment_content, results);
//	    		fragmentTransaction.commit();
	    		Bitmap image_restuarant=fetchImage(restuarant.getRestuarant_image());
	    		if(image_restuarant!=null)
	    		image.setImageBitmap(image_restuarant);
	    		name.setText(restuarant.getRestuarant_name());
	    		distance.setText(restuarant.getRestuarant_distance());
	    		address.setText(restuarant.getRestuarant_address());
	    		ratings.setRating((float)restuarant.getRestuarant_rating());
	
//	    		results.getmImageView().setImageBitmap(image_restuarant);
//	    		results.getmName().setText(restuarant.getRestuarant_name());
//	    		results.getmDistance().setText(restuarant.getRestuarant_distance());
//	    		results.getmAddress().setText(restuarant.getRestuarant_address());
//	    		results.getmRatingbar().setRating((float)restuarant.getRestuarant_rating());
	    		
	    		policyTable.addView(row);
	    	//	alert(restuarant.getRestuarant_name());
	    	
	    	}
	    	
	    //	fragmentTransaction.commit();*/
	}
	
	
	/*@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        setContentView(R.layout.selectedcard);
        
        
        startActivity(new Intent(getApplicationContext(), SelectedCard.class));
    }*/
	
	public void alert(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
			 Intent intent = new Intent(this,MainActivity.class);
			 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
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
	
	public void call_Yelp(){
		 APIStaticKeys api_keys = new APIStaticKeys();
		    
		    Yelp yelp = new Yelp(api_keys.getYelpConsumerKey(), api_keys.getYelpConsumerSecret(), 
		    		api_keys.getYelpToken(), api_keys.getYelpTokenSecret());
		    if(mSearchString==null){
		    	mSearchString="hotels";
		    }
		    //Toast.makeText(this,mSearch_String,Toast.LENGTH_LONG).show();
		    String response;
		    if(mCurrentLocation!=null){
		    	response= yelp.search(mSearchString, mCurrentLocation.getLatitude() , mCurrentLocation.getLongitude());
		    	//response="nothing";
		    	//alert("Search String:"+mSearchString);
		    	alert(response);
		    }else{
		    	response= yelp.search("Restuarants", 30.361471, -87.164326);
		    }
		    	
		     
		    //String response = yelp.search("Restuarants", 30.361471, -87.164326);
		    YelpParser yParser = new YelpParser();
		    yParser.setResponse(response);
		    try {
				yParser.parseBusiness();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Do whatever you want with the error, like throw a Toast error report
			}
		    
		    int i = 0;

		    Bundle tmpBundle = yParser.getYelpBundle();
		    
		    //Toast.makeText(this, message,Toast.LENGTH_LONG).show();
		    ArrayList<String> tmpKeys = yParser.getBundleKeys();
		    ListIterator<String> itr= tmpKeys.listIterator();
		    int i_c=0;
	    	alert("size:"+tmpKeys.size());
		    while(itr.hasNext()){
		    	i_c++;
		    	String tmp_key=itr.next();
		    	Bundle temp_Bundle=tmpBundle.getBundle(tmp_key);
		    	 //  Toast.makeText(this,temp_Bundle.getString("name")+"\n"+temp_Bundle.getString("address")+"\n"+temp_Bundle.getString("distance")+"\n"+temp_Bundle.getString("rating")+"\n"+temp_Bundle.getString("stateCode")+"\n"+temp_Bundle.getString("phonenumber")+"\n"+temp_Bundle.getString("url"),Toast.LENGTH_LONG).show();
		    	Restuarant results = new Restuarant();
		    	
		    	//alert(temp_Bundle.getString("distance"));
		    	//alert(temp_Bundle.getString("price"));
		    	//int distance =0;//
		    	double distance=(double)mCheck_Proximity;
		    	int price = 0;//Integer.parseInt(temp_Bundle.getString("price"));
		    	//int ratings = 0;
		    	double ratings=Double.parseDouble(temp_Bundle.getString("rating"));
		    	double distanceInMiles=Math.round(Double.parseDouble(temp_Bundle.getString("distance")) * 0.00062137);
		    
		    	if(distance!=0)
		    	if(distance<distanceInMiles){
		    		continue;
		    	}
		    	
		    /*	if(price!=0)
		    	if(price>this.mCheck_Price){
		    		continue;
		    	}*/
		    	alert("Req rating" +this.mCheck_Ratings+ "obtained rating"+ratings);
		    	if(ratings!=0)
		    	if(this.mCheck_Ratings>ratings){
		    		continue;
		    	} 
		    	
		    	results.setRestuarant_name(temp_Bundle.getString("name"));
		    	//alert(temp_Bundle.getString("address"));
		    	/*ArrayList<String> address_bundle=(ArrayList<String>)temp_Bundle.getParcelableArrayList(("location.display_address");
		    	Iterator<String> address_itr=address_bundle.iterator();
		    	String newaddress;
		    	while(address_itr.hasNext()){
		    		newaddress=newaddress+address_itr.next()+"\n";
		    	}*/
		    	results.setRestuarant_yelp_id(temp_Bundle.getString("id"));
		    	results.setRestuarant_address(temp_Bundle.getString("address"));
		    	results.setRestuarant_distance(temp_Bundle.getString("distance"));
		    	
		    //	alert(distanceInMiles+" miles + without rounding"+Math.round(distanceInMiles));
		    	results.setRestuarant_image(temp_Bundle.getString("imageurl"));
		    	results.setRestuarant_phonenumber(temp_Bundle.getString("phonenumber"));
		    	results.setRestuarant_website(temp_Bundle.getString("url"));
		    	results.setRestuarant_rating(ratings);
		    	results.setRestuarant_noofreviews(temp_Bundle.getString("noofreviews"));
		    	results.setRestuarant_categories(temp_Bundle.getString("categories"));
		    	results.setRestuarant_latitude(Double.parseDouble(temp_Bundle.getString("latitude")));
		    	results.setRestuarant_longitude(Double.parseDouble(temp_Bundle.getString("longitude")));
		    	results.setRestuarant_website(temp_Bundle.getString("website"));
		    	//ArrayList<ReviewBean> test=temp_Bundle.getParcelableArrayList("reviews");
		    	//results.setRestuarant_reviews(test);
		    	
		   	   // sr1.setRestuarant_price(Double.parseDouble(temp_Bundle.getString("price")));
		    	results.setRestuarant_type("Mexican");
		   	    resultsList.add(results);
		    }
		    
	}
	
	/**
	 * Obtained code from previous project
     * Finds distance between two coordinate pairs.
     *
     * @param lat1 First latitude in degrees
     * @param lon1 First longitude in degrees
     * @param lat2 Second latitude in degrees
     * @param lon2 Second longitude in degrees
     * @return distance in meters
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {

      final double Radius = 6371 * 1E3; // Earth's mean radius

      double dLat = Math.toRadians(lat2 - lat1);
      double dLon = Math.toRadians(lon2 - lon1);
      double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
          * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

      return Radius * c;
    }


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);
        //setContentView(R.layout.selectedcard);
		
		Restuarant selectedRestuarant=objects[position];
	//	Intent myIntent = new Intent(getApplicationContext(), SelectedCard.class);
		Intent myIntent = new Intent(ResultListActivity.this,SelectedCard.class).putExtra("resturantDetails",selectedRestuarant);
        IntentLocation location = new IntentLocation();
        location.setmLatitude(this.getmCurrentLocation().getLatitude());
        location.setmLongitude(this.getmCurrentLocation().getLongitude());
        myIntent.putExtra("location",location);
        myIntent.putExtra("price", this.getmCheck_Price());
        myIntent.putExtra("proximity", this.getmCheck_Proximity());
        myIntent.putExtra("ratings", this.getmCheck_Ratings());
        myIntent.putExtra("search", this.getmSearchString());
        
//		alert("Selected is "+objects[position].getRestuarant_address());
		//myIntent.putExtra("resturantDetails", selectedRestuarant);
        startActivity(myIntent);
	}

	
	
}
