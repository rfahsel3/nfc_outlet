package com.ryanjfahsel.nfcoutlet;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ryanjfahsel.nfcoutlet.R;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class NetworkActivity extends AsyncTask<Void,Void,Void> {

	private String st;
	private TextView tv;
	private MainActivity mActivity;
	public NetworkActivity(MainActivity mActivity)
	{
		this.mActivity=mActivity;
	}
	
	protected Void doInBackground(Void... params) {
		postData("http://www.ryanjfahsel.com/index.php", "902563529","rfahsel3","Katie sucks");
		Log.w("Got Here", "Got here");
		return null;
	}
	
	protected void onPostExecute(Void result)  {
		tv = (TextView)mActivity.findViewById(R.id.tv1);
		tv.setText(st);
	}
	
	
	public void postData(String postURL, String nfc_id, String username, String passwd){
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
			st=total.toString();
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
	
	
	

