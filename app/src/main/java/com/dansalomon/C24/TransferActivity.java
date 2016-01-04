package com.dansalomon.C24;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.dansalomon.C24.Adapter.TransferListAdapter;
import com.dansalomon.C24.classes.TransferItem;
import com.dansalomon.C24.utils.JSONfunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TransferActivity extends ActionBarActivity{

    private ProgressDialog mProgressDialog;
    private List<TransferItem> transferItems;
    private JSONArray jsonarray;
    private JSONObject jsonObject;
    private ListView listView;
    private TransferListAdapter adapter;
    private String dest;
    private Double amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_service);
        setSupportActionBar(toolbar);

        String destination = getIntent().getStringExtra("dest");
        getSupportActionBar().setTitle("Destination : "+destination);
        setAmount(getIntent().getDoubleExtra("amount",0));
        setDest(customToLowerCase(destination));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new DownloadJSON().execute();
    }

    public String customToLowerCase(String word){

        int taille = word.length();
        Log.d("TAG",taille+"voilà la nouvelle valeur : "+word);

        String result = "";

        for (int i = 0; i< taille;i++){

            Character character = word.charAt(i);
            Log.d("TAG","le valalalalalala : "+character);

            if(character != '\'' && character != '-' && !character .equals(' ')){

                result += character.toString().toLowerCase();
            }
        }

        return result;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public class DownloadJSON extends AsyncTask<Void, Void ,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(TransferActivity.this);
            // Set progressdialog title
            mProgressDialog.setMessage("Chargement...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        public void rangeArray(JSONArray jsonArray){

            JSONObject c = new JSONObject();

            for(int i = 0; i < jsonarray.length(); i++){

                for(int j = i+1; j <jsonarray.length(); j++){

                    try {

                        if(jsonArray.getJSONObject(j).getDouble("cout") < jsonArray.getJSONObject(i).getDouble("cout")) {

                            c = jsonArray.getJSONObject(j);
                            jsonArray.put(j,jsonArray.getJSONObject(i));
                            jsonArray.put(i,c);
                            Log.d("TAG","hello "+c.getDouble("cout"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            transferItems = new ArrayList<TransferItem>();
            // Retrieve JSON Objects from the given URL address
            jsonarray = JSONfunctions.getArrayfromURL("http://compare24.livehost.fr/c24service.php?action=compareTransfer&dest="+dest+"&montant="+amount+"");

            Log.d("TAG","http://compare24.livehost.fr/c24service.php?action=compareTransfer&dest="+dest+"&montant="+amount+"");
            rangeArray(jsonarray);

            try {

                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    TransferItem transferItem = new TransferItem();

                    transferItem.setAgence(jsonObject.getString("nom_agence"));
                    transferItem.setCout(jsonObject.getDouble("cout"));

                    Log.d("TAG",""+jsonObject.getDouble("cout"));

                    transferItems.add(transferItem);
                }

            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            listView = (ListView) findViewById(R.id.mylistView);
            // Pass the results into ListViewAdapter.java
            adapter = new TransferListAdapter(getBaseContext(), transferItems);
            // Set the adapter to the ListView
            listView.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }

    }

}
