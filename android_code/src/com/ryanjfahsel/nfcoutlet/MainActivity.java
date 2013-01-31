package com.ryanjfahsel.nfcoutlet;



import java.util.concurrent.ExecutionException;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
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
	NfcAdapter mAdapter;
	PendingIntent mPendingIntent;
	IntentFilter ndef;
	IntentFilter[] mFilters;
	String[][] mTechLists;
	Intent intent;
	String nfcid;
	String username;
	String password;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
		//Vars
		username = preferences.getString("Unm","not found");
		password = preferences.getString("Psw","not found");
		final String paramList2[][] = {{"nfcid","902584465"},{"username", username},{"password",password}};
		
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
        mTechLists = new String[][] { new String[] { MifareClassic.class.getName() } };
        
        intent = getIntent();
        
        
		
		//Temp Button Code
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
	
	public void resolveIntent(Intent intent) {
	// 1) Parse the intent and get the action that triggered this intent
    //String action = intent.getAction();
    // 2) Check if it was triggered by a tag discovered interruption.
    //  3) Get an instance of the TAG from the NfcAdapter
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
   // 4) Get an instance of the Mifare classic card from this TAG intent
        //MifareClassic mfc = MifareClassic.get(tagFromIntent);
        
        byte[] id = tagFromIntent.getId();
        String idStr = tagFromIntent.toString();
        
        
      
        Log.w("Tag Id", id.toString());
        Log.w("Tag IdStr", idStr);
        String nfcid = bytesToHexString(id);
        Log.w("test", nfcid);
        
        final String paramList2[][] = {{"nfcid",nfcid},{"username", username},{"password",password}};
        new NetworkActivity( mActivity, "http://ryanjfahsel.com/index.php", paramList2, "MainActivity").execute();
      //Add Text
    	TextView text = (TextView)findViewById(R.id.tv1);
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

}
