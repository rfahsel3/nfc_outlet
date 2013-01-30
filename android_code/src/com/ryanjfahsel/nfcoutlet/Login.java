package com.ryanjfahsel.nfcoutlet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ryanjfahsel.nfcoutlet.R;

public class Login extends Activity {
	public static final String PREFS_NAME = "LoginPrefsFile";
	Login loginActivity =this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		//Check to see if already logged in
		SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
		if(prefs.getString("logged", "").toString().equals("logged")){
			Intent intent = new Intent(Login.this, MainActivity.class);
			startActivity(intent);
		}
		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText username = (EditText) findViewById(R.id.usernameInput);
				EditText password = (EditText) findViewById(R.id.passwordInput);
				if(username.getText().toString().length()>0 && password.getText().toString().length()>0){
					new NetworkActivity(loginActivity).execute();
				}
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
