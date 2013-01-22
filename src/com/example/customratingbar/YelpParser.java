package com.example.customratingbar;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.Toast;

/**
 * This class is used to parse Yelp's response
 * 
 * @author myandroidlife.wordpress.com
 *
 */

public class YelpParser {
	
	private String yelp_response;
	private Bundle yelp_bundle = new Bundle();
	private ArrayList<String> keys = new ArrayList<String>();
	
	/**
	 * Sets Yelp's response for this class
	 * @param response
	 */
	public void setResponse(String response){yelp_response = response;}
	
	/**
	 * Returns the set Yelp response
	 * @return string yelp_response
	 */
	public String getResponse(){return yelp_response;}

	/**
	 * Parse's yelp's response for the business name; mobile url; and ratings url.
	 * Mobile url and ratings url is separated by " ,,, "
	 * @sets yelp_bundle(key = business name)
	 * @sets keys arraylist with business name
	 * @throws JSONException
	 */
	public ArrayList<ReviewBean> parseforReviews() throws JSONException{
		JSONObject o1 = new JSONObject(yelp_response);
		//JSONArray businesses = o1.getJSONArray("businesses");

			
			JSONArray reviewsArray=(JSONArray)(o1.get("reviews"));
			
			//JSONArray categories=(JSONArray)(businesses.getJSONObject(i).get("categories"));
			//String address=o2.get("display_address").toString();
			//StringBuffer categories_full=new StringBuffer();
			
			ArrayList<ReviewBean> reviewsData=new ArrayList<ReviewBean>();
			for(int reviewCount = 0; reviewCount < reviewsArray.length(); reviewCount++){
				JSONObject c = reviewsArray.getJSONObject(reviewCount);
				ReviewBean review=new ReviewBean();
				JSONObject userDeatils=(JSONObject)c.get("user");
				review.setReviewCount(Integer.parseInt(c.getString("rating")));
				review.setReviewerName(userDeatils.getString("name"));
				review.setReviews(c.getString("excerpt"));
				reviewsData.add(review);
			}
			
			
			return reviewsData;
             
             
			//tmpString = address_full+businesses.getJSONObject(i).get("name").toString() + " ,,, " +
			//businesses.getJSONObject(i).get("rating").toString()+" ,,, "+businesses.getJSONObject(i).get("distance").toString()+",,,";
			//Dictionary<String, String> values = (Dictionary<String, String>)businesses.getJSONObject(i).get("location");
			//keys.add(businesses.getJSONObject(i).get("name").toString());
			//yelp_bundle.



		
	}
	
