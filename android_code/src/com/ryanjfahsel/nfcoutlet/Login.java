package com.ryanjfahsel.nfcoutlet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Window;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ryanjfahsel.nfcoutlet.R;

public class Login extends Activity {
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	Login loginActivity =this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		//Check to see if already logged in
		SharedPreferences username = getSharedPreferences(USERNAME, 0);
		SharedPreferences password = getSharedPreferences(PASSWORD, 0);
		if(username.getString("username", "").toString().length()>0 && password.getString("password","").toString().length()>0){
			Intent intent = new Intent(Login.this, MainActivity.class);
			startActivity(intent);
		}
		
		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText usernameEdit = (EditText) findViewById(R.id.usernameInput);
				EditText passwordEdit = (EditText) findViewById(R.id.passwordInput);
				if(usernameEdit.getText().toString().length()>0 && passwordEdit.getText().toString().length()>0){
				String networkMessage =new NetworkActivity(loginActivity).get();
					if(networkMessage.equals("1")){
						//Authenticated
						//Save username and password
						Editor usernameEditor = getSharedPreferences(USERNAME, 0).edit();
						Editor passwordEditor = getSharedPreferences(PASSWORD, 0).edit();
						usernameEditor.putString(USERNAME, usernameEditor.getClass().toString());
						passwordEditor.putString(PASSWORD, passwordEditor.getClass().toString());
						usernameEditor.commit();
						passwordEditor.commit();
						
						//Go to MainActicity
						Intent intent = new Intent(Login.this, MainActivity.class);
						startActivity(intent);
					}
					else{
						Context context = getApplicationContext();
						CharSequence text = "Incorrect username or password";
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
				}
				else{
					Context context = getApplicationContext();
					CharSequence text = "Please enter a username and password";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
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
