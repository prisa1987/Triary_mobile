package com.example.triary_app;


import java.util.ArrayList;
import java.util.List;

import com.example.triary_app.R.menu;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Creation_4th_BookActivity extends ListActivity {
	
	private ImageView AddView;
	private setTableBoookTable setTable;
	private Cursor cursor;
	private String id;
	private String image;
	private String status;
	private String author;
	private String country;
	private List<ContactDetail> list;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		setTable = new setTableBoookTable(this);
		String category = getIntent().getStringExtra("category");
		
		if(category.equals("Attraction"))  readBook_Attraction();
		else if(category.equals("Accomodation")) readBook_Accomodation();
		else if(category.equals("Other")) readBook_Other();
		
		int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Book_Akhanake.ttf");
		if (actionBarTitleId > 0) {
		    TextView title = (TextView) findViewById(actionBarTitleId);
		    if (title != null) {
		        title.setTypeface(tf);
		  
		    }
		}
		

		DetailAdapter adapter = new DetailAdapter(this, R.layout.activity_creation_4th_book, list);
		setListAdapter(adapter);
	   
	
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
            back();
            return true;
       
        default:
            return super.onOptionsItemSelected(item);
        }
 
}

	private void back() {
		Intent intent = new Intent(getApplicationContext(),Creation_4th_BookActivity.class);
		startActivity(intent);
		
	}
	
	
	public void readBook_Attraction(){
		list= new ArrayList<ContactDetail>();
		
		cursor = setTable.readAllBook();
		if(cursor.moveToFirst()){
			do{
				ContactDetail contact = new ContactDetail();
//				if(cursor.getString(2).equals("Attraction")){
					contact.setId(Integer.parseInt(cursor.getString(0)));
					contact.setDate(cursor.getString(1));
					contact.setCategory(cursor.getString(2));
					contact.setName(cursor.getString(3));
					contact.setComment(cursor.getString(4));
//					contact.setScores(cursor.getString(7));
					list.add(contact);
//				}
				
			}while(cursor.moveToNext());
		}
	
		
		
		
	}
	
	private void readBook_Other() {
		
		list= new ArrayList<ContactDetail>();	
		cursor = setTable.readAllBook();
		if(cursor.moveToFirst()){
			do{
				ContactDetail contact = new ContactDetail();
				if(cursor.getString(2).equals("Other")){
					contact.setId(Integer.parseInt(cursor.getString(0)));
					contact.setDate(cursor.getString(1));
					contact.setCategory(cursor.getString(2));
					contact.setName(cursor.getString(3));
					contact.setComment(cursor.getString(4));
					contact.setScores(cursor.getString(7));
					list.add(contact);
				}
				
			}while(cursor.moveToNext());
		}
	
	}



	private void readBook_Accomodation() {
		
		list= new ArrayList<ContactDetail>();	
		cursor = setTable.readAllBook();
		if(cursor.moveToFirst()){
			do{
				ContactDetail contact = new ContactDetail();
				if(cursor.getString(2).equals("Accomodation")){
					contact.setId(Integer.parseInt(cursor.getString(0)));
					contact.setDate(cursor.getString(1));
					contact.setCategory(cursor.getString(2));
					contact.setName(cursor.getString(3));
					contact.setComment(cursor.getString(4));
					contact.setScores(cursor.getString(7));
					list.add(contact);
				}
				
			}while(cursor.moveToNext());
		}
	
		
	}
	

		

}
