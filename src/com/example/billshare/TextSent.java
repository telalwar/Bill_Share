package com.example.billshare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextSent extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Notify.isMessageSent = false;
		setContentView(R.layout.activity_text_sent);
		TextView tvhomePage = (TextView) findViewById(R.id.homepage);
		tvhomePage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentForHomeScreen = new Intent(TextSent.this,
						MainActivity.class);
				intentForHomeScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentForHomeScreen);
			}
		});
		Button butFeedback = (Button) findViewById(R.id.butfeedback);
		butFeedback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//finish();
				Intent intentForDivideBillScreen = new Intent(
						TextSent.this, Feedback.class);
				startActivity(intentForDivideBillScreen);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_sent, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_home:
			Intent intent = new Intent(TextSent.this, MainActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
