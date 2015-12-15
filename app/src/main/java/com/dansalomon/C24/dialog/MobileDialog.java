package com.dansalomon.C24.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.dansalomon.C24.Mobile;
import com.dansalomon.C24.R;

/**
 * Created by Dan Salomon on 03/12/2015.
 */
public class MobileDialog extends DialogFragment {

    public Dialog onCreateDialog (Bundle SavedInstaceState){

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        builder.setTitle("Internet Mobile");

        LayoutInflater inflater = (getActivity()).getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_mobile,null));

        builder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(getActivity(),Mobile.class));
            }
        });

        Dialog dialog = builder.create();

        return  dialog;
    }
}
