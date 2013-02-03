package com.ryanjfahsel.nfcoutlet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tool extends Activity {
	public static final String PREF_FILE_NAME = "PrefFile";
	private TextView tv;
	Login LoginActivity=new Login();
	Tool ToolActivity = this;
	private MainActivity mActivity;
	SharedPreferences preferences;
	String toolId;
	String toolName;
	String toolDescr;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//Set preferences
		preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
		
		//Removes title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.tool);
		
		//Get data out of Bundles
		Bundle bundle = getIntent().getExtras();
		toolName = bundle.getString("toolName");
		toolId = bundle.getString("toolId");
		toolDescr = bundle.getString("toolDescr");
		
		Log.w("Tool",toolName);
		Log.w("Tool", toolId);
		Log.w("Tool", toolDescr);
		
		//Set toolName TextView
		TextView toolName_tv = (TextView)findViewById(R.id.tool_name_tv);
		toolName_tv.setText(toolName);
		//Set toolId TextView
		TextView toolId_tv = (TextView)findViewById(R.id.tool_id_tv);
		toolId_tv.setText(toolId);
		//Set toolDescr TextView
		TextView toolDescr_tv = (TextView)findViewById(R.id.tool_descr_tv);
		toolDescr_tv.setText(toolDescr);
		
		//Create paramListTool to pass in HTTPPost
		final String paramListTool[][] = {{"tid",toolId}};
		
		//LogoutButton Code
	    final Button logoutButton = (Button) findViewById(R.id.button_logout);
	    logoutButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		// Perform action on click
	    		new NetworkActivity(ToolActivity, "http://ryanjfahsel.com/stop.php", paramListTool, "Tool").execute();
	    		LoginActivity.logoutUser(preferences, ToolActivity);
	    		
	  	  	}
	    });
	    
	  //stopToolButton Code
	    final Button stopToolButton = (Button) findViewById(R.id.stopTool_Button);
	    stopToolButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		// Perform action on click
	    		// HTTPPost to Stop
	    		
	    		new NetworkActivity(ToolActivity, "http://ryanjfahsel.com/stop.php", paramListTool, "Tool").execute();
	    		
	  	  	}
	    });
		
	}
	
	public void authStop(String str){
		if(str.equals("1")){
			//Successfully Stopped machine
			Intent intent = new Intent(Tool.this, MainActivity.class);
			startActivity(intent);
			
			//Set text view stating successful stop
			String msg = "Thanks for stopping "+ toolName +" to use another tool tap your phone on a registered nfc sticker";
			tv=(TextView)mActivity.findViewById(R.id.tv1);
			tv.setText(msg);
		}
		else{
			//If machine was not turned off, send message
			Context context = getApplicationContext();
			int duration = Toast.LENGTH_LONG;
			
			Toast toast = Toast.makeText(context, str, duration);
			toast.show();
		}
	}
	
}
