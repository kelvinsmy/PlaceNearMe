package com.mycompany.placesnearme;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;

public class Activity2 extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity2);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void museumOnClick(View view) {
        Intent getThirdScreen = new Intent(this, Activity3.class);
        getThirdScreen.putExtra("Activity3", "Activity2");
        startActivity(getThirdScreen);
    }
}