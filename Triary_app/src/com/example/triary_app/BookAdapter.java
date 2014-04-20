package com.example.triary_app;

import java.util.ArrayList;
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

public class BookAdapter extends ArrayAdapter{
	
	private Context context;
	private int itemId;
	private List<ContactBook> book;
	
	public BookAdapter(Context context,int itemId,List<ContactBook> book){
		super(context,itemId,book);
		this.context = context;
		this.itemId = itemId;
		this.book = book;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View item = convertView;
//		ImageView imgBook  = (ImageView) item.findViewById(R.id.imgBook);
//	 	TextView title = (TextView) item.findViewById(R.id.title);
//		TextView country =  (TextView) item.findViewById(R.id.country);
		BookHolder holder = null;
		
		if(item == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			item = inflater.inflate(itemId, parent,false);
			holder = new BookHolder();
			
			holder.imgBook = (ImageView) item.findViewById(R.id.imgBook);
			holder.title = (TextView) item.findViewById(R.id.title);
			holder.country = (TextView) item.findViewById(R.id.country);
			
			item.setTag(holder);
		}else{
			holder = (BookHolder)item.getTag();
		}
		
//		List<ContactBook> book = new ArrayList<ContactBook>();
//		imgBook.setImageURI(book.get(position).getImg());
		holder.title.setText(book.get(position).getTitle());
		holder.country.setText(book.get(position).getCountry());
		
		String path = book.get(position).getImg();
		Bitmap bitmap  = BitmapFactory.decodeFile(path);
		holder.imgBook.setImageBitmap(bitmap);
	
		
		
		return item;
	}

}

class BookHolder{
	ImageView imgBook ;
	TextView title;
	TextView country;
}
