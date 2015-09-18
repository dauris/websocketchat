package com.techzguyz.samplesocketandroidapp;

import com.techzguyz.samplesocketandroidapp.other.Msg;
import com.techzguyz.samplesocketandroidapp.other.Utils;
import com.techzguyz.samplesocketandroidapp.other.WsConfig;

//websocket used for the application communication
import com.codebutler.android_websockets.WebSocketClient;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	//LogCat tag
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private Button btnSend;
	private EditText inputMsg;
	private WebSocketClient client;
	
	//chat messages list adapter;
	private MsgListAdapter msgAdapter;
	private List<Msg> listMsges;
	private ListView listVMsg;
	
	private Utils utils;
	
	//Client Name
	private String name = null;
	
	//JSON flags to identify the kind of JSON response
	private static final String Tag_Self = "self", Tag_New = "new", Tag_MSG = "message", Tag_Exit = "exit";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnSend = (Button) findViewById(R.id.btnSend);
        inputMsg = (EditText) findViewById(R.id.inputMsg);
        listVMsg = (ListView) findViewById(R.id.list_view_messages);
        
        utils = new Utils(getApplicationContext());
        
        //Getting the person name from previous screen
        Intent i = getIntent();
        name = i.getStringExtra("name");
        
        btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Sending message to web socket server
				sendMessageToServer(utils.getSendMessageJSON(inputMsg.getText().toString()));
				
				//clearing the input filed once message was sent
				inputMsg.setText("");
			}
		});
        
        listMsges = new ArrayList<Msg>();
        
        msgAdapter = new MsgListAdapter(this, listMsges);
        listVMsg.setAdapter(msgAdapter);
        
        client = new WebSocketClient(URI.create(WsConfig.URL_WEBSOCKET + URLEncoder.encode(name)), new WebSocketClient.Listener() {
			
			@Override
			public void onMessage(byte[] data) {
				// TODO Auto-generated method stub
				Log.d(TAG, String.format("Got binary message! %s", bytesToHex(data)));
	        	//Message will be in JSON format
	        	parseMessage(bytesToHex(data));
				
			}
			
			@Override
			public void onMessage(String message) {
				// TODO Auto-generated method stub
				Log.d(TAG, String.format("Got string message! %s", message));
	        	parseMessage(message);
				
			}
			
			@Override
			public void onError(Exception error) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDisconnect(int code, String reason) {
				// TODO Auto-generated method stub
				String msg = String.format(Locale.US, "Disconnected! Code: %d Reason: %s", code, reason);
	        	
	        	showToast(message);
	        	
	        	//clear the session id from shared preferences
	        	utils.storeSessionId(null);
				
			}
			
			@Override
			public void onConnect() {
				// TODO Auto-generated method stub
				
			}
		}, null);
        client.connect();
    }
        
        
        /**
         * On receiving the message from web socket server
         */
        @Override
        public void onMsg(String message) {
        	
        }
        
        @Override
        public void onMsg(byte[] data) {
        	
        }
        
        /**
         * Called when the connection is terminated
         */
        @Override
        public void onDisconnect(int code, String reason) {
        	
        }
        
        @Override
        public void onError(Exception error) {
        	
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
