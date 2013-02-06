package com.ryanjfahsel.nfcoutlet;



import java.util.concurrent.ExecutionException;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.ryanjfahsel.nfcoutlet.R;
//Designed by Ryan, Colin, and Ramya
public class MainActivity extends Activity {
	MainActivity mActivity = this;
	//Login Class
	Login LoginActivity=new Login();
	
	public static final String PREF_FILE_NAME = "PrefFile";
	NfcAdapter mAdapter;
	PendingIntent mPendingIntent;
	IntentFilter ndef;
	IntentFilter[] mFilters;
	String[][] mTechLists;
	Intent intent;
	String nfcid;
	String username;
	String password;
	SharedPreferences preferences;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Removes title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
		//Vars
		username = preferences.getString("Unm","not found");
		password = preferences.getString("Psw","not found");
		
		//NFC Code
		mAdapter = NfcAdapter.getDefaultAdapter(this);
		mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
		try {
            ndef.addDataType("*/*");
        } catch (MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[] {
                ndef,
        };

        // Setup a tech list for all NfcF tags
        mTechLists = new String[][] { new String[] { MifareUltralight.class.getName(), Ndef.class.getName(), NfcA.class.getName()},
                new String[] { MifareClassic.class.getName(), Ndef.class.getName(), NfcA.class.getName()}};
        
        intent = getIntent();
        
		
	    
	    //LogoutButton Code
	    final Button logoutButton = (Button) findViewById(R.id.button_logout);
	    logoutButton.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		// Perform action on click
	    		LoginActivity.logoutUser(preferences, mActivity);
	    		
	  	  	}
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	//This is where stuff happens after recognizing nfc tag
	public void resolveIntent(Intent intent) {
	
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        
        byte[] id = tagFromIntent.getId();
        String idStr = tagFromIntent.toString();
        
      
        Log.w("Tag Id", id.toString());
        Log.w("Tag IdStr", idStr);
        String nfcid = bytesToHexString(id);
        Log.w("test", nfcid);
        
        final String paramList2[][] = {{"nfcid",nfcid},{"username", username},{"password",password}};
        new NetworkActivity( mActivity, "http://nfc.ryanjfahsel.com/requestToStart.php", paramList2, "MainActivity").execute();
      //Add Text
      //TextView text = (TextView)findViewById(R.id.tv1);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.w("Foreground dispatch", "Discovered tag with intent: " + intent);
        resolveIntent(intent);            
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }

        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);  
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);  
            stringBuilder.append(buffer);
        }

        return stringBuilder.toString();
    }
    //authenticates to use tool
    public void authTool(String str){
    	//splits str by ","
    	Log.w("AuthTool", str);
    	String [] arrayStr = str.split(",");
    	if(arrayStr[0].equals("1")){
    		//Starts Tool Activity
    		Intent intent = new Intent(MainActivity.this, Tool.class);
    		Bundle bundle = new Bundle();
    		bundle.putString("toolId", arrayStr[1]);
    		bundle.putString("toolName", arrayStr[2]);
    		bundle.putString("toolDescr", arrayStr[3]);
    		intent.putExtras(bundle);
			startActivity(intent);
    	}
    	else{
    		Context context = getApplicationContext();
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, arrayStr[0], duration);
			Log.w("authTool",arrayStr[0] );
			toast.show();
    	}
    }

}
