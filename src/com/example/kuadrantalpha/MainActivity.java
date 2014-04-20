package com.example.kuadrantalpha;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity{
	
	Button locationButton;

	
	LocationManager locationManager;
	LocationListener ll;
	String provider;
	IncomingSms BR_smsreceiver = null;
	String savedNumber = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		locationButton = (Button) findViewById(R.id.bLocation);

		
		locationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getLocation("");
				
			}
		});
		

		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		ll = new myLocationListener();
		
		BR_smsreceiver = new IncomingSms();
        BR_smsreceiver.setMainActivityHandler(this);
        IntentFilter fltr_smsreceived = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(BR_smsreceiver,fltr_smsreceived);  

	}
	
	//Stuff related to location

	public void getLocation(String number){
		boolean GPSworks = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean NetworkWorks = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		
		savedNumber =  number;
		
		if(NetworkWorks){
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0, ll);
		}else if(GPSworks){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, ll);
		}
	}
	
	class myLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			if(location != null){
				double pLong = location.getLongitude();
				double pLat = location.getLatitude();
				locationManager.removeUpdates(myLocationListener.this);
//				Context context = getApplicationContext();
//				CharSequence text = "Location: "+Double.toString(pLong)+" "+Double.toString(pLat);
				String text = Double.toString(pLong)+";"+Double.toString(pLat);
//				int duration = Toast.LENGTH_SHORT;

//				Toast toast = Toast.makeText(context, text, duration);
//				toast.show();
				if(!savedNumber.equals("")){
					sendSMS(savedNumber, text);
				}else{
					Context context = getApplicationContext();
					CharSequence text2 = "Location: "+Double.toString(pLong)+" "+Double.toString(pLat);
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text2, duration);
					toast.show();
				}
			}
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//SMS sending
	
	private void sendSMS(String phoneNumber, String message)
    {        
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);        
    }   
	
	
}
