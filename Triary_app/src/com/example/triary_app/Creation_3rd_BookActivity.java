package com.example.triary_app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Creation_3rd_BookActivity extends ListActivity {
	
	private String id;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
		
	    id = getIntent().getStringExtra("id");
//	    String[] values = new String[] { "Attraction","Accomodation","Food","Other" };
	    Category[] categories = new Category[]{
	    	new Category(R.drawable.compass,"Attraction"),
	    	new Category(R.drawable.accomodation,"Accomodation"),
	    	new Category(R.drawable.shopping,"Other"),
	    	
	    };
	    
	    CategoryAdapter adapter = new CategoryAdapter(this, R.layout.activity_creation_3rd_book, categories);
	    setListAdapter(adapter);
	    
	 
	    
	}
	

	 @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
//	    String item = (String) getListAdapter().getItem(position);
//	    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		 TextView tv = (TextView) v.findViewById(R.id.type);
		 if(tv.getText().equals("Attraction")){
			 Toast.makeText(this, "Attraction", Toast.LENGTH_LONG).show();
			 Intent intent = new Intent(getApplicationContext(),Creation_4th_BookActivity.class);
			intent.putExtra("category", "Attraction");
			startActivity(intent);
				
		 }
		 else if(tv.getText().equals("Accomodation")){
			 Intent intent = new Intent(getApplicationContext(),Creation_4th_BookActivity.class);
				intent.putExtra("category", "Accomodation");
				startActivity(intent);
		}
		 else if(tv.getText().equals("Other")){
			 Intent intent = new Intent(getApplicationContext(),Creation_4th_BookActivity.class);
				intent.putExtra("category", "Other");
				startActivity(intent);
			}
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
		    	 // save Detail
		    	createDetail();
		        return true;
		   
		    default:
		        return super.onOptionsItemSelected(item);
		    }
		
		}
		
		
		 

	private void createDetail() {
			Intent i = new Intent(getApplicationContext(), Creation_detail_BookActivity.class);
			i.putExtra("id",id);
			startActivity(i);
		}
}
