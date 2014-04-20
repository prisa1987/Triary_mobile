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

public class DetailAdapter extends ArrayAdapter{
	
	private Context context;
	private int itemId;
	private List<ContactDetail> detail;
	
	public DetailAdapter(Context context,int itemId,List<ContactDetail> detail){
		super(context,itemId,detail);
		this.context = context;
		this.itemId = itemId;
		this.detail = detail;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View item = convertView;
		DetailHolder holder = null;
		
		if(item == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			item = inflater.inflate(itemId, parent,false);
			holder = new DetailHolder();
			
//			holder.img = (ImageView) item.findViewById(R.id.editName);
			holder.name = (TextView) item.findViewById(R.id.editName);
			holder.comment = (TextView) item.findViewById(R.id.editDescription);
			
			item.setTag(holder);
		}else{
			holder = (DetailHolder)item.getTag();
		}
		
//		List<ContactBook> book = new ArrayList<ContactBook>();
//		imgBook.setImageURI(book.get(position).getImg());
//		holder.name.setText(detail.get(position).getName());
//		holder.comment.setText(detail.get(position).getComment());
		
//		String path = book.get(position).getImg();
//		Bitmap bitmap  = BitmapFactory.decodeFile(path);
//		holder.imgBook.setImageBitmap(bitmap);
	
		
		
		return item;
	}

}

	class DetailHolder{
		ImageView img;
		TextView name;
		TextView comment;
	
	}

