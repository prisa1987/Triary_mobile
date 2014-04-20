package com.example.triary_app;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class setTableBoookTable {
	
	private DataBaseHandler dbHandler;
	private SQLiteDatabase db;
	private ContentValues values;
	private static final String Tri_table = "Book_table";
	
	
	public setTableBoookTable(Context con){
		dbHandler = new DataBaseHandler(con);
		db = dbHandler.getWritableDatabase();	
		
 		
// 		dbHandler.onUpgrade(db, 1, 1);


	}
	
	public void addCountry(String country){
		if(values == null) values = new ContentValues(); 
		values.put("country", country);
//		return db.insert(Tri_table, null, values);
	}
	
	
	public void addTitle(String title){
		if(values == null) values = new ContentValues(); 
		values.put("title", title);
//		return db.insert(Tri_table, null, values);
	}
	
	public void addAuthor(String author){
		if(values == null) values = new ContentValues();
		values.put("author", author);
//		return db.insert(Tri_table, null, values);
		
	}	
	
	
	public void addImage(String imageName){
		if(values == null) values = new ContentValues();
		values.put("imgArea", imageName);
//		return db.insert(Tri_table, null, values);
	}
	
	public void setStatus(String status){
		if(values == null) values = new ContentValues();
		values.put("status", status);
	}
	
	public long insert(){
		return db.insert(Tri_table, null, values);
	}

	public Cursor readBook(String title){
		String[] args = { title };
		Cursor mCursor = db.query(true, Tri_table, new String[]{
				"Book_table_id", "title","author","country","imgArea","status" 
		}, "title=? ", args, null, null, null, null, null);
		
		
		
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public Cursor readAllBook(){
	
		Cursor mCursor = db.query(true, Tri_table, new String[]{
				"Book_table_id", "title","author","country","imgArea","status" 
		}, null, null, null, null, null, null);
		
		
		
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}


}
