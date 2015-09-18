package com.techzguyz.samplesocketandroidapp;
import com.techzguyz.samplesocketandroidapp.other.Msg;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MsgListAdapter extends BaseAdapter {

	private Context context;
	private List<Msg> msgItems;
	
	public MsgListAdapter(Context context, List<Msg> navDrawerItems) {
		this.context = context;
		this.msgItems = navDrawerItems;
	}
	
	@Override
	public int getCount(){
		return msgItems.size();
	}
	
	@Override
	public Object getItem(int position) {
		return msgItems.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/**
		 * The following list not implement reusable list items as list items are showing incorrect data.
		 * Add the solution if you have one
		 */
		
		Msg m = msgItems.get(position);
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		//Identifying the message owner
		if(msgItems.get(position).isSelf()) {
			//message belongs to you, so load the right aligned
			convertView = mInflater.inflate(R.layout.list_item_msg_r, null);
		} else {
			//message belongs to you, so load the right aligned
			convertView = mInflater.inflate(R.layout.list_item_msg_l, null);
		}
		
		TextView lblFrom = (TextView) convertView.findViewById(R.id.lblMsgFrom);
		TextView txtMsg = (TextView) convertView.findViewById(R.id.txtMsg);
		
		txtMsg.setText(m.getMsg());
		lblFrom.setText(m.getFromName());
		
		return convertView;
	}
	
}
