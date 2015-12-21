package com.app.mydoctor.WebService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;


import org.apache.http.entity.mime.MultipartEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.app.mydoctor.AppInterface.ServerResponse;
import com.app.mydoctor.R;

/**
 * Created by Rakshith on 9/25/2015.
 */
public class ConnectWebService {

    ServerResponse serverResponse;
    Context context;

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    //default is zero
    public  int TYPE=0;
    public void setOnServerResponse(ServerResponse serverResponse){
        this.serverResponse=serverResponse;
    }
    public void setContext(Context context){
        this.context=context;
    }
    public Context getContext(){
        return context;
    }
    public void jsonObjectGetRequest(String url, final Activity activity) {

        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        Toast.makeText(activity, "response=" + response, Toast.LENGTH_LONG).show();
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(activity, "error=" + error, Toast.LENGTH_LONG).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    public void jsonArrayGetRequest(String url, final Activity activity) {


        String tag_json_arry = "json_array_req";


        final ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);

    }


    public void stringGetRequest(String url, final Activity activity, final boolean dialog) {
        String tag_string_req = "string_req";
        final ProgressDialog  pDialog;
        if(activity!=null)
            pDialog= new ProgressDialog(activity);
       else
            pDialog=null;

        if(dialog && activity!=null) {

            pDialog.setMessage("Loading...");
            pDialog.show();
        }



        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {
                if(dialog && activity!=null)
                   pDialog.hide();

               /* if(ConnectWebService.this.getTYPE()== AppGcm.GCM_SEND)
                if(response.equals(" {\"device_det_detail\":\"success\"}")){
                    AppPreferences.storePreference(true, AppPreferences.GCM_SENT, context);
                }*/

               // Toast.makeText(activity, "response=" + response, Toast.LENGTH_LONG).show();
                if(serverResponse!=null) {
                    serverResponse.setLoading(false);
                    serverResponse.onServerResponse(response);
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(dialog && activity!=null)
                    pDialog.hide();
                serverResponse.setLoading(false);
                if(serverResponse!=null)
                   // Toast.makeText(activity, "response=" + error, Toast.LENGTH_LONG).show();
                    serverResponse.onServerError(error+"");

            }

        }




        ){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if(serverResponse!=null)
                   serverResponse.parseNetworkResponse(response);


                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Mashape-Key", "cKTJmf96mhmshllWLSh44x7Zxtftp1nmPBcjsncunA2fsDXxHY");
                headers.put("Accept", "application/json");
                return headers;
            }




        };



       /* strReq.setRetryPolicy(new DefaultRetryPolicy(
                1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }


    public void jsonArrayPostRequest(String url, final Activity activity, final Map<String, String> params) {

        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.show();

        CustomJsonArrayRequest jsonObjReq = new CustomJsonArrayRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        pDialog.hide();
                        Toast.makeText(activity, "response=" + response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.hide();
                Toast.makeText(activity, "response=" + error, Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    public void jsonObjectPostRequest(String url, final Activity activity, final Map<String, String> params) {

        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.show();

        CustomJsonObjectRequest jsonObjReq = new CustomJsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        pDialog.hide();
                        Toast.makeText(activity, "response=" + response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.hide();
                Toast.makeText(activity, "response=" + error, Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    public void stringPostRequest(String url, final Activity activity, final Map<String, String> params,final Boolean dialog) {

        String tag_json_obj = "json_obj_req";
        final ProgressDialog  pDialog;
        if(activity!=null)
            pDialog= new ProgressDialog(activity);
        else
            pDialog=null;

        if(dialog && activity!=null) {

            pDialog.setMessage("Loading...");
            pDialog.show();
        }


        CustomStringRequest jsonObjReq = new CustomStringRequest(Request.Method.POST,
                url, null,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        if(dialog && activity!=null)
                            pDialog.hide();
                       /*if(response!=null)
                        if(ConnectWebService.this.getTYPE()== AppGcm.GCM_SEND)
                            if(response.trim().equals("{\"device_det_detail\":\"success\"}") || response.trim().equals("{\"device_det_detail\":\"update\"}")){
                                AppPreferences.storePreference(true, AppPreferences.GCM_SENT, context);
                            }*/
                        //Toast.makeText(activity, "response=" + response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if(dialog && activity!=null)
                    pDialog.hide();
              //  Toast.makeText(activity, "response=" + error, Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    public void postRequest_File(String url, final Activity activity, File file) {

        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.show();

        //RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        MultipartRequestTest jsonObjReq = new MultipartRequestTest(Request.Method.POST,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        Toast.makeText(activity, "response=" + response, Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Toast.makeText(activity, "response=" + error, Toast.LENGTH_LONG).show();

            }
        }, file);

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


       // queue.add(jsonObjReq);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }




    public void postRequest_Entity(String url, final Activity activity, MultipartEntity entity) {

        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.show();

        //RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        MultipartRequestTest jsonObjReq = new MultipartRequestTest(Request.Method.POST,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        Toast.makeText(activity, "response=" + response, Toast.LENGTH_LONG).show();
                        Log.d("tag", response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Toast.makeText(activity, "response=" + error, Toast.LENGTH_LONG).show();

            }
        }, entity);

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        // queue.add(jsonObjReq);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public void volleyNetworkInageLoader(NetworkImageView networkImageView, String url) {
        if(networkImageView.getDrawable()==null)
            networkImageView.setImageResource(R.mipmap.loading);

        ImageLoader  imageLoader=AppController.getInstance().getImageLoader();
        networkImageView.setImageUrl(url, imageLoader);
    }
}