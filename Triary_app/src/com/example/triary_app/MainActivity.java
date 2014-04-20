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


public class MainActivity extends ListActivity {
	
	private ImageView AddView;
	private setTableBoookTable setTable;
	private Cursor cursor;
	private String id;
	private String image;
	private String status;
	private String author;
	private String country;
	private List<ContactBook> list;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		setTable = new setTableBoookTable(this);
		readAllBook();
		int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Book_Akhanake.ttf");
		if (actionBarTitleId > 0) {
		    TextView title = (TextView) findViewById(actionBarTitleId);
		    if (title != null) {
		        title.setTypeface(tf);
		  
		    }
		}
		
	
//	    String[] values = new String[] { "1","2","3","4","5","6","7","8" }; // Query All Book
//		String[] values = getListBook();
	    // use your custom layout
//	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//	    R.layout.activity_main, R.id.title, getListBook());
	    
	   
	    
//	    setListAdapter(adapter);
		
		BookAdapter adapter = new BookAdapter(this, R.layout.activity_main, list);
		setListAdapter(adapter);
	    
	    
		
//		AddView = (ImageView) findViewById(R.id.imgAdd);
//		AddView.setOnClickListener(new OnClickListener() {
			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getApplicationContext(),Cover_BookActivity.class);
//				startActivity(intent);
				
//			}
//		});
	  
		
		
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
            createBook();
            return true;
       
        default:
            return super.onOptionsItemSelected(item);
        }
 
}

	private void createBook() {
		Intent intent = new Intent(getApplicationContext(),Cover_BookActivity.class);
		startActivity(intent);
		
	}
	
	
	public void readAllBook(){
		list= new ArrayList<ContactBook>();
		
		cursor = setTable.readAllBook();
		if(cursor.moveToFirst()){
			do{
				ContactBook contact = new ContactBook();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setTitle(cursor.getString(1));
				contact.setAuthor(cursor.getString(2));
				contact.setCountry(cursor.getString(3));
				contact.setImg(cursor.getString(4));
				contact.setStatus(cursor.getString(5));
				
				list.add(contact);
				
			}while(cursor.moveToNext());
		}
	
		
		
		
	}
	

		

}
