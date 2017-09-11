package com.mycompany.placesnearme;
import android.content.Intent;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;



public class ApiConnector extends Activity3
        {



    public JSONArray GetAllLocation(double lat, double lng) {



        String url = "";

        if (lat==0)
     {
         url = "http://localhost/json-toiletall.php";
     }
        else{
         url = "http://localhost/json-toilet.php?lat="+lat+"&lng="+lng;
     }





        HttpEntity httpEntity = null;

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            httpEntity = httpResponse.getEntity();


        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = null;
        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("Entity Response :", entityResponse);
                jsonArray = new JSONArray(entityResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;

    }
 //
 public JSONArray GetAllLocationText(double lat, double lng) {


     String url = "";

     if (lat==0)
     {
         url = "http://localhost/json-toiletall1.php";
     }
     else{
         url = "http://localhost/json-toilet1.php?lat="+lat+"&lng="+lng;
     }












     HttpEntity httpEntity = null;

     try {
         DefaultHttpClient httpClient = new DefaultHttpClient();
         HttpGet httpGet = new HttpGet(url);

         HttpResponse httpResponse = httpClient.execute(httpGet);

         httpEntity = httpResponse.getEntity();


     } catch (ClientProtocolException e) {
         e.printStackTrace();

     } catch (IOException e) {
         e.printStackTrace();
     }

     JSONArray jsonArray = null;
     if (httpEntity != null) {
         try {
             String entityResponse = EntityUtils.toString(httpEntity);
             Log.e("Entity Response :", entityResponse);
             jsonArray = new JSONArray(entityResponse);
         } catch (JSONException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     return jsonArray;

 }




  //


}
