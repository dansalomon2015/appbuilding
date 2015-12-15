package com.dansalomon.C24.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.dansalomon.C24.R;

/**
 * Created by Dan Salomon on 03/12/2015.
 */
public class ComparaisonDialog extends DialogFragment {


    public Dialog onCreateDialog (Bundle SavedInstaceState){

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        builder.setTitle("");

        LayoutInflater inflater = (getActivity()).getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_comparaison_du_jour,null));

        builder.setNegativeButton(R.string.fermer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        Dialog dialog = builder.create();

        return  dialog;
    }

}
