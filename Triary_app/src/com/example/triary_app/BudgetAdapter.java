package com.example.triary_app;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BudgetAdapter extends BaseAdapter {
	
	public ArrayList<HashMap> list;
	Activity activity;
	private ViewHolder holder;

	public BudgetAdapter(Activity activity, ArrayList<HashMap> list){
		super();
		this.activity = activity;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = activity.getLayoutInflater();
		if (view == null) {
			view = inflater.inflate(R.layout.layout_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.itemtitle);
			holder.price = (TextView) view.findViewById(R.id.price);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
//		if(position % 2 == 0)view.setBackgroundColor(Color.GREEN);
//		else view.setBackgroundColor(Color.WHITE);
		if(position==list.size()) view.setBackgroundColor(Color.BLUE);
		HashMap map = list.get(position);
		holder.name.setText((String)map.get("n"));
		holder.price.setText((String)map.get("p"));

		return view;
	}

	private class ViewHolder {
		TextView name;
		TextView price;
		

	}

}