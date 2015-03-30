package com.example.triary_app;



import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MapActivity extends Activity {
	private GoogleMap mMap;
	// private Button btnShowLocation;
	private GPSTracker gps;
	final Context context = this;
	private List<Address> addresses;
	private String add = "";
	private StringBuilder sb;
	private double latitude;
	private double longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		latitude = Double.parseDouble(getIntent().getStringExtra("lat"));
		longitude = Double.parseDouble(getIntent().getStringExtra("long"));
		Toast.makeText(this, longitude+"", Toast.LENGTH_LONG).show();
		
		int actionBarTitleId = Resources.getSystem().getIdentifier(
				"action_bar_title", "id", "android");
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Book_Akhanake.ttf");
		if (actionBarTitleId > 0) {
			TextView title = (TextView) findViewById(actionBarTitleId);
			if (title != null) {
				title.setTypeface(tf);

			}
		}
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		// create class object
		gps = new GPSTracker(MapActivity.this);
		// check if GPS enabled
		if (gps.canGetLocation()) {
//			double latitude = gps.getLatitude();
//			double longitude = gps.getLongitude();
			addLocationToMap(latitude, longitude);
			Geocoder geocoder = new Geocoder(getApplicationContext(),
					Locale.getDefault());
			try {
				addresses = geocoder.getFromLocation(latitude, longitude, 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		// add = addresses.get(0).toString();
		sb = new StringBuilder();
		add = "";
		if (addresses.size() > 0) {
			Address address = addresses.get(0);
			for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
				sb.append(address.getAddressLine(i)).append("\n");
				if (i != 0)
					add = add + address.getAddressLine(i) + " ";
			}
		}
//		Toast.makeText(getApplicationContext(), "Your Location is - " + add,
//				Toast.LENGTH_LONG).show();

	}

	private void addLocationToMap(double lat, double lon) {
		LatLng post = new LatLng(lat, lon);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		Marker melbourne = mMap.addMarker(new MarkerOptions()
				.position(post)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
				.title("Location").snippet(add));
		melbourne.showInfoWindow();
		CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(post, 10);
		mMap.animateCamera(yourLocation);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	 
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_map, container,
					false);
			return rootView;
		}
	}

}
