package com.example.triary_app;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SetTableBudget {
	
	private DataBaseHandler dbHandler;
	private SQLiteDatabase db;
	private ContentValues values;
	private static final String Tri_table = "Budget_table";
	
	
	public SetTableBudget(Context con){
		dbHandler = new DataBaseHandler(con);
		db = dbHandler.getWritableDatabase();	
// 		dbHandler.onUpgrade(db, 1, 1);
	}
	
	public void addDetailBudget(String detailBudget){
		if(values == null) values = new ContentValues(); 
		values.put("detailBudget", detailBudget);
//		return db.insert(Tri_table, null, values);
	}
	
	
	public void addPriceBudget(String price){
		if(values == null) values = new ContentValues();
		values.put("price", price);
//		return db.insert(Tri_table, null, values);
	}
	

	
	public long insert(){
		return db.insert(Tri_table, null, values);
	}

	public Cursor readBudgetAll(String id_book){
		String[] args = { id_book };
		Cursor mCursor = db.query(true, Tri_table, new String[]{
				"Budget_table_id", "detailBudget","price","book_id"
		}, null, null, null, null, null, null, null);
		
		
		
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	
	

	public long deleteBudget(String title){
		 try {
					
	     		long rows = db.delete("Budget_table", "detailBudget = ?",
			            new String[] { String.valueOf(title) });		
//	     		db.close();
	     		return rows; // return rows deleted.
					
			 } catch (Exception e) {
			    return -1;
			 }
	}

	public void addBook(String id) {
		if(values == null) values = new ContentValues(); 
		values.put("book_id", id);
		
	}
	


}
