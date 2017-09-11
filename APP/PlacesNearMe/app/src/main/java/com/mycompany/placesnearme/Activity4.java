package com.mycompany.placesnearme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Activity4 extends ActionBarActivity {
   double lat;
   double lng;
   private TextView testview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
         lat = intent.getDoubleExtra(Activity3.EXTRA_LAT,0);
         lng = intent.getDoubleExtra(Activity3.EXTRA_LNG,0);




        //
        setContentView(R.layout.activity_activity4);

        this.testview = (TextView) this.findViewById(R.id.testview);

        new GetAllLocationText().execute(new ApiConnector());

    }

    //
    public void setText(JSONArray jsonArray)
    {
        String s = "";
        for(int i=0; i<jsonArray.length();i++){
            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                s=s +
                        "id:"+json.getString("id")+"\n"+"English name: "+json.getString("name_en")+"\nEnglish address: "+json.get("address_en")+"\nDistance: " + json.getInt("distance")+"m\n\n";
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        this.testview.setText(s);
    }

    private class GetAllLocationText extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params){
            return params[0].GetAllLocationText(lat,lng);
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray){
            setText(jsonArray);
        }



    }







    //

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
