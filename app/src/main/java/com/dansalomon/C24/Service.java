package com.dansalomon.C24;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.dansalomon.C24.dialog.ComparaisonDialog;
import com.dansalomon.C24.dialog.MobileDialog;
import com.dansalomon.C24.dialog.OfficeDialog;
import com.dansalomon.C24.dialog.ProposerDialog;
import com.dansalomon.C24.dialog.TransfertDialog;
import com.dansalomon.C24.utils.JSONfunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Service extends ActionBarActivity {

    final CharSequence[] items = {"Data", "Prix"};
    final CharSequence[] items2 = {"50 Mo", "100 Mo", "200 Mo", "350 Mo", "500 Mo", "1Go - 5Go", "6Go - 10Go", "10Go - 30Go"};
    final CharSequence[] items3 = {"10Go", "30Go", "Illimité"};
    private List<String> country;
    private JSONObject jsonObject;
    private JSONArray jsonarray;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_service);
        setSupportActionBar(toolbar);

        new DownloadJSON().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service, menu);
        return true;
    }


    public void onTranfertItemClick(View view){

        TransfertDialog transfertDialog = new TransfertDialog();

        transfertDialog.show(getFragmentManager(), "");
    }

    public void onMobileItemClick(View view){


       final Context context = this;

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Comparer par : ");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if ( item == 1){
                    MobileDialog mobileDialog = new MobileDialog();
                    mobileDialog.show(getFragmentManager(), "");
                }
                else {

                    new AlertDialog.Builder(context)
                            .setTitle("Faites votre choix")
                            .setSingleChoiceItems(items2, 0, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                            startActivity(new Intent(context,Mobile.class));
                                   //Toast.makeText(context, ""+this.getClass(),Toast.LENGTH_LONG).show();
                                }
                            }).setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                }
            }
        });

        builder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

    public void onOfficeItemClick(View view){

        final Context context = this;

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Comparer par : ");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if ( item == 1){
                    OfficeDialog officeDialog = new OfficeDialog();

                    officeDialog.show(getFragmentManager(), "");
                }
                else {

                    new AlertDialog.Builder(context)
                            .setTitle("Faites votre choix")
                            .setSingleChoiceItems(items3, 0, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    startActivity(new Intent(context,OfficeHome.class));
                                    //Toast.makeText(context, ""+this.getClass(),Toast.LENGTH_LONG).show();
                                }
                            }).setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                            .show();

                }
            }
        });

        builder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.comparison_du_jour){

            ComparaisonDialog comparaisonDialog = new ComparaisonDialog();

            comparaisonDialog.show(getFragmentManager(), "");
        }

        if (id == R.id.proposer){

            ProposerDialog proposerDialog = new ProposerDialog();

            proposerDialog.show(getFragmentManager(), "");

        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public List<String> getCountry(){

        return  this.country;
    }

    public class DownloadJSON extends AsyncTask<Void, Void ,Void>{


        @Override
        protected Void doInBackground(Void... params) {
            country = new ArrayList<String>();
            // Retrieve JSON Objects from the given URL address
            jsonObject = JSONfunctions.getJSONfromURL("http://compare24.livehost.fr/c24service.php?action=liste_pays");

            try {
                // Locate the array name in JSON
                jsonarray = jsonObject.getJSONArray("");

                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    country.add(jsonObject.getString("nom_pays"));
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Service.this);
            // Set progressdialog title
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

    }

}
