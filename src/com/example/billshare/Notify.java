package com.example.billshare;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
public class Notify extends FragmentActivity {
	private LinearLayout layout;
	public static ArrayList<String> phoneNumberList = new ArrayList<String>();
	private StringBuffer numberBuffer = new StringBuffer("");
	public static boolean isMessageSent = false;
	private EditText mainEditText;
	private List<EditText> editTextList = new ArrayList<EditText>();
	PackageManager pm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isMessageSent = false;
		setContentView(R.layout.activity_notify);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Button nextButOnNotify = (Button) findViewById(R.id.butOnNotify);
		layout = (LinearLayout) findViewById(R.id.linear_layout);
		
		 pm = this.getPackageManager();

		
		
		for (int i = 0; i < DivideBill.numberOfPeople; i++) {
			mainEditText = editText(i);
			editTextList.add(mainEditText);
			layout.addView(mainEditText);
		}
		nextButOnNotify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//sendMessage();
			//To check weather device has sms functionality or not	
				 if (pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
					 sendMessage();
				 } else {
			Toast.makeText(Notify.this, "Your device doesn't have send SMS functioanlity" ,Toast.LENGTH_SHORT).show();
				 }
			//end	 
			}
		});
	}

	private EditText editText(int _intID) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 10, 0, 10);
		EditText editText = new EditText(this);
		editText.setId(_intID);
		editText.setHint("Enter Phone Number");
		editText.setLayoutParams(params);
		editText.setSingleLine();
		editText.setBackgroundColor(Color.WHITE);
		editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
		return editText;
	}

	public void getPhoneNumbers() {
		for (int i = 0; i < DivideBill.numberOfPeople; i++) {
			numberBuffer
					.append(editTextList.get(i).getText().toString() + "; ");
		}
	}

	public void sendMessage() {
		getPhoneNumbers();
		Intent i = new Intent(android.content.Intent.ACTION_VIEW);
		isMessageSent = true;
		/**
		 * Multiple recipient numbers
		 */
		i.putExtra("address", numberBuffer.toString());
		// here i can send message to emulator 5556,5558,5560
		// you can change in real device
		i.putExtra("sms_body", "Cost per head = " + EnterManualValues.total
				/ DivideBill.numberOfPeople);
		i.setType("vnd.android-dir/mms-sms");
		startActivity(i);
		EnterManualValues.total=0;
		DivideBill.numberOfPeople=1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isMessageSent) {
			Intent notifyIntent = new Intent(Notify.this, TextSent.class);
			notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(notifyIntent);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notify, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
