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

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class NetworkActivity extends AsyncTask<Void,Void,String> {

	private String st;
	private TextView tv;
	private Activity activity;
	String postURL;
	String parameterList[][];
	
	public NetworkActivity(Activity mActivity, String postURL, String parameterList[][]){
		this.activity=mActivity;
		this.postURL=postURL;
		this.parameterList=parameterList;
	}
	
	
	protected String doInBackground(Void... params) {
		
		httpPost();
		Log.w("Got Here", "Got here");
		return st;
	}
	
	protected String onPostExecute()  {
		return st;
	}
	
	
	public void httpPost(){
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(postURL);
		
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			
			for(String iteration[]: parameterList)	{
				nameValuePairs.add(new BasicNameValuePair(iteration[0],iteration[1]));
			}
			
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
	
	
	