	/**
	 * Parse's yelp's response for the business name; mobile url; and ratings url.
	 * Mobile url and ratings url is separated by " ,,, "
	 * @sets yelp_bundle(key = business name)
	 * @sets keys arraylist with business name
	 * @throws JSONException
	 */
	public void parseBusiness() throws JSONException{
		JSONObject o1 = new JSONObject(yelp_response);
		JSONArray businesses = o1.getJSONArray("businesses");
		
		
		
		for (int i = 0; businesses.length() > i; i++){
			Bundle details=new Bundle();
			JSONObject o2=(JSONObject)(businesses.getJSONObject(i).get("location"));
			JSONObject co_ordinates=(JSONObject)(o2.get("coordinate"));
			
			
			//JSONArray reviewsArray=(JSONArray)(businesses.getJSONObject(i).get("reviews"));
			
			//JSONArray categories=(JSONArray)(businesses.getJSONObject(i).get("categories"));
			//String address=o2.get("display_address").toString();
			StringBuffer address_full=new StringBuffer();
			//StringBuffer categories_full=new StringBuffer();
			
			/*ArrayList<ReviewBean> reviewsData=new ArrayList<ReviewBean>();
			for(int reviewCount = 0; reviewCount < reviewsArray.length(); reviewCount++){
				JSONObject c = reviewsArray.getJSONObject(reviewCount);
				ReviewBean review=new ReviewBean();
				JSONObject userDeatils=(JSONObject)c.get("user");
				review.setReviewCount(Integer.parseInt(c.getString("rating")));
				review.setReviewerName(userDeatils.getString("name"));
				review.setReviews(c.getString("excerpt"));
				reviewsData.add(review);
			}*/
			
			
			
			JSONArray addressList=(JSONArray)o2.get("display_address");
			
			
			
			
			 String[]  number  = new String[addressList.length()];
			 //JSONArray[] categories_list=new JSONArray[categories.length()];

             for(int z=0;z< addressList.length();z++)
              {
                         
                         number [z] = addressList.getString(z);
                         if(number [z]!=""||number [z]!=" ")
                         address_full.append(number[z]+"\n");
               }
             /*
             for(int z=0;z< categories.length();z++)
             {
            	 //JSONArray addressList_temp            	 
            	 categories_list[z]=(JSONArray)categories.get(z);
            	 String[]  cat  = new String[categories_list[z].length()];
            	 for(int g=0;g< categories_list[z].length();g++)
            		 
                 {
                            
                            cat [g] = categories_list[z].getString(g);
                            categories_full.append(cat[g]+",");
                  }
            	 //categories_full.append(categories_list[z]+"\n");
                        //number [z] = addressList.getString(z);
                        //address_full.append(number[z]+"\n");
              }*/
             
    //         alert("Categories:"+categories_full);

			//JSONObject o3=(JSONObject)(businesses.getJSONObject(i).get("location.coordinate"));
			//String latitude=o3.get("latitude").toString();
			//String longitude=o3.get("longitude").toString();
			//String json = ((Dictionary<String, String>)businesses.getJSONObject(i).get("location")).get("address");
             details.putString("id",businesses.getJSONObject(i).get("id").toString());
             details.putString("address", address_full.toString());
             details.putString("name", businesses.getJSONObject(i).get("name").toString());
             details.putString("rating", businesses.getJSONObject(i).get("rating").toString());
             details.putString("distance", businesses.getJSONObject(i).get("distance").toString());
             details.putString("stateCode",(String) o2.get("state_code"));
             details.putString("phonenumber",businesses.getJSONObject(i).get("display_phone").toString());
             details.putString("url",businesses.getJSONObject(i).get("url").toString());
             details.putString("imageurl",businesses.getJSONObject(i).get("image_url").toString());
             details.putString("noofreviews", businesses.getJSONObject(i).get("review_count").toString());
             details.putString("latitude", co_ordinates.getDouble("latitude")+"");
             details.putString("longitude",co_ordinates.getDouble("longitude")+"");
             details.putString("website",businesses.getJSONObject(i).get("mobile_url").toString());
             //details.putString("categories", categories_full.toString());
           //  details.putParcelableArrayList("reviews",reviewsData );
             
			//tmpString = address_full+businesses.getJSONObject(i).get("name").toString() + " ,,, " +
			//businesses.getJSONObject(i).get("rating").toString()+" ,,, "+businesses.getJSONObject(i).get("distance").toString()+",,,";
			//Dictionary<String, String> values = (Dictionary<String, String>)businesses.getJSONObject(i).get("location");
			keys.add(businesses.getJSONObject(i).get("name").toString());
			//yelp_bundle.
			yelp_bundle.putBundle(businesses.getJSONObject(i).get("name").toString(), details);
		}
	}
	
	
	
	
	/**
	 * Gets the business name, assuming you supply the bundle, key, and int
	 * In reality, all it does is pull myKey.get(i), but I added the myBundle
	 * param to try and force all information to be there.
	 * @param myBundle
	 * @param myKey
	 * @param i
	 * @return myKey.get(i)
	 */
	public String getBusinessName(Bundle myBundle, ArrayList<String> myKey, int i){return myKey.get(i);}
	
	/**
	 * This gets the business's name, which is stored in the ArrayList keys, using
	 * this class's stored results.
	 * @param i
	 * @return
	 */
	public String getBusinessName(int i){return keys.get(i);}
	
	/**
	 * This returns the business's mobile URL from myBundle using the key provided at int i
	 * @param myBundle
	 * @param myKey
	 * @param i
	 * @return mobileURL
	 */
	public String getBusinessMobileURL(Bundle myBundle, ArrayList<String> myKey, int i){
		String tmp = myBundle.getString(myKey.get(i));
		int x = tmp.indexOf(" ,,, ");
		String mobileURL = tmp.substring(0, x);
		return mobileURL;
	}
	
	/**
	 * This returns the mobile URL using this class's internally stored variables at int i.
	 * For ease of use I suggest using this method.
	 * 
	 * @param i
	 * @return mobileURL
	 */
	public String getBusinessMobileURL(int i){
		String tmp = yelp_bundle.getString(keys.get(i));
		int x = tmp.indexOf(" ,,, ");
		String mobileURL = tmp.substring(0, x);
		return mobileURL;
	}
	
	/**
	 * This will return the rating URL from the user-supplied Bundle, key, and int i
	 * @param myBundle
	 * @param myKey
	 * @param i
	 * @return ratingURL
	 */
	public String getRatingURL (Bundle myBundle, ArrayList<String> myKey, int i){
		String tmp = myBundle.getString(myKey.get(i));
		int x = tmp.indexOf(" ,,, ") + 5;
		String ratingURL = tmp.substring(x);
		return ratingURL;
	}
	
	/**
	 * This will return the rating URL using this class's internal variables.
	 * I recommend using this method. Int i is for keys.get(i).
	 * @param i
	 * @return ratingURL
	 */
	public String getRatingURL (int i){
		String tmp = yelp_bundle.getString(keys.get(i));
		int x = tmp.indexOf(" ,,, ") + 5;
		String ratingURL = tmp.substring(x);
		return ratingURL;
	}

	/**
	 * Returns the bundle, key is the business name.
	 * @return yelp_bundle
	 */
	public Bundle getYelpBundle(){return yelp_bundle;}
	
	/**
	 * Returns the keys (business names) for the yelpBundle.
	 * @return keys (business names)
	 */
	public ArrayList<String> getBundleKeys(){return keys;}
	
	/**
	 * This will return the keys.size(), and is designed to be used with loops
	 * @return keys.size()
	 */
	public int getBudleKeysSize(){int size = keys.size(); return size; }
}
