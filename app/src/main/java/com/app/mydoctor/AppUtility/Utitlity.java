package com.app.mydoctor.AppUtility;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Rakshith on 11/11/2015.
 */
public class Utitlity {

   public static String getDeviceId(Context context){
       String deviceId = Settings.Secure.getString(context.getContentResolver(),
               Settings.Secure.ANDROID_ID);
       return deviceId;
   }

    public static boolean isNetworkAvailable_UI(final Activity activity) {

        boolean net_status;
        if (activity == null)
            return false;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // if no network is available networkInfo will be null, otherwise check
        // if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            net_status= true;
        }else{
            net_status= false;



            Toast.makeText(activity,"Connect to Internet",Toast.LENGTH_SHORT).show();

        }



        return net_status;
    }


}
