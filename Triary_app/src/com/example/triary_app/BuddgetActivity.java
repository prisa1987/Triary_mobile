package com.example.triary_app;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class BuddgetActivity extends Activity {

	private ImageView budicon;
	private ImageView budinput;
	private EditText price;
	private EditText list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		budicon = (ImageView) findViewById(R.id.imageView1);
		budicon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertView(buildArray());
			}
		});
//		budinput = (ImageView) findViewById(R.id.budinput);
		budinput.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertAsk();
			}
		});

	}

	//call alertdialog of input data
	public void alertAsk() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				BuddgetActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		final FrameLayout frameView = new FrameLayout(BuddgetActivity.this);
		alertDialog.setView(frameView).setCancelable(false)
				.setPositiveButton("SAVE",
				new DialogInterface.OnClickListener() {
					
					//when user click on "save" button this method will get data from edittext
					@Override
					public void onClick(DialogInterface dialog, int id) {

						list = (EditText)findViewById(R.id.list);
//						String listtext = list.getText().toString();
						price = (EditText)findViewById(R.id.price);
//						String pricetext = price.getText().toString();
//						Double pricetext = Double.parseDouble(price.getText().toString());
//						Toast.makeText(getApplicationContext(), listtext+" price "+pricetext +" was saved.", 
//								   Toast.LENGTH_LONG).show();
						Toast.makeText(getApplicationContext(), " was saved.", 
								   Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton("CANCEL", 
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		View convertView = (View) inflater.inflate(R.layout.asking, null);
		alertDialog.setView(convertView);
//		ListView lv = (ListView) convertView.findViewById(R.id.lv);
//		MyAdapter adapter = new MyAdapter(this, array);
//		lv.setAdapter(adapter);
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
		// lv.setAdapter(adapter);
		alertDialog.show();
	}

	//build array for showing on listview
	public ArrayList buildArray() {
		ArrayList<HashMap> array = new ArrayList<HashMap>();
		ArrayList<String> data = new ArrayList<String>();
		data = new ArrayList<String>();
//		data.add("������������������������");
//		data.add("���������������������������������������");
//		data.add("������������������������������������ - ���������������������������������");
//		data.add("������������������������");
//		data.add("���������������������������������������");
//		data.add("������������������������������������ - ���������������������������������");
//		data.add("������������������������");
//		data.add("���������������������������������������");
//		data.add("������������������������������������ - ���������������������������������");
//		data.add("������������������������");
//		data.add("���������������������������������������");
//		data.add("������������������������������������ - ���������������������������������");
//		data.add("���������");
		ArrayList<Double> price = new ArrayList<Double>();
		price = new ArrayList<Double>();
//		price.add(3.0);
//		price.add(12.5);
//		price.add(7.5);
//		price.add(3.0);
//		price.add(12.5);
//		price.add(7.5);
//		price.add(3.0);
//		price.add(12.5);
//		price.add(7.5);
//		price.add(3.0);
//		price.add(12.5);
//		price.add(7.5);
		double sum = 0.0;
		for (int i = 0; i < price.size(); i++) {
			sum += price.get(i);
		}
		price.add(sum);
		HashMap<String, String> map;
		for (int i = 0; i < price.size(); i++) {
			map = new HashMap<String, String>();
			map.put("n", data.get(i));
			map.put("p", price.get(i) + "");
			array.add(map);
		}
		return array;
	}

	//call alertdialog of viewing data
	public void alertView(ArrayList arr) {
		// String names[] ={"A","B","C","D"};
		ArrayList array = arr;
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				BuddgetActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		final FrameLayout frameView = new FrameLayout(BuddgetActivity.this);
		alertDialog.setView(frameView).setCancelable(false).setPositiveButton("CLOSE",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		View convertView = (View) inflater.inflate(R.layout.custom, null);
		alertDialog.setView(convertView);
		ListView lv = (ListView) convertView.findViewById(R.id.lv);
		BudgetAdapter adapter = new BudgetAdapter(this, array);
		lv.setAdapter(adapter);
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
		// lv.setAdapter(adapter);
		alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_delete) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
