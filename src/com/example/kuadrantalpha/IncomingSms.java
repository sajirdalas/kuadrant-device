package com.example.kuadrantalpha;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver{

	final SmsManager sms = SmsManager.getDefault();
	MainActivity main = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		final Bundle bundle = intent.getExtras();
		 
		try {
		     
		    if (bundle != null) {
		         
		        final Object[] pdusObj = (Object[]) bundle.get("pdus");
		         
		        for (int i = 0; i < pdusObj.length; i++) {
		             
		            SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
		            String phoneNumber = currentMessage.getDisplayOriginatingAddress();
		             
		            String senderNum = phoneNumber;
		            String message = currentMessage.getDisplayMessageBody();
		 
		            Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
		             
		 
		           // Show alert
//		            int duration = Toast.LENGTH_LONG;
//		            Toast toast = Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, duration);
//		            toast.show();
		            
		            if(message.equals("getLocation")){
		            
		            	main.getLocation(senderNum);
		            
		            }
		             
		        } // end for loop
		      } // bundle is null
		 
		} catch (Exception e) {
		    Log.e("SmsReceiver", "Exception smsReceiver" +e);
		     
		}
		
	}

	void setMainActivityHandler(MainActivity main){
	    this.main=main;
	}
	
	
		
}
