package com.example.triary_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.InputEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FullDetailAdapter extends ArrayAdapter{
	
	private Context context;
	private int itemId;
	private List<ContactDetail> detail;
	private GoogleMap map;
	private int pos;

	public FullDetailAdapter(Context context, int itemId, List<ContactDetail> detail) {
		super(context, itemId, detail);
		this.context = context;
		this.itemId = itemId;
		this.detail = detail;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;
		DetailFullHolder holder = null;
		this.pos = position;
		
		if (item == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			item = inflater.inflate(itemId, parent, false);
			holder = new DetailFullHolder();
			
			
			holder.imgView = (ImageView) item.findViewById(R.id.imageBigView);
			holder.checkin = (TextView) item.findViewById(R.id.placesView);
			holder.name = (TextView) item.findViewById(R.id.titleView);
			holder.scores = (RatingBar) item.findViewById(R.id.mainRatingBar);
			holder.comment = (TextView) item.findViewById(R.id.commentView);
			holder.date = (TextView) item.findViewById(R.id.dateView);
			holder.imageMap = (ImageView) item.findViewById(R.id.imageMap);
	

			item.setTag(holder);
		} else {
			holder = (DetailFullHolder) item.getTag();
		}

//		 List<ContactBook> book = new ArrayList<ContactBook>();
//		 imgBook.setImageURI(book.get(position).getImg());
		
		holder.name.setText(detail.get(position).getName());
		holder.comment.setText(detail.get(position).getComment());
		holder.checkin.setText(detail.get(position).getCheckin());
		holder.date.setText(detail.get(position).getDate());
		
		String path    = detail.get(position).getImg();
		
		if(path != null)		{
			Bitmap bitmap  = BitmapFactory.decodeFile(path);
//			BitmapFactory.Options options=new BitmapFactory.Options();
//			options.inSampleSize = 8;
//			Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);
			
			holder.imgView.setImageBitmap(bitmap);
		}
	
		
		if(detail.get(position).getScores().equals(0) ){
			holder.scores.setRating(0);
		}else{
			holder.scores.setRating(Float.parseFloat(detail.get(position)
					.getScores()));
		}
		
//		 map = ((MapFragment) igetFragmentManager().findFragmentById(R.id.imageMap)).getMap(); 
	
		holder.imageMap.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(context, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("lat", detail.get(pos).getLatitude());
				intent.putExtra("long", detail.get(pos).getLongtitude());
				context.startActivity(intent);
			}
		});
		return item;
	}
		
	

	
	


		class DetailFullHolder {
		 TextView checkin;
			ImageView imgView;
			TextView name;
			TextView comment;
			ImageView imageMap;
			RatingBar scores;
			TextView date;
//			ImageView budView;
		}
		


}

