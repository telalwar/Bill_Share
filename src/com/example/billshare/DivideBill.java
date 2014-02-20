package com.example.billshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DivideBill extends FragmentActivity {
	public static int numberOfPeople = 0;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_divide_bill);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		spinner = (Spinner) findViewById(R.id.person_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.person_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		Button nextButtonDivideBill = (Button) findViewById(R.id.butDivideBill);
		nextButtonDivideBill.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				numberOfPeople = Integer.parseInt(spinner.getSelectedItem()
						.toString());
				Intent intentDivideBill = new Intent(DivideBill.this,
						Notify.class);
				intentDivideBill.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentDivideBill);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.divide_bill, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
