package com.example.triary_app;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeleteBookAction extends ListActivity {
	  /** Items entered by the user is stored in this ArrayList variable */
  
    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter adapter;

	
	private setTableBoookTable setTable;
	private Cursor cursor;
	private List list;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        /** Setting a custom layout for the list activity */
        setContentView(R.layout.activity_deletebook);
        setTable = new setTableBoookTable(this);
        readAllBook();
 
     
        /** Reference to the delete button of the layout main.xml */
        Button btnDel = (Button) findViewById(R.id.btnDel);
 
        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, list);
 
        getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));   
//		hide();
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
 
        /** Defining a click event listener for the button "Delete" */
        OnClickListener listenerDel = new OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Getting the checked items from the listview */
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();
 
                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){                      
                        setTable.deleteBook(list.get(i).toString());
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
            }
        };
 
    
        /** Setting the event listener for the delete button */
        btnDel.setOnClickListener(listenerDel);
 
        /** Setting the adapter to the ListView */
        setListAdapter(adapter);
    }
    
    public void readAllBook() {
		list = new ArrayList();

		cursor = setTable.readAllBook();
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));

			} while (cursor.moveToNext());
		}

	}
    


    
 
}
