package com.example.customratingbar;






import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	RatingBar rb;
	private static final int TWO_MINUTES = 1000 * 60 * 2;
	/*Variables for storing location
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	LocationManager mlocationManager;
	LocationListener mlocationListener;
	Location mcurrentLocation;
	double mLatitude;
	double mLongitude;
	String mlocation;
	String mlocationProvider;
	
	/*
	 * Variables to store filter values 
	 */
	int  mCheck_Price;
	int  mCheck_Proximity;
	double  mCheck_Ratings;
	int  mCheck_Atmosphere;
	
	public String getMlocationProvider() {
		return mlocationProvider;
	}

	public void setMlocationProvider(String locationProvider) {
		this.mlocationProvider = locationProvider;
	}
	public LocationListener getMlocationListener() {
		return mlocationListener;
	}

	public Location getMcurrentLocation() {
		return mcurrentLocation;
	}

	public void setMcurrentLocation(Location mcurrentLocation) {
		this.mcurrentLocation = mcurrentLocation;
	}

	public void setMlocationListener(LocationListener mlocationListener) {
		this.mlocationListener = mlocationListener;
	}
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

	public int getmCheck_Atmosphere() {
		return mCheck_Atmosphere;
	}

	public void setmCheck_Atmosphere(int mCheck_Atmosphere) {
		this.mCheck_Atmosphere = mCheck_Atmosphere;
	}

	public String getMlocation() {
		return mlocation;
	}

	public void setMlocation(String location) {
		mlocation = location;
	}

	
	public LocationManager getMlocationManager() {
		return mlocationManager;
	}



	public void setMlocationManager(LocationManager mlocationManager) {
		this.mlocationManager = mlocationManager;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//set LocationManager
		//this.setMlocationManager((LocationManager) getSystemService(LOCATION_SERVICE));
        //this.getMlocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
		LocationManager locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		this.setMlocationManager(locationManager);
		this.setMlocationProvider(LocationManager.NETWORK_PROVIDER);
		LocationListener locationListener= new LocationListener() {
		    public void onLocationChanged(Location location) {
		        // Called when a new location is found by the network location provider.
		       // makeUseOfNewLocation(location);
		    	if(isBetterLocation(location,mcurrentLocation)){
		    		mcurrentLocation=location;
		    	}
				setmLatitude(mcurrentLocation.getLatitude());
				setmLongitude(mcurrentLocation.getLongitude());
				//alert("CurrentLocation - Latitude:"+getmLatitude()+" Longitude:"+getmLongitude());
		      }

		      public void onStatusChanged(String provider, int status, Bundle extras) {
		    	  
		    	  if(status==LocationProvider.AVAILABLE){
		    		  alert("Location - Locked in");
		    	  }else if(status==LocationProvider.TEMPORARILY_UNAVAILABLE){
		    		  alert("Locking in on your location");
		    	  }else if(status == LocationProvider.OUT_OF_SERVICE){
		    		  alert("GPS Provider out of service");
		    	  }
		    	  
		      }

		      public void onProviderEnabled(String provider) {
		    	  if(!isSameProvider(mlocationProvider,provider)){
		    		  mlocationProvider=provider;
		    	  }
		      }

		      public void onProviderDisabled(String provider) {
		    	// Or use LocationManager.GPS_PROVIDER
		    	  if(mlocationManager!=null)
		    		  mcurrentLocation = mlocationManager.getLastKnownLocation(mlocationProvider);
		    	  else
		    		  alert("Location Not set");
		    	 }
		    };
		this.setMlocationListener(locationListener);
		// Register the listener with the Location Manager to receive location updates
		try{
		this.getMlocationManager().requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		}catch(Exception e){
			alert("NO WiFi GPS found");
		this.getMlocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);	
		}
		
		
		ActionBar actionBar = getActionBar();
		  // add the custom view to the action bar
		
		  actionBar.setCustomView(R.layout.actionbar_view);
		  actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		   | ActionBar.DISPLAY_SHOW_HOME );
		  
		  
		  EditText search_field=(EditText)findViewById(R.id.search_string_food);
		  search_field.setSelected(false);
		  TextView activityLabel=(TextView)findViewById(R.id.activityId);
		  activityLabel.setText("Home");
		  
		  Bundle extras= getIntent().getExtras(); ;
		  if(extras != null){
		  TextView userName = (TextView)findViewById(R.id.user_welcome_name);
		  String input=extras.getString("userName");
		  String user_name_text=input.substring(0, 1).toUpperCase() + input.substring(1);
		  userName.setText(user_name_text);
		  
		  }
		  
		
		  final ImageButton button = (ImageButton) findViewById(R.id.search_food);
	        button.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	TextView searchString= (TextView)findViewById(R.id.search_string_food);
	        	Toast.makeText(getApplicationContext(),searchString.getText(),Toast.LENGTH_LONG).show();
	        	processQuery(searchString.getText().toString());
	        	
	        }});
	        
	        /*ImageButton contextmenu = (ImageButton)findViewById(R.id.actionbar_popup_button);
	        contextmenu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					showPopup(v);
				}
			});*/
	        
	        
	        
	        OnClickListener parameter_listener = new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					  if(v.getId() == R.id.distanceLabel){
				    	  if(((ToggleButton)findViewById(R.id.distanceLabel)).isChecked()){
					    	  ((ToggleButton)findViewById(R.id.milebutton1)).setVisibility(View.VISIBLE);
					          ((ToggleButton)findViewById(R.id.milebutton2)).setVisibility(View.VISIBLE);
					    	  ((ToggleButton)findViewById(R.id.milebutton3)).setVisibility(View.VISIBLE);
					    	  ((ToggleButton)findViewById(R.id.milebutton5)).setVisibility(View.VISIBLE);
					    	  ((ToggleButton)findViewById(R.id.milebutton10)).setVisibility(View.VISIBLE);
					    	  ((RelativeLayout)findViewById(R.id.relativeLayoutDistanceCard)).setVisibility(View.VISIBLE);
					    	  TextView text = (TextView)findViewById(R.id.distanceLabelCard);
					    	  setmCheck_Proximity(0);
					    	  //if(getmCheck_Price()!=0)
					    	  text.setText("Distance filter active");
					    	   
				    	  }else{
				    		  Toast.makeText(getApplicationContext(),"Inside Else",Toast.LENGTH_LONG).show();
				    		  ((ToggleButton)findViewById(R.id.milebutton1)).setVisibility(View.INVISIBLE);
					          ((ToggleButton)findViewById(R.id.milebutton2)).setVisibility(View.INVISIBLE);
					    	  ((ToggleButton)findViewById(R.id.milebutton3)).setVisibility(View.INVISIBLE);
					    	  ((ToggleButton)findViewById(R.id.milebutton5)).setVisibility(View.INVISIBLE);
					    	  ((ToggleButton)findViewById(R.id.milebutton10)).setVisibility(View.INVISIBLE);
					    	  ((RelativeLayout)findViewById(R.id.relativeLayoutDistanceCard)).setVisibility(View.INVISIBLE);
					    	  TextView text = (TextView)findViewById(R.id.distanceLabelCard);
					    	  text.setText("");
					    	  detactivateToggle(0);
					    	  setmCheck_Proximity(0);
				    	  }
				    	 // Button distance_button = (Button)findViewById(v.getId());
				    	 // distance_button.getBackground().
				    	 // Toast.makeText(getApplicationContext(),distance_button.get,Toast.LENGTH_LONG).show();
				    	 // distance_button.setImageResource(R.drawable.mile2);
				    	  
//				        alert("1");
				    //    break;
					  }else if(v.getId()==R.id.priceLabel){
				    	  RatingBar priceBar=(RatingBar)findViewById(R.id.priceBar);
				    	  if(((ToggleButton)findViewById(R.id.priceLabel)).isChecked()){
				    	  priceBar.setVisibility(View.VISIBLE);
				    	  ((RelativeLayout)findViewById(R.id.relativeLayoutPriceCard)).setVisibility(View.VISIBLE);
				    	  rb=(RatingBar)findViewById(R.id.priceBar);
				    	  TextView text = (TextView)findViewById(R.id.priceLabelCard);
    	        	    	text.setText("Price filter activated");	
					  	  rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){
	
					  	        @Override
					  	        public void onRatingChanged(RatingBar ratingBar, float rating,
					  	                boolean fromUser) {
					  	            // TODO Auto-generated method stub
					  	        	    setmCheck_Price((int) rating);
					  	        	    TextView text = (TextView)findViewById(R.id.priceLabelCard);
					  	        	    if(rating!=0)
					  	        	    	text.setText("Search for restuarant with price range of  0 to "+(int) rating+" $");
					  	        	    else
					  	        	    	text.setText("Price filter active");	
					  	              
					  	        	    //Toast.makeText(getApplicationContext(),Float.toString(rating),Toast.LENGTH_LONG).show();
	
					  	        }
	
		     	  	      });
				    	  }else{
				    	  priceBar.setVisibility(View.INVISIBLE);
				    	  priceBar.setRating(0);
				    	  setmCheck_Price(0);
				    	  ((RelativeLayout)findViewById(R.id.relativeLayoutPriceCard)).setVisibility(View.INVISIBLE);
		  	        	  TextView text = (TextView)findViewById(R.id.priceLabelCard);
				    	  text.setText("");
				    	  }
