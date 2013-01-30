package com.ryanjfahsel.nfcoutlet;



import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import com.ryanjfahsel.nfcoutlet.R;
//Designed by Ryan, Colin, and Ramya
public class MainActivity extends Activity {
	MainActivity mActivity = this;
	public static final String PREF_FILE_NAME = "PrefFile";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
		
		String username = preferences.getString("Unm","not found");
		String password = preferences.getString("Psw","not found");
		final String paramList2[][] = {{"nfcid","902584465"},{"username", username},{"password",password}};
		final Button button = (Button) findViewById(R.id.button_send);
	    button.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            // Perform action on click
	        	
				new NetworkActivity( mActivity, "http://ryanjfahsel.com/index.php", paramList2, "MainActivity").execute();
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
