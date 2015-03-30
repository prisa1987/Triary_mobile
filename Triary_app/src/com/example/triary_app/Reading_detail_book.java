package com.example.triary_app;
import java.util.ArrayList;
import java.util.List;


import com.google.android.gms.maps.GoogleMap;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Reading_detail_book extends ListActivity {

	private String id_book;
	private ArrayList<ContactDetail> list;
	private setTableDetaillsOfBook setTable;
	private Cursor cursor;
	
	// MAP
	private GoogleMap mMap;
	private ImageView btnShowLocation;
	private GPSTracker gps;
	final Context context = this;
	private List<Address> addresses;
	private String add = "";
	private StringBuilder sb;
	
	private double latitude;
	private double longitude;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
			

		getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));  
		
		super.onCreate(savedInstanceState);
		 setTable = new setTableDetaillsOfBook(this);
		
		String category = getIntent().getStringExtra("category");
		id_book = getIntent().getStringExtra("id");
	
		if (category.equals("Attraction"))
			readBook_Attraction();
		else if (category.equals("Accomodation"))
			readBook_Accomodation();
		else if (category.equals("Other"))
			readBook_Other();
		else if (category.equals("Food"))
			readBook_Food();
		else readBookAll();
		getActionBar().hide();
		
		if(list.size()!=0){
			FullDetailAdapter adapter = new FullDetailAdapter(this,
					R.layout.activity_reading_detail_book, list);
			setListAdapter(adapter);
		}else{
			alertPlease();
		}

	}
	
	public void alertPlease(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("No Information")
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   Intent intent = new Intent(getApplicationContext(),Creation_3rd_BookActivity.class);
		       		intent.putExtra("id", id_book);
		       		startActivity(intent);
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
		
	}

//	btnShowLocation = (ImageView) findViewById(R.id.imageMap);
//	btnShowLocation.setOnClickListener(new View.OnClickListener() {
		

	private void readBookAll() {
		list = new ArrayList<ContactDetail>();
		cursor = setTable.readDetail(id_book);
		if (cursor.moveToFirst()) {
			do {
				ContactDetail contact = new ContactDetail();
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setDate(cursor.getString(1));
				contact.setCategory(cursor.getString(2));
				contact.setName(cursor.getString(3));
				contact.setComment(cursor.getString(4));
				 contact.setScores(cursor.getString(7));
				 contact.setImg(cursor.getString(9));
				 contact.setCheckin(cursor.getString(10));
				list.add(contact);
				 contact.setLatitude(cursor.getString(5));
				 contact.setLongtitude(cursor.getString(6));
			} while (cursor.moveToNext());
		}
		
	}

	private void readBook_Food() {
		list = new ArrayList<ContactDetail>();
		cursor = setTable.readDetail(id_book);
		if (cursor.moveToFirst()) {
			do {
				ContactDetail contact = new ContactDetail();
			 if(cursor.getString(2).equals("Food")){
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setDate(cursor.getString(1));
				contact.setCategory(cursor.getString(2));
				contact.setName(cursor.getString(3));
				contact.setComment(cursor.getString(4));
				 contact.setScores(cursor.getString(7));
				 contact.setImg(cursor.getString(9));
				 contact.setCheckin(cursor.getString(10));
				 contact.setLatitude(cursor.getString(5));
				 contact.setLongtitude(cursor.getString(6));
				list.add(contact);
				 }

			} while (cursor.moveToNext());
		}
		
	}

	private void readBook_Other() {
		list = new ArrayList<ContactDetail>();
		cursor = setTable.readDetail(id_book);
		if (cursor.moveToFirst()) {
			do {
				ContactDetail contact = new ContactDetail();
			 if(cursor.getString(2).equals("Other")){
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setDate(cursor.getString(1));
				contact.setCategory(cursor.getString(2));
				contact.setName(cursor.getString(3));
				contact.setComment(cursor.getString(4));
				 contact.setScores(cursor.getString(7));
				 contact.setImg(cursor.getString(9));
				 contact.setCheckin(cursor.getString(10));
				 contact.setLatitude(cursor.getString(5));
				 contact.setLongtitude(cursor.getString(6));
				list.add(contact);
				 }

			} while (cursor.moveToNext());
		}
		
	}

	private void readBook_Accomodation() {
		list = new ArrayList<ContactDetail>();
		cursor = setTable.readDetail(id_book);
		if (cursor.moveToFirst()) {
			do {
				ContactDetail contact = new ContactDetail();
			 if(cursor.getString(2).equals("Accomodation")){
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setDate(cursor.getString(1));
				contact.setCategory(cursor.getString(2));
				contact.setName(cursor.getString(3));
				contact.setComment(cursor.getString(4));
				 contact.setScores(cursor.getString(7));
				 contact.setImg(cursor.getString(9));
				 contact.setCheckin(cursor.getString(10));
				 contact.setLatitude(cursor.getString(5));
				 contact.setLongtitude(cursor.getString(6));
				list.add(contact);
				 }

			} while (cursor.moveToNext());
		}
		
	}

	private void readBook_Attraction() {
		list = new ArrayList<ContactDetail>();
		cursor = setTable.readDetail(id_book);
		if (cursor.moveToFirst()) {
			do {
				ContactDetail contact = new ContactDetail();
			 if(cursor.getString(2).equals("Attraction")){
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setDate(cursor.getString(1));
				contact.setCategory(cursor.getString(2));
				contact.setName(cursor.getString(3));
				contact.setComment(cursor.getString(4));
				 contact.setScores(cursor.getString(7));
				 contact.setImg(cursor.getString(9));
				 contact.setCheckin(cursor.getString(10));
				 contact.setLatitude(cursor.getString(5));
				 contact.setLongtitude(cursor.getString(6));
				list.add(contact);
				 }
			} while (cursor.moveToNext());
		}
		
	}
	
	
	
	
	
}
