package com.dansalomon.C24.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.dansalomon.C24.R;
import com.dansalomon.C24.Service;
import com.dansalomon.C24.Transfer;
import com.dansalomon.C24.utils.JSONfunctions;

import java.util.List;


/**
 * Dialog Created by Dan Salomon on 25/10/2015.
 */
public class TransfertDialog extends DialogFragment {

    final String[] country_names = {"Ghana","Tchad","Congo","France","Cameroun","Nigeria","RCA","Guinnée Konakry","Guinnée Bisseau","Allemagne","Benin"};



    public Dialog onCreateDialog (Bundle SavedInstaceState){

        super.onCreate(SavedInstaceState);

        List<String> country = ((Service)getActivity()).getCountry();

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        builder.setTitle("Transfert d'argent");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>((this.getActivity().getApplicationContext()),R.layout.item_country_textview,country);


        LayoutInflater inflater = (getActivity()).getLayoutInflater();

        View view = inflater.inflate(R.layout. dialog_transfer, null);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.editCountry);

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

                startActivity(new Intent(getActivity(),Transfer.class));
            }
        });

        return builder.create();
    }

}
