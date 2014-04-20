package com.example.triary_app;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "DB_Triary";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE_Book_table = 
	"create table Book_table( Book_table_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "+"title, "+"author, "+"country, "+"imgArea, "+"status );";
	

	private static final String DATABASE_CREATE_DetailOfBook_table =
	"create table DetailOfBook( DetailOfBook INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "+"date ,"+"category ,"+"name ,"+"comment ,"+"latitude ,"+"longtitude ,"+"score , "+"book_id );";
	
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_Book_table);	
		
		db.execSQL(DATABASE_CREATE_DetailOfBook_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DataBaseHandler.class.getName(), "Upgrade database version");
		db.execSQL("DROP TABLE IF EXISTS Book_table");
	
		db.execSQL("DROP TABLE IF EXISTS DetailOfBook");
		
		onCreate(db);
		
	}

	
	 
}
