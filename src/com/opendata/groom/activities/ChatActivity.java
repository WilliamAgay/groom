package com.opendata.groom.activities;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class ChatActivity extends Activity implements IOCallback {

	private final Handler handler = new Handler();
	public ListView msgView;
	public ArrayAdapter<String> msgList;
	SocketIO socket;

	// public ArrayAdapter<String> msgList=new ArrayAdapter<String>(this,
	// android.R.layout.simple_list_item_1);;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		// msgView = (ListView) findViewById(R.id.listView);
		//
		// msgList = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1);
		// msgView.setAdapter(msgList);
		//
		// // msgView.smoothScrollToPosition(msgList.getCount() - 1);
		//
		// Button btnSend = (Button) findViewById(R.id.btn_Send);
		//
		// receiveMsg();
		// btnSend.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// final EditText txtEdit = (EditText) findViewById(R.id.txt_inputText);
		// //msgList.add(txtEdit.getText().toString());
		// sendMessageToServer(txtEdit.getText().toString());
		// msgView.smoothScrollToPosition(msgList.getCount() - 1);
		//
		// }
		// });

		((EditText) findViewById(R.id.txt_inputText)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        if (actionId == EditorInfo.IME_ACTION_SEND) {
		        	if(socket!=null && socket.isConnected())
					{
						socket.emit("sendchat", ((EditText) findViewById(R.id.txt_inputText)).getText().toString());
					}
					else
					{
						Toast.makeText(ChatActivity.this, "En attente de connexion..", Toast.LENGTH_SHORT).show();
						connectSocket();
					}
		            return true;
		        }
		        return false;
		    }
		});
		
		

		// receiveMsg();
		// ----------------------------
		// server msg receieve
		// -----------------------
		connectSocket();
		
	}

	
	public void connectSocket()
	{
		try {
			socket = new SocketIO("http://87.106.98.48:3000");

			socket.connect(this);
			socket.emit("adduser",( (GroomApplication)getApplicationContext()).accountName);
			// End Receive msg from server/
		} catch (Exception e) {
			Log.e("oncreate", e.getMessage());
		}
	}
	@Override
	public void onDisconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(String paramString, IOAcknowledge paramIOAcknowledge) {
		
			writeMessage(((GroomApplication)getApplicationContext()).accountName, paramString);
		
	}

	@Override
	public void onMessage(JSONObject paramJSONObject,
			IOAcknowledge paramIOAcknowledge) {
		Log.d("onMessage", "" + paramJSONObject);
		
		if (paramJSONObject != null && !paramJSONObject.isNull("args")) {
			try {
				JSONArray arr = (JSONArray) paramJSONObject.get("args");
				JSONObject obj = (JSONObject) arr.get(0);
				if (obj.has("toto"))
				{
					writeMessage("toto", obj.getString("toto"));
				}
				if (obj.has(((GroomApplication)getApplicationContext()).accountName))
				{
					writeMessage(((GroomApplication)getApplicationContext()).accountName, obj.getString(((GroomApplication)getApplicationContext()).accountName));
				}
				if (obj.has("SERVER"))
				{
					writeMessage(((GroomApplication)getApplicationContext()).accountName, obj.getString("SERVER"));
				}
					
			} catch (JSONException e) {
				Log.e("JSONException", e.getMessage());
			}
		}
		
	}

	@Override
	public void on(String paramString, IOAcknowledge paramIOAcknowledge,
			Object... paramArrayOfObject) 
	{
		if(paramArrayOfObject!=null && paramArrayOfObject.length>0)
		{
			try {
				
				if( paramArrayOfObject.length>1 && paramArrayOfObject[1].getClass()==String.class)
				{
					writeMessage((String) paramArrayOfObject[0],(String) paramArrayOfObject[1]);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e("Exception on",e.getMessage());
			}
		}
		
	}
	
	
	public void writeMessage(String emetteur, String theText)
	{
		String color ="#B98A24";
		if(emetteur!=null && emetteur.equals("toto"))
		{
			color ="#425155";
		}
		TextView tv = (TextView) findViewById(R.id.thistory);
		tv.setText(Html.fromHtml(tv.getText().toString() + "<br><font color=\""+color+"\"><b>" + emetteur+"</b></font>  " + theText));

		
		
	}

	@Override
	public void onError(SocketIOException paramSocketIOException) {
		// TODO Auto-generated method stub

	}
	// public void sendMessageToServer(String str) {
	//
	// final String str1=str;
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// //String host = "opuntia.cs.utep.edu";
	// String host="10.0.2.2";
	// String host2 = "127.0.0.1";
	// PrintWriter out;
	// try {
	// Socket socket = new Socket(host, 8008);
	// out = new PrintWriter(socket.getOutputStream());
	//
	// // out.println("hello");
	// out.println(str1);
	// Log.d("", "hello");
	// out.flush();
	// } catch (UnknownHostException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// Log.d("", "hello222");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// Log.d("", "hello4333");
	// }
	//
	// }
	// }).start();
	// }


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (socket.isConnected())
		{
			socket.disconnect();
		}
	}

	/*
	 * public void receiveMsg() { new Thread(new Runnable() {
	 * 
	 * @Override public void run() { // TODO Auto-generated method stub
	 * 
	 * //final String host="opuntia.cs.utep.edu"; final String host="10.0.2.2";
	 * //final String host="localhost"; Socket socket = null ; BufferedReader in
	 * = null; try { socket = new Socket(host,8008); } catch
	 * (UnknownHostException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * try { in = new BufferedReader(new
	 * InputStreamReader(socket.getInputStream())); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * while(true) { String msg = null; try { msg = in.readLine();
	 * Log.d("","MSGGG:  "+ msg);
	 * 
	 * //msgList.add(msg); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } if(msg == null) { break; } else {
	 * displayMsg(msg); } }
	 * 
	 * } }).start();
	 * 
	 * 
	 * }
	 * 
	 * public void displayMsg(String msg) { final String mssg=msg;
	 * handler.post(new Runnable() {
	 * 
	 * @Override public void run() { // TODO Auto-generated method stub
	 * msgList.add(mssg); msgView.setAdapter(msgList);
	 * msgView.smoothScrollToPosition(msgList.getCount() - 1); Log.d("","hi"); }
	 * });
	 * 
	 * }
	 */

}