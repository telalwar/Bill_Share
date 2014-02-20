package com.example.billshare;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	private List<Integer> priceList = new ArrayList<Integer>();
	private Context context;

	public ListAdapter(List<Integer> priceList,
			Context applicationContext) {
		this.priceList = priceList;
		this.context = applicationContext;
	}

	public void setPriceList(List<Integer> priceList) {
		this.priceList = priceList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return priceList.size();
	}

	@Override
	public Object getItem(int position) {
		return priceList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactsViewHolder contactsViewHolder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.activity_list_row, null);
			contactsViewHolder = new ContactsViewHolder();
			contactsViewHolder.tv1 = (TextView) convertView
					.findViewById(R.id.item);
		} else {
			contactsViewHolder = (ContactsViewHolder) convertView.getTag();
		}
		int value = priceList.get(position);

		contactsViewHolder.tv1.setText("" + value);

		convertView.setTag(R.id.id_name, value);
		convertView.setTag(contactsViewHolder);
		return convertView;
	}

	private class ContactsViewHolder {
		TextView tv1;
	}
}
