package com.techzguyz.samplesocketandroidapp.other;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;;

public class Utils {
	
	private Context context;
	private SharedPreferences sp;
	
	private static final String Key_SP = "ANDROID_WEB_CHAT";
	private static final int Key_Mode_P = 0;
	private static final String KEY_SESSION_ID = "sessionId", FLAG_MESSAGE = "message";
	
	public Utils(Context context) {
		this.context = context;
		sp = this.context.getSharedPreferences(Key_SP, Key_Mode_P);
	}
	
	public void storeSessionId(String sessionId) {
		Editor ET = sp.edit();
		ET.putString(KEY_SESSION_ID, sessionId);
		ET.commit();
	}
	
	public String getSessionId() {
		return sp.getString(KEY_SESSION_ID, null);
	}
	
	public String getSendMessageJSON(String message) {
		String json = null;
		
		try {
			JSONObject jObj = new JSONObject();
			jObj.put("flag", FLAG_MESSAGE);
			jObj.put("sessionId", getSessionId());
			jObj.put("message", message);
			
			json = jObj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

}
