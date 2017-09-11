package com.mycompany.placesnearme;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import com.google.android.gms.common.api.GoogleApiClient;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Activity3 extends ActionBarActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    Marker markermy;

   public final static String EXTRA_LAT = "com.mycompany.placesnearme.LAT";
   public final static String EXTRA_LNG = "com.mycompany.placesnearme.LNG";
    //1234
    double lat;
    double lng;
    protected static final String TAG = "basic-location-sample";
    public Location mLastLocation;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    GoogleApiClient mGoogleApiClient;
    Marker marker;
    List<Marker> markerList = new ArrayList<Marker>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {



       // this.lat=mLastLocation.getLatitude();
       // this.lng=mLastLocation.getLongitude();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_activity3);
        setUpMapIfNeeded();
        //1234
        //sendMessage();
        //123
//radioB
        RadioButton radioButtonkm = (RadioButton) findViewById(R.id.radioButton);
        RadioButton radioButtonall = (RadioButton) findViewById(R.id.radioButton2);

        radioButtonkm.setChecked(true);

//radioB
       // new GetAllLocation().execute(new ApiConnector());
       // 123




    }

    //1234










    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity3, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Int) {
            intiate();
        }
        if (id==R.id.action_Findme){
            findMe();
        }


        return super.onOptionsItemSelected(item);
    }

    //123





    public void setText(JSONArray jsonArray)
    {

        for(int i=0; i<jsonArray.length();i++){
            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                marker = mMap.addMarker(new MarkerOptions().position(new LatLng(json.getDouble("lat"), json.getDouble("lng"))).title(json.getString("name_en")).icon(BitmapDescriptorFactory.fromResource(R.drawable.toilet)));
                //marker = mMap.addMarker(new MarkerOptions().position(new LatLng(json.getDouble("lat"), json.getDouble("lng"))).title(json.getString("name_en")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                markerList.add(marker);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }
    private class GetAllLocation extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        //radioB


        //radioB
        @Override
        protected JSONArray doInBackground(ApiConnector... params){

            return params[0].GetAllLocation(lat,lng);
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray){
            setText(jsonArray);
        }



    }
   //123

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
       buildGoogleApiClient();
     //  this.lat=mLastLocation.getLatitude();
     //  this.lng=mLastLocation.getLongitude();
        //123

        //123
      //  mMap.setMyLocationEnabled(true);

    }
   ///123

   public void textOnClick(View view){


       Intent getFourthScreen = new Intent(this, Activity4.class);
     //  getFourthScreen.putExtra("Activity4","Activity3");
       getFourthScreen.putExtra(EXTRA_LAT, lat);
       getFourthScreen.putExtra(EXTRA_LNG, lng);
       startActivity(getFourthScreen);

   }


    //123

   protected synchronized void buildGoogleApiClient() {
  // protected   void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();



      //  sendMessage();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected void findMe(){

        if(markermy!=null) {
            markermy.remove();
        }
        if (mLastLocation != null) {
            //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
            markermy =mMap.addMarker(new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())).title("Me"));
            markerList.add(markermy);
            LatLng ll = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
            mMap.animateCamera(update);
        }
    }
    protected void intiate(){


        RadioButton radioButtonkm = (RadioButton) findViewById(R.id.radioButton);
        RadioButton radioButtonall = (RadioButton) findViewById(R.id.radioButton2);

        if (radioButtonkm.isChecked()){
            this.lat=mLastLocation.getLatitude();
        }
        if (radioButtonall.isChecked())   {
            this.lat=0;
        }
        mMap.clear();

        markerList.clear();
       if(mLastLocation!=null) {
           mMap.addMarker(new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())).title("Me"));
       }

        new GetAllLocation().execute(new ApiConnector());
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.

       mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        if(mLastLocation != null){
            Toast.makeText(this,"Current Location is available", Toast.LENGTH_LONG).show();
            this.lat=mLastLocation.getLatitude();
            this.lng=mLastLocation.getLongitude();

            mMap.addMarker(new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())).title("Me"));
            markerList.add(markermy);
            LatLng ll = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
            mMap.animateCamera(update);
        }
        else {
            Toast.makeText(this, "Current location is Not available", Toast.LENGTH_LONG).show();
            this.lat=0;
            this.lng=0;
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }



   ///
    /*
    @Override

    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    */
}
