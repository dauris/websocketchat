package com.techzguyz.samplesocketproject.other;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by DL5411 on 9/16/2015.
 */
public class Utils {

    private Context context;
    private SharedPreferences sp;

    private static final String KEY_SP = "ANDROID_WEB_CHAT";
    private static final int KEY_MODE_PRIVATE = 0;
    private static final String KEY_SESSION_ID = "sessionId", FLAG_MESSAGE = "message";

    public Utils(Context context) {
        this.context = context;
        sp = this.context.getSharedPreferences(KEY_SP, KEY_MODE_PRIVATE);
    }

    public void storeSessionId(String sessionId) {
        Editor editor = sp.edit();
        editor.putString(KEY_SESSION_ID, sessionId);
        editor.commit();
    }

    public String getKeySessionId() {
        return sp.getString(KEY_SESSION_ID, null);
    }

    public String getSendMessageJson(String message) {
        String json = null;

        try {
            JSONObject jObj = new JSONObject();
            jObj.put("flag", FLAG_MESSAGE);
            jObj.put("sessionId",getKeySessionId());
            jObj.put("message", message);

            json = jObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

}
