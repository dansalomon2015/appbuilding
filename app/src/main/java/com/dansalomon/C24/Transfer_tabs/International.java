package com.dansalomon.C24.Transfer_tabs;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.dansalomon.C24.R;

import com.dansalomon.C24.Adapter.TransferListAdapter;
import com.dansalomon.C24.classes.TransferItem;
import com.dansalomon.C24.utils.JSONfunctions;
import com.dansalomon.C24.utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 15/02/2015.
 */
public class International extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    ProgressDialog mProgressDialog;
    JSONArray jsonarray;
    ListView listView;
    JSONObject jsonObject;
    List<TransferItem> transferItems;
    LayoutInflater layoutInflater;
    TransferListAdapter adapter;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInflater = inflater;
        View v =inflater.inflate(R.layout.international,container,false);
        onRefresh();
        return v;

    }

    @Override
    public void onRefresh() {
        if (NetworkUtil.getConnectivityStatus(getActivity().getApplicationContext()) == 0) {
            Toast.makeText(getActivity().getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            swipeLayout.setRefreshing(false);
        } else {
            new DownloadJSON().execute();
        }
    }


    public class DownloadJSON extends AsyncTask<Void, Void ,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setMessage("Chargement...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }



        @Override
        protected Void doInBackground(Void... params) {
            transferItems = new ArrayList<TransferItem>();
            // Retrieve JSON Objects from the given URL address
            jsonarray = JSONfunctions.getArrayfromURL("http://compare24.livehost.fr/c24service.php?action=compareTransfer&dest=Ghana&montant=5000");

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

            View view = layoutInflater.inflate(R.layout.international, null);

            listView = (ListView) view.findViewById(R.id.international_listView);
            // Pass the results into ListViewAdapter.java
            adapter = new TransferListAdapter(getActivity().getApplicationContext(), transferItems);
            // Set the adapter to the ListView
            listView.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }

    }
}
