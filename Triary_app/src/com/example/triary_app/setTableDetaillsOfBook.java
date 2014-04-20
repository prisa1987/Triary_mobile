package com.example.triary_app;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class setTableDetaillsOfBook {
	private DataBaseHandler dbHandler;
	private SQLiteDatabase db;
	private ContentValues values;
	private static final String Tri_table_Book_DetailOfBook = "DetailOfBook";
	
	
	public setTableDetaillsOfBook(Context con){
		dbHandler = new DataBaseHandler(con);
		db = dbHandler.getWritableDatabase();	
// 		dbHandler.onUpgrade(db, 1, 1);
	}
	
	public void addDetial(String book_id,String category,String name,String comment,String map,String scores){
		if(values == null) values = new ContentValues(); 
		values.put("Book_id", book_id);
		values.put("category", category);
		values.put("comment", comment);
		values.put("score", scores);
		
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   //get current date time with Date()
		   Date date = new Date();
//		   System.out.println(dateFormat.format(date));
	 
		   String current_date = dateFormat.format(date).toString();
		   values.put("date", current_date);
	}
	


	public long insert(){
		return db.insert(Tri_table_Book_DetailOfBook, null, values);
	}


}
