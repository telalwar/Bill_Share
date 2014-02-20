package com.example.billshare;

import java.io.File;
import java.util.Random;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	// private static final int ACTION_TAKE_PHOTO_S = 2;
	final static private int NEW_PICTURE = 1;
	private Bitmap mImageBitmap;
	String mCameraFileName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tvSecondChoice = (TextView) findViewById(R.id.secondchoice);
		TextView first = (TextView) findViewById(R.id.firstchoice);
		tvSecondChoice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent manualValuesIntent = new Intent(MainActivity.this,
						EnterManualValues.class);

				startActivity(manualValuesIntent);

			}
		});
		first.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(MainActivity.this, "Right answer" ,
				// Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				// Picture from camera
				intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

				Random generator = new Random();
				int n = 10000;
				n = generator.nextInt(n);
				String fname = "Image-" + n + ".jpg";

				String outPath = "/sdcard/" + fname;
				File outFile = new File(outPath);

				mCameraFileName = outFile.toString();
				Uri outuri = Uri.fromFile(outFile);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);
				 /*sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
				 Uri.parse("file://"+
				 Environment.getExternalStorageDirectory())));*/
				startActivityForResult(intent, NEW_PICTURE);
			}
		});

	}

	//
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == NEW_PICTURE) {
			// return from file upload
			if (resultCode == RESULT_OK) {
				Uri uri = null;
				if (data != null) {
					uri = data.getData();
				}
				if (uri == null && mCameraFileName != null) {
					uri = Uri.fromFile(new File(mCameraFileName));
				}
				File file = new File(mCameraFileName);
				if (!file.exists()) {
					file.mkdir();
				}

			} else {
				Toast.makeText(MainActivity.this, "Problem in taking PICTURE",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	//

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.help:
			help_Page();
			return true;
		case R.id.action_settings:
			// showHelp();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void help_Page() {
		// TODO Auto-generated method stub
		Intent helpIntent = new Intent(MainActivity.this, Help.class);
		startActivity(helpIntent);
	}

}
