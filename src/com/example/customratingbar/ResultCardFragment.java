package com.example.customratingbar;

import android.app.Fragment;
import android.database.CursorJoiner.Result;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultCardFragment extends Fragment implements OnClickListener {
	
	
	ImageView mImageView;
	TextView mName;
	TextView mAddress;
	TextView mDistance;
	RatingBar mRatingbar;
	
	
	public static ResultCardFragment newInstance(int index){
		
		ResultCardFragment f = new ResultCardFragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);
		return f;
	}
	
	
	public int getShownIndex() {
		return getArguments().getInt("index", 0);
    
	}
	
	public ImageView getmImageView() {
		return mImageView;
	}

	public void setmImageView(ImageView mImageView) {
		this.mImageView = mImageView;
	}

	public TextView getmName() {
		return mName;
	}

	public void setmName(TextView mName) {
		this.mName = mName;
	}

	public TextView getmAddress() {
		return mAddress;
	}

	public void setmAddress(TextView mAddress) {
		this.mAddress = mAddress;
	}

	public TextView getmDistance() {
		return mDistance;
	}

	public void setmDistance(TextView mDistance) {
		this.mDistance = mDistance;
	}

	public RatingBar getmRatingbar() {
		return mRatingbar;
	}

	public void setmRatingbar(RatingBar mRatingbar) {
		this.mRatingbar = mRatingbar;
	}


	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        // onCreateView() is a lifecycle event that is unique to a Fragment. This is called when Android
        // needs the layout for this Fragment. The call to LayoutInflater::inflate() simply takes the layout
        // ID for the layout file, the parent view that will hold the layout, and an option to add the inflated
        // view to the parent. This should always be false or an exception will be thrown. Android will add
        // the view to the parent when necessary.
        View view = inflater.inflate(R.layout.resultcard, container, false);
        mName = (TextView)view.findViewById(R.id.resultCardname); 
        mImageView=(ImageView)view.findViewById(R.id.resultCardImage);
        mDistance=(TextView)view.findViewById(R.id.resultCarddistance);
        mAddress=(TextView)view.findViewById(R.id.resultCardaddress);
        mRatingbar=(RatingBar)view.findViewById(R.id.resultCardratingsBar);
        // This is how you access your layout views. Notice how we call the findViewById() method
        // on our View directly. There is no method called findViewById() defined on Fragments like
        // there is in an Activity.
       /* Button button = (Button) view.findViewById(R.id.fragment_button);
        
        // A simple OnClickListener for our button. You can see here how a Fragment can encapsulate
        // logic and views to build out re-usable Activity components.
        button.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                
                if (activity != null) {
                    Toast.makeText(activity, R.string.toast_you_just_clicked_a_fragment, Toast.LENGTH_LONG).show();
                }
            }
            
        });*/
        
        return view;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
