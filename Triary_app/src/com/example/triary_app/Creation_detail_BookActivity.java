package com.example.triary_app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class Creation_detail_BookActivity extends Activity {
	
	 private Spinner spinnerType;
	 private setTableDetaillsOfBook setTable;
	 private EditText nameText;
	 private EditText descriptionText;
	 private EditText mapText;
	 private EditText map;
	 private RatingBar scores;
	 private Spinner categorySpinner;
	 private String id_text;
	 private int id;
	 private String category;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creation_detail_book);
		id_text = getIntent().getStringExtra("id");
		id = Integer.parseInt(id_text);
		setTable = new setTableDetaillsOfBook(this);
		
	
		addItemsOnSpinnerType();
		addListenerOnSpinnerItemSelection();
		
		nameText = (EditText) findViewById(R.id.editName);
		descriptionText = (EditText) findViewById(R.id.editDescription);
		map =  (EditText) findViewById(R.id.editMap);
		scores = (RatingBar)findViewById(R.id.scores);
		categorySpinner = (Spinner) findViewById(R.id.spinnerType);

		
	}


	private void addListenerOnSpinnerItemSelection() {
		spinnerType = (Spinner) findViewById(R.id.spinnerType);
		spinnerType.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	private void addItemsOnSpinnerType() {
		spinnerType = (Spinner) findViewById(R.id.spinnerType);
		List<String> list = new ArrayList<String>();
		list.add("Attraction");
		list.add("Accomodation");
		list.add("Food");
		list.add("Other");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerType.setAdapter(dataAdapter);
		spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> av,View v,int position,long id){
				
					category = ((EditText) v).getText().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				return;		
			}
			
		});
		
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
    menuAdd.setVisible(false);
    menuDone.setVisible(true);
    return true;
}



	 /**
	 * On selecting action bar icons
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Take appropriate action for each action item click
		
	    switch (item.getItemId()) {
	    case R.id.action_done:
	    	 // save Detail
	    	saveDetail();
	        return true;
	   
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	
	}
	 

	private void saveDetail() {

		String name = nameText.getText().toString();
		String comment = descriptionText.getText().toString();
		String category = (String) spinnerType.getSelectedItem();
		String score_text = scores.getRating()+"";
		
		
		nameText.setText(" ");
		descriptionText.setText(" ");
		map.setText(" ");
		scores.setRating(0);
		
		setTable.addDetial(id_text, category, name, comment, "", score_text);
		
		Intent intent = new Intent(getApplicationContext(),Creation_3rd_BookActivity.class);
		startActivity(intent);
		setTable.insert();
		
	}
	
}
