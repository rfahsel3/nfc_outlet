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

public class NetworkActivity extends AsyncTask<Void,Void,Void> {

	private String st;
	private Login LoginActivity;
	private MainActivity mActivity;
	private Tool ToolActivity;
	String postURL;
	String parameterList[][];
	String activityType;
	
	public NetworkActivity(Activity mActivity, String postURL, String parameterList[][], String activityType){
		if(activityType.equals("Login")){
			Log.w("Hey",mActivity.getClass().getName());
			this.LoginActivity=(Login) mActivity;
			this.postURL=postURL;
			this.parameterList=parameterList;
			this.activityType=activityType;
		}
		else if(activityType.equals("MainActivity")){
			Log.w("Hey2",mActivity.getClass().getName());
			this.mActivity= (MainActivity) mActivity;
			this.postURL=postURL;
			this.parameterList=parameterList;
			this.activityType=activityType;
		}
		else if(activityType.equals("Tool")){
			Log.w("Hey2",mActivity.getClass().getName());
			this.ToolActivity= (Tool) mActivity;
			this.postURL=postURL;
			this.parameterList=parameterList;
			this.activityType=activityType;
		}
	}
	
	
	protected Void doInBackground(Void... params) {
		
		httpPost();
		return null;
	}
	
	protected void onPostExecute(Void Result){
		if(activityType.equals("Login")){
			LoginActivity.auth(st);
		}
		else if(activityType.equals("MainActivity")){
			mActivity.authTool(st);
		}
		else if(activityType.equals("Tool")){
			ToolActivity.authStop(st);
		}
	}
	
	
	public void httpPost(){
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(postURL);
		
		try{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			
			for(String iteration[]: parameterList)	{
				Log.w("Iterate", "Iterate");
				nameValuePairs.add(new BasicNameValuePair(iteration[0],iteration[1]));
				Log.w("Ryan",iteration[0]);
				Log.w("Ryan",iteration[1]);
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
	
	
	

