package com.dansalomon.C24;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dansalomon.C24.utils.NetworkUtil;

/**
 * Created by Edwin on 15/02/2015.
 */
public class WelcomeActivity extends ActionBarActivity {

    // Declaring Your View and Variables
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();
        setContentView(R.layout.activity_main);

        Button btnconnect = (Button) findViewById(R.id.btnconnect);

        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (NetworkUtil.getConnectivityStatus(context) == 0) {
                    Toast.makeText(context, R.string.no_internet, Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(WelcomeActivity.this, Service.class));
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

}