package com.app.mydoctor.AppInterface;

import com.android.volley.NetworkResponse;


/**
 * Created by Rakshith on 10/13/2015.
 */
public interface ServerResponse {

    public void onServerResponse(String result);
    public void onServerError(String result);
    public void setLoading(boolean status);
    public boolean getLoading();


    public void parseNetworkResponse(NetworkResponse response);
}
