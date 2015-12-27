package com.dansalomon.C24.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.dansalomon.C24.R;
import com.dansalomon.C24.Service;
import com.dansalomon.C24.Transfer;
import com.dansalomon.C24.TransferActivity;
import com.dansalomon.C24.utils.JSONfunctions;

import java.util.List;


/**
 * Dialog Created by Dan Salomon on 25/10/2015.
 */
public class TransfertDialog extends DialogFragment {

    private Context context;

    public Dialog onCreateDialog (Bundle SavedInstaceState){

        super.onCreate(SavedInstaceState);

        List<String> country = ((Service)getActivity()).getCountry();

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        builder.setTitle("Transfert d'argent");

        context = this.getActivity().getApplicationContext();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>((this.getActivity().getApplicationContext()),R.layout.item_country_textview,country);


        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        final View view = inflater.inflate(R.layout. dialog_transfer, null);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.transferCountry);

        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView .setTextColor(Color.BLACK);


        builder.setView(view);


        builder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText editText = (EditText) view.findViewById(R.id.transferAmount);

                String dest = autoCompleteTextView.getText().toString().trim();

                if(editText.getText().toString().trim().equals("") || dest.equals("")){

                    Toast.makeText(context,R.string.verifiez_les_champs, Toast.LENGTH_SHORT).show();
                }else {

                    Double amount = Double.parseDouble(editText.getText().toString().trim());
                    ((Service)getActivity()).setAmount(amount);
                    ((Service)getActivity()).setDest(dest);
                    editText.setText("");
                    autoCompleteTextView.setText("");

                    Log.d("TAG","hello "+ dest+"   fdffdf :"+amount);

                    Intent transfer = new Intent (getActivity(),TransferActivity.class );
                    transfer.putExtra("amount", amount);
                    transfer.putExtra("dest", dest);
                    startActivity(transfer);
                }
            }
        });
        super.onCreate(SavedInstaceState);
        return builder.create();
    }

}
