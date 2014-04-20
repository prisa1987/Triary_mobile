package com.example.triary_app;


import java.io.File;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
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



public class Cover_BookActivity extends Activity{
	
	

	private EditText editTitle;
	private EditText editAuthor;
	private EditText editCountry;
	private ImageView imageView;
	private Button BtnDone;
	
//	private ImageView pickImg;
	
	
	private String title;
	private String author;
	private String country;
	private setTableBoookTable setTable;
	
	private final String [] items = new String[]{"Take from camera","Select from Gallery"};

	private int PICK_FROM_CAMERA = 1;

	  private Uri mImageCaptureUri;
		private int PICK_FROM_FILE = 2;
		 String path     = "";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_book);
		
		setTable = new setTableBoookTable(this);
		
		
		editTitle = (EditText) findViewById(R.id.editTitle);
		editAuthor = (EditText) findViewById(R.id.editAuthor);
		editCountry = (EditText) findViewById(R.id.editCountry);
		imageView = (ImageView) findViewById(R.id.imageInsert);
		
		int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Book_Akhanake.ttf");
		if (actionBarTitleId > 0) {
		    TextView title = (TextView) findViewById(actionBarTitleId);
		    if (title != null) {
		        title.setTypeface(tf);
		  
		    }
		}
	
	    ArrayAdapter<String> adapter  = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
	    AlertDialog.Builder builder     = new AlertDialog.Builder(this);
	    builder.setTitle("Select Image");
        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
         
			public void onClick( DialogInterface dialog, int item ) {
                if (item == 0) {
                    Intent intent    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file        = new File(Environment.getExternalStorageDirectory(),
                                        "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                    mImageCaptureUri = Uri.fromFile(file);
 
                    try {
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
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
 
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
                }
            }
        } );
        
        final AlertDialog dialog = builder.create();
        
//		imageView = (ImageView) findViewById(R.id.imageCover);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent();
//				intent.setAction(Intent.ACTION_GET_CONTENT);
//				intent.setType("image/*");
//				startActivityForResult(Intent.createChooser(intent, "Select app to pick image"), PICK_NAME);
				dialog.show();
			}
		});
		
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
 
        return super.onCreateOptionsMenu(menu);
    }
 
@Override
public boolean onPrepareOptionsMenu(Menu menu) {
	
	MenuItem menuSetting = menu.findItem(R.id.action_settings);
    MenuItem menuAdd = menu.findItem(R.id.action_add);
    MenuItem menuDone = menu.findItem(R.id.action_done);

    menuSetting.setVisible(false);
    menuAdd.setVisible(false);
    menuDone.setVisible(true);
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
   
    default:
        return super.onOptionsItemSelected(item);
    }

}

	private void doneBook() {
		title = editTitle.getText().toString();
		author = editAuthor.getText().toString();
		country = editCountry.getText().toString();
		
		editTitle.setText(" ");
		editAuthor.setText(" ");
		editCountry.setText(" ");
		addDataToSQLite();
		Intent intent = new Intent(getApplicationContext(),Creation_2nd_BookActivity.class);
		intent.putExtra("title", title);
		startActivity(intent);
	
}

	protected void addDataToSQLite(){
		setTable.addTitle(title);
		setTable.addAuthor(author);
		setTable.addCountry(country);
		
		setTable.addImage(path);
		setTable.setStatus("unfinished");
		setTable.insert();
		
		
//		Log.d("data","data id = "+insertID);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) return;
		 
        Bitmap bitmap   = null;
       
 
        if (requestCode == PICK_FROM_FILE) {
            mImageCaptureUri = data.getData();
            path = getRealPathFromURI(mImageCaptureUri); //from Gallery
 
            if (path == null)
                path = mImageCaptureUri.getPath(); //from File Manager
 
            if (path != null)
                bitmap  = BitmapFactory.decodeFile(path);
        } else {
            path    = mImageCaptureUri.getPath();
            bitmap  = BitmapFactory.decodeFile(path);
        }
 
        imageView.setImageBitmap(bitmap);
    }
 
		
	public String getRealPathFromURI(Uri contentUri) {
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query( contentUri, proj, null, null,null);
 
        if (cursor == null) return null;
 
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 
        cursor.moveToFirst();
 
        return cursor.getString(column_index);
    }



}
