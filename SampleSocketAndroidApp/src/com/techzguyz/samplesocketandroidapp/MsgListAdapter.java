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
}
