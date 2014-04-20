package com.example.kuadrantalpha;

import com.example.kuadrantalpha.MainActivity.myLocationListener;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationListener;
import android.location.LocationManager;

public class SMSService extends IntentService{
	
	LocationManager locationManager;
	LocationListener ll;
	String provider;
	IncomingSms BR_smsreceiver = null;
	String savedNumber = "";

	public SMSService(String name) {
		super(name);
	}
	
	
	
	@Override
	public void onCreate() {
//		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		
//		ll = new myLocationListener();
//		
//		BR_smsreceiver = new IncomingSms();
//        BR_smsreceiver.setMainActivityHandler(this);
//        IntentFilter fltr_smsreceived = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
//        registerReceiver(BR_smsreceiver,fltr_smsreceived);  
		super.onCreate();
	}



//	public void getLocation(String number){
//		boolean GPSworks = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//		boolean NetworkWorks = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//		
//		savedNumber =  number;
//		
//		if(NetworkWorks){
//			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0, ll);
//		}else if(GPSworks){
//			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, ll);
//		}
//	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
	}

}
