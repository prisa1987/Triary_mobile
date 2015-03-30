package com.example.triary_app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Reading_category_book extends Activity{

	private String title;
	private setTableBoookTable setTable;
	private TextView titleView;
	private TextView authorView;
	private TextView countryView;
	private ImageView imageArea;
	private CharSequence author;
	private CharSequence country;
	private String image;
	private Cursor cursor;
	private String id;
	private String status;
	private ImageView attractionBTN;
	private ImageView accomodationBTN;
	private ImageView foodBTN;
	private ImageView otherBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_reading_category_book);
		title = getIntent().getStringExtra("title");
		setTable = new setTableBoookTable(this);
		
		readBook();
		
		titleView = (TextView) findViewById(R.id.title);
		authorView = (TextView) findViewById(R.id.authorView);
		countryView = (TextView) findViewById(R.id.countryView);
		imageArea = (ImageView) findViewById(R.id.imageArea);
		
		attractionBTN = (ImageView)findViewById(R.id.AttractionBTN);
		accomodationBTN = (ImageView)findViewById(R.id.AccomodationBTN);
		foodBTN = (ImageView)findViewById(R.id.foodBTN);
		otherBTN = (ImageView)findViewById(R.id.otherBTN);
		
		attractionBTN.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),Reading_detail_book.class);
				intent.putExtra("category","Attraction");
				startActivity(intent);
				
			}
		});
		
		accomodationBTN.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),Reading_detail_book.class);
				intent.putExtra("category","Accomodation");
				startActivity(intent);
				
			}
		});
		
		otherBTN.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),Reading_detail_book.class);
				intent.putExtra("category","Other");
				startActivity(intent);
				
			}
		});
		
		foodBTN.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),Reading_detail_book.class);
				intent.putExtra("category","Food");
				startActivity(intent);
				
			}
		});
		
		titleView.setText(title);
		authorView.setText(author);
		countryView.setText(country);
		
		String path    = image;
		Bitmap bitmap  = BitmapFactory.decodeFile(path);
		imageArea.setImageBitmap(bitmap);
	}

	private void readBook() {
		cursor = setTable.readBook(title);
		id = cursor.getString(0);
		author = cursor.getString(2);
		country = cursor.getString(3);
		image = cursor.getString(4);
		status = cursor.getString(5);
		
	}
	
}