//				    	  alert("2");
				     //   break;
					  }else if(v.getId()==R.id.ratingsLabel){
				    	  RatingBar ratingsBar=(RatingBar)findViewById(R.id.ratingsBar);
				    	  if(((ToggleButton)findViewById(R.id.ratingsLabel)).isChecked()){
				    		  ratingsBar.setVisibility(View.VISIBLE);
				    		  ((RelativeLayout)findViewById(R.id.relativeLayoutRatingsCard)).setVisibility(View.VISIBLE);
					    	  rb=(RatingBar)findViewById(R.id.ratingsBar);
					    	  TextView text = (TextView)findViewById(R.id.ratingsLabelCard);
			  	        	  text.setText("Rating filter active");  
			  	        	  
						  	  rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){
		
						  	        @Override
						  	        public void onRatingChanged(RatingBar ratingBar, float rating,
						  	                boolean fromUser) {
						  	            // TODO Auto-generated method stub
						  	        	    setmCheck_Ratings(rating);
						  	        	  TextView text = (TextView)findViewById(R.id.ratingsLabelCard);
						  	        	  if(rating!=0)
								    	  text.setText("Search for restuarant with ratings atleast "+rating+"");
						  	        	  else{
						  	        		text.setText("Rating filter active");  
						  	        	  }
						  	              
						  	                //Toast.makeText(getApplicationContext(),Float.toString(rating),Toast.LENGTH_LONG).show();
		
						  	        }
		
			     	  	      });
				    		  
					    	  }else{
					    	  ratingsBar.setVisibility(View.INVISIBLE);
					    	  ratingsBar.setRating(0);
					    	  setmCheck_Ratings(0);
					    	  ((RelativeLayout)findViewById(R.id.relativeLayoutRatingsCard)).setVisibility(View.INVISIBLE);
					    	  TextView text = (TextView)findViewById(R.id.ratingsLabelCard);
					    	  text.setText("");
			  	              
					    	  }
				    	  //alert("3");
				    	//  break;
				      }
					
				}
			};
			
			
			ToggleButton distancebutton= (ToggleButton)findViewById(R.id.distanceLabel);
	        ToggleButton pricebutton= (ToggleButton)findViewById(R.id.priceLabel);
	        ToggleButton ratingsbutton= (ToggleButton)findViewById(R.id.ratingsLabel);
	        
	        distancebutton.setOnClickListener(parameter_listener);
	        pricebutton.setOnClickListener(parameter_listener);
	        ratingsbutton.setOnClickListener(parameter_listener);
	        
	        
	        
	        TextView date=(TextView)findViewById(R.id.date);
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat formatter = new SimpleDateFormat(" d EEE,MMMMM ");
	        String current_time = formatter.format(cal.getTime());
	        date.setText(current_time);
	        
		/*rb=(RatingBar)findViewById(R.id.ratingBar1);

	    rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

	        @Override
	        public void onRatingChanged(RatingBar ratingBar, float rating,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	                Toast.makeText(getApplicationContext(),Float.toString(rating),Toast.LENGTH_LONG).show();

	        }

	    });*/
	}
	
	public void processQuery(String search_food_text){
		Intent myIntent = new Intent(getApplicationContext(), ResultListActivity.class);
		//alert("Price"+this.getmCheck_Price());
        myIntent.putExtra("price", this.getmCheck_Price());
        myIntent.putExtra("proximity", this.getmCheck_Proximity());
        myIntent.putExtra("ratings", this.getmCheck_Ratings());
        IntentLocation location = new IntentLocation();
        location.setmLatitude(this.getMcurrentLocation().getLatitude());
        location.setmLongitude(this.getMcurrentLocation().getLongitude());
        myIntent.putExtra("location",location);
        
        if(this.getMcurrentLocation()==null){
        	alert("Searching but sorry we could not get your location");
        }
        myIntent.putExtra("search",search_food_text);
        //if()
       // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //SharedPreferences prefsPrivate = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        //Editor prefsPrivateEditor = prefs.edit();
        //prefsPrivateEditor.putString("mPrice", mCheck_Price+"");
        //prefsPrivateEditor.putString("mProximity", mCheck_Proximity+"");
        //prefsPrivateEditor.putString("mServiceTime", mCheck_ServiceTime+"");
        //prefsPrivateEditor.putString("mAtmosphere", mCheck_Atmosphere+"");
        //prefsPrivateEditor.commit();
        //String value = prefs. String("price", null);
       // return value == null ? -1 : Integer.valueOf(value);
        startActivity(myIntent);
        finish();
    
	}

	/*public void showPopup(View v){    	
		Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT).show();
    	PopupMenu popupMenu = new PopupMenu(this,v);    	
    //	popupMenu.setOnMenuItemClickListener(this);
    //	popupMenu.setOnDismissListener(this);    	
    	popupMenu.inflate(R.menu.mainmenu);
    	popupMenu.show();
    	popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
			    switch (item.getItemId()) {
			    case R.id.menuitem1:
			      Toast.makeText(getApplicationContext(), "Help 1 selected", Toast.LENGTH_SHORT).show();
			      break;
			    case R.id.menuitem2:
			      Toast.makeText(getApplicationContext(), "Exit selected", Toast.LENGTH_SHORT).show();
			      break;

			    default:
			      break;
			    }

			    return true;

			}
		});
    	
    }*/
	
	public void ButtonOnClick(View v) {
		  detactivateToggle(v.getId());
	      if(v.getId()== R.id.milebutton1){
	    	 // Button distance_button = (Button)findViewById(v.getId());
	    	 // distance_button.getBackground().
	    	 // Toast.makeText(getApplicationContext(),distance_button.get,Toast.LENGTH_LONG).show();
	    	 // distance_button.setImageResource(R.drawable.mile2);
	    	  //((ToggleButton)findViewById(R.id.milebutton10)).setChecked(false);
	    	  setmCheck_Proximity(1);
	        //alert("1");
	    //    break;
	      }else if(v.getId()==R.id.milebutton2){
	      
	    	  //((ToggleButton)findViewById(R.id.milebutton10)).setChecked(false);
	    	  setmCheck_Proximity(2);
	    	  //alert("2");
	      //  break;
	      }else if(v.getId()==R.id.milebutton3){
	    	  //((ToggleButton)findViewById(R.id.milebutton10)).setChecked(false);
	    	  setmCheck_Proximity(3);
	    	  //alert("3");
	    	//  break;
	      }else if(v.getId()==R.id.milebutton5){
	    	  //((ToggleButton)findViewById(R.id.milebutton10)).setChecked(false);
	    	  setmCheck_Proximity(5);
	    	  //alert("5");
	    	 // break;
	      }else if(v.getId()==R.id.milebutton10){
	    	  /*if(((ToggleButton)findViewById(R.id.milebutton1)).isChecked()){
		    	  ((ToggleButton)findViewById(R.id.milebutton1)).setChecked(false);
		          ((ToggleButton)findViewById(R.id.milebutton2)).setChecked(false);
		    	  ((ToggleButton)findViewById(R.id.milebutton3)).setChecked(false);
		    	  ((ToggleButton)findViewById(R.id.milebutton5)).setChecked(false);
		    	 
	    	  }*/
	    	  setmCheck_Proximity(10);
	    	  //alert(getmCheck_Proximity()+"");
	    	  //alert("10");
	    	  //break;
	      }
	      
	      TextView text = (TextView)findViewById(R.id.distanceLabelCard);
    	  //if(getmCheck_Price()!=0)
	      text.setText("Search for restuarant with distance of  0 to "+getmCheck_Proximity()+" miles");
    	  //text.setText("Distance filter activated");
	      
	}
	public void alert(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
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
	    
	    if(item.getItemId() == R.id.menuitem1){
	      Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT).show();
	    //  break;
	    }else if(item.getItemId()== R.id.menuitem2){
	      Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT).show();
	      Intent intent = new Intent(Intent.ACTION_MAIN);
	      intent.addCategory(Intent.CATEGORY_HOME);
	      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	      startActivity(intent);
	    //  break;
	    }else{
	    
	    //  break;
	    }

	    return true;
	  }



	/** Determines whether one Location reading is better than the current Location fix
	  * @param location  The new Location that you want to evaluate
	  * @param currentBestLocation  The current Location fix, to which you want to compare the new one
	  */
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
	    if (currentBestLocation == null) {
	        // A new location is always better than no location
	        return true;
	    }

	    // Check whether the new location fix is newer or older
	    long timeDelta = location.getTime() - currentBestLocation.getTime();
	    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
	    boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
	    boolean isNewer = timeDelta > 0;

	    // If it's been more than two minutes since the current location, use the new location
	    // because the user has likely moved
	    if (isSignificantlyNewer) {
	        return true;
	    // If the new location is more than two minutes older, it must be worse
	    } else if (isSignificantlyOlder) {
	        return false;
	    }

	    // Check whether the new location fix is more or less accurate
	    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
	    boolean isLessAccurate = accuracyDelta > 0;
	    boolean isMoreAccurate = accuracyDelta < 0;
	    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

	    // Check if the old and new location are from the same provider
	    boolean isFromSameProvider = isSameProvider(location.getProvider(),
	            currentBestLocation.getProvider());

	    // Determine location quality using a combination of timeliness and accuracy
	    if (isMoreAccurate) {
	        return true;
	    } else if (isNewer && !isLessAccurate) {
	        return true;
	    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
	        return true;
	    }
	    return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
	    if (provider1 == null) {
	      return provider2 == null;
	    }
	    return provider1.equals(provider2);
	}
	private void detactivateToggle(int id){
		  if(id != R.id.milebutton1){
		    	  ((ToggleButton)findViewById(R.id.milebutton1)).setChecked(false);
		  }
		  if(id!=R.id.milebutton2){
			  	  ((ToggleButton)findViewById(R.id.milebutton2)).setChecked(false);
		  }
		  if(id!=R.id.milebutton3){
		    	  ((ToggleButton)findViewById(R.id.milebutton3)).setChecked(false);
		  }
		  if(id!=R.id.milebutton5){
		    	  ((ToggleButton)findViewById(R.id.milebutton5)).setChecked(false);
		  }if(id!=R.id.milebutton10){
		    	  ((ToggleButton)findViewById(R.id.milebutton10)).setChecked(false);
		   }
	}

}
