package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

//I added these pkgs
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button = (Button) findViewById(R.id.button_send);
	    button.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            // Perform action on click
	        	postData("902584465", "http://ryanjfahsel.com/index.php", "cgray9", "password123");
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void postData(String nfc_id, String postURL, String username, String passwd){
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(postURL);
		
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("nfcid", nfc_id));
			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("passwd", passwd));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String line = "";
		    StringBuilder total = new StringBuilder();
		    
		    // Wrap a BufferedReader around the InputStream
		    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		    // Read response until the end
		    while ((line = rd.readLine()) != null) { 
		        total.append(line); 
		    }
			
			Log.w("Response:", "GOT HERE");
		    Log.w("Response: ", total.toString());
			Log.w("RESPONSE", "GOT HERE");
		}
		catch(ClientProtocolException e){
			//TODO
		}
		catch(IOException e){
			//TODO
		}
	}

}
