package com.example.triary_app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Creation_2nd_BookActivity extends Activity {

	private TextView titleView;
	private TextView authorView;
	private TextView countryView;
	private	ImageView imageArea;
	private setTableBoookTable setTable;
	
	private Cursor cursor;
	
	private String id;
	private String title;
	private String author;
	private String country;
	private String status;
	private String image;
	private ImageView imageAdd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creation_2nd_book);
		title = getIntent().getStringExtra("title");
		setTable = new setTableBoookTable(this);
		
		readBook();
		
		titleView = (TextView) findViewById(R.id.title);
		authorView = (TextView) findViewById(R.id.authorView);
		countryView = (TextView) findViewById(R.id.countryView);
		imageArea = (ImageView) findViewById(R.id.imageArea);	
		
		titleView.setText(title);
		authorView.setText(author);
		countryView.setText(country);
		
		String path    = image;
		Bitmap bitmap  = BitmapFactory.decodeFile(path);
		imageArea.setImageBitmap(bitmap);
	
		
	}
	
	public void readBook(){
		cursor = setTable.readBook(title);
		id = cursor.getString(0);
		author = cursor.getString(2);
		country = cursor.getString(3);
		image = cursor.getString(4);
		status = cursor.getString(5);
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
 
        return super.onCreateOptionsMenu(menu);
    }
 
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		MenuItem menuSetting = menu.findItem(R.id.action_settings);
	    MenuItem menuAdd = menu.findItem(R.id.action_add);
	    MenuItem menuDone = menu.findItem(R.id.action_done);
	
	    menuSetting.setVisible(false);
	    menuAdd.setVisible(true);
	    menuDone.setVisible(false);
	    return true;
	}

	 /**
	 * On selecting action bar icons
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Take appropriate action for each action item click
		
	    switch (item.getItemId()) {
	    case R.id.action_add:
	    	 // create Book
	        addBook();
	        return true;
	   
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	   
	
	}
	private void addBook() {
		Intent intent = new Intent(getApplicationContext(),Creation_3rd_BookActivity.class);
		intent.putExtra("id", id);
		startActivity(intent);
		
	}

}

