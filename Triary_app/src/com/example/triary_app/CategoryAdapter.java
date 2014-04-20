package com.example.triary_app;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter{

	private Context context;
	private int itemId;
	private Category[] category;
	
	
	public CategoryAdapter(Context context,int itemId,Category[] category){
		super(context,itemId,category);
		this.context = context;
		this.itemId = itemId;
		this.category = category;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View item = convertView;
		Holder holder = null;
		
		if(item == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			item = inflater.inflate(itemId, parent,false);
			holder = new Holder();
			
			holder.imgIcon = (ImageView) item.findViewById(R.id.ImgIcon);
			holder.type = (TextView) item.findViewById(R.id.type);
		
			
			item.setTag(holder);
		}else{
			holder = (Holder)item.getTag();
		}
		

		Category cat = category[position];
		holder.type.setText(cat.getCategory());
		holder.imgIcon.setImageResource(cat.getCategoryId());
		
	
		return item;
	}
	
	

}

class Holder{
	ImageView imgIcon ;
	TextView type;
	

}	
