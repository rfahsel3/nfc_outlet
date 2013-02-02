package com.ryanjfahsel.nfcoutlet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class Tool extends Activity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//Removes title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.tool);
		
		//Get data out of Bundles
		Bundle bundle = getIntent().getExtras();
		String toolName = bundle.getString("toolName");
		String toolId = bundle.getString("toolId");
		String toolDescr = bundle.getString("toolDescr");
		
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
		
		
		
	}
	
}
