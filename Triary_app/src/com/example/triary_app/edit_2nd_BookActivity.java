package com.example.triary_app;

import java.io.File;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class edit_2nd_BookActivity extends Activity {

	private EditText editTitle;
	private EditText editAuthor;
	private EditText editCountry;
	private ImageView imageView;
	private Button BtnDone;

	// private ImageView pickImg;

	private String title;
	private String author;
	private String country;
	private setTableBoookTable setTable;

	private final String[] items = new String[] { "Take from camera",
			"Select from Gallery" };

	private int PICK_FROM_CAMERA = 1;

	private Uri mImageCaptureUri;
	private int PICK_FROM_FILE = 2;
	String path = "";
	private Cursor cursor;
	private String id_book;
	private String image;
	private String status;
	private Bitmap bitmapp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_book);
		
		setTable = new setTableBoookTable(this);
		editTitle = (EditText) findViewById(R.id.editTitle);
		editAuthor = (EditText) findViewById(R.id.editAuthor);
		editCountry = (EditText) findViewById(R.id.editCountry);
		imageView = (ImageView) findViewById(R.id.imageInsert);
		title = getIntent().getStringExtra("title");
		
		readBook();
		editTitle.setText(title);
		editAuthor.setText(author);
		editCountry.setText(country);
		bitmapp = BitmapFactory.decodeFile(path);
		imageView.setImageBitmap(bitmapp);
		
		int actionBarTitleId = Resources.getSystem().getIdentifier(
				"action_bar_title", "id", "android");
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Book_Akhanake.ttf");
		if (actionBarTitleId > 0) {
			TextView title = (TextView) findViewById(actionBarTitleId);
			if (title != null) {
				title.setTypeface(tf);

			}
		}
//		hide();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, items);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Image");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int item) {
				if (item == 0) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File file = new File(Environment
							.getExternalStorageDirectory(), "tmp_avatar_"
							+ String.valueOf(System.currentTimeMillis())
							+ ".jpg");
					mImageCaptureUri = Uri.fromFile(file);

					try {
						intent.putExtra(
								android.provider.MediaStore.EXTRA_OUTPUT,
								mImageCaptureUri);
						intent.putExtra("return-data", true);

						startActivityForResult(intent, PICK_FROM_CAMERA);
					} catch (Exception e) {
						e.printStackTrace();
					}

					dialog.cancel();
				} else {
					Intent intent = new Intent();

					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);

					startActivityForResult(Intent.createChooser(intent,
							"Complete action using"), PICK_FROM_FILE);
				}
			}
		});

		final AlertDialog dialog = builder.create();

		// imageView = (ImageView) findViewById(R.id.imageCover);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent();
				// intent.setAction(Intent.ACTION_GET_CONTENT);
				// intent.setType("image/*");
				// startActivityForResult(Intent.createChooser(intent,
				// "Select app to pick image"), PICK_NAME);
				dialog.show();
			}
		});

	}
	
	public void hide(){
		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags = 
//        		View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                 View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                 View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                ;
        // This work only for android 4.4+
        if (currentApiVersion >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            // Code below is for case when you press Volume up or Volume down.
            // Without this after pressing valume buttons navigation bar will
            // show up and don't hide
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener
            (new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {


		MenuItem menuSetting = menu.findItem(R.id.action_delete);
		MenuItem menuAdd = menu.findItem(R.id.action_add);
		MenuItem menuDone = menu.findItem(R.id.action_done);
		MenuItem menuEdit = menu.findItem(R.id.action_edit);
		MenuItem menuNext = menu.findItem(R.id.action_next);
		MenuItem menuHome = menu.findItem(R.id.action_home);
		menuEdit.setVisible(false);
		

		menuSetting.setVisible(false);
		menuAdd.setVisible(false);
		menuDone.setVisible(true);
		menuNext.setVisible(false);
		menuHome.setVisible(true);
		return true;
	}

	/**
	 * On selecting action bar icons
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click

		switch (item.getItemId()) {
		case R.id.action_done:
			// create Book
			doneBook();
			return true;
			
		case R.id.action_home:{
			// save Detail
//			saveDetail();
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
//			intent.putExtra("id", id_book);
			return true;
		}

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void doneBook() {
		title = editTitle.getText().toString();
		author = editAuthor.getText().toString();
		country = editCountry.getText().toString();
		
		if(title.trim().equals("")){
			alertPlease();
		}else{
		editTitle.setText(" ");
		editAuthor.setText(" ");
		editCountry.setText(" ");
		setTable.editDataToSQLite(id_book, title, author, path, country);
		
		Intent intent = new Intent(getApplicationContext(),
				Creation_2nd_BookActivity.class);
		intent.putExtra("title", title);
		startActivity(intent);
		}

	}
	
	public void alertPlease() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Please fill title")
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                //do things
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;

		Bitmap bitmap = null;

		if (requestCode == PICK_FROM_FILE) {
			mImageCaptureUri = data.getData();
			path = getRealPathFromURI(mImageCaptureUri); // from Gallery

			if (path == null)
				path = mImageCaptureUri.getPath(); // from File Manager

			if (path != null)
				bitmap = BitmapFactory.decodeFile(path);
		} else {
			path = mImageCaptureUri.getPath();
			bitmap = BitmapFactory.decodeFile(path);
		}

		imageView.setImageBitmap(bitmap);
	}

	public String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = getContentResolver().query(contentUri, proj, null,
				null, null);

		if (cursor == null)
			return null;

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		cursor.moveToFirst();

		return cursor.getString(column_index);
	}
	
	public void readBook(){
		cursor = setTable.readBook(title);
		id_book = cursor.getString(0);
		title = cursor.getString(1);
		author = cursor.getString(2);
		country = cursor.getString(3);
		path = cursor.getString(4);
		status = cursor.getString(5);
		
	}
	

}
