package com.example.billshare;

import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EnterManualValues extends Activity {
	private ListView listView;
	private EditText editText1;
	private Button doneButton, checkgallery;
	private Button buttonOnEnterManual;
	private ListAdapter adapter;
	public static int total = 0;
	private ArrayList<Integer> priceList = new ArrayList<Integer>();
	final static private int NEW_PICTURE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_manual_values);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		listView = (ListView) findViewById(R.id.list);
		buttonOnEnterManual = (Button) findViewById(R.id.butOnEnterManual);
		editText1 = (EditText) findViewById(R.id.editText1);
		doneButton = (Button) findViewById(R.id.done_button);
		checkgallery = (Button) findViewById(R.id.phone_gallery);
		adapter = new ListAdapter(priceList, this);
		listView.setAdapter(adapter);
		buttonOnEnterManual.setOnClickListener(onclickListener);
		doneButton.setOnClickListener(onclickListener);
		checkgallery.setOnClickListener(onclickListener);
	}

	private OnClickListener onclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			int id = view.getId();

			switch (id) {
			case R.id.butOnEnterManual:
				if (TextUtils.isEmpty(editText1.getText().toString().trim())) {
					Toast.makeText(EnterManualValues.this,
							"Please enter a valid price", Toast.LENGTH_SHORT)
							.show();
				} else {
					int value = Integer
							.parseInt(editText1.getText().toString());
					total = total + value;
					priceList.add(value);
					editText1.setText("");
					adapter.setPriceList(priceList);
				}
				break;
			case R.id.done_button:
				if (priceList.isEmpty()) {
					Toast.makeText(EnterManualValues.this,
							"Please enter atleast One amount",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intentForDivideBillScreen = new Intent(
							EnterManualValues.this, DivideBill.class);
					intentForDivideBillScreen
							.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentForDivideBillScreen);
				}
				break;
			// To access gallery ..start
			case R.id.phone_gallery:
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType("video/*, images/*");
				startActivityForResult(i, NEW_PICTURE);
				//break;
			// To access gallery end

			default:
				break;
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_manual_values, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			Intent intent = new Intent(EnterManualValues.this,
					MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
