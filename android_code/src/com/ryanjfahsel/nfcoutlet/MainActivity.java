package com.ryanjfahsel.nfcoutlet;



import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import com.ryanjfahsel.nfcoutlet.R;
//Designed by Ryan, Colin, and Ramya
public class MainActivity extends Activity {
	MainActivity mActivity = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button = (Button) findViewById(R.id.button_send);
	    button.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            // Perform action on click
	        	new NetworkActivity(mActivity).execute();
	        	//Add Text
	        	TextView text = (TextView)findViewById(R.id.tv1);
	        	
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	

}
