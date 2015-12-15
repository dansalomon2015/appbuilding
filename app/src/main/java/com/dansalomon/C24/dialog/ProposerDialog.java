package com.dansalomon.C24.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.dansalomon.C24.R;

/**
 * Created by Dan Salomon on 03/12/2015.
 */
public class ProposerDialog extends DialogFragment {

    public Dialog onCreateDialog (Bundle SavedInstaceState){

        final AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        builder.setTitle("Qu'aimeriez vous comparer d'autre?");

        final LayoutInflater inflater = (getActivity()).getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_proposer,null));

        builder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context = inflater.getContext();
                /*Toast.makeText(context, "Suggestion sera prise en compte. Merci",Toast.LENGTH_LONG).show();*/

               /* Snackbar.makeText(context,"Suggestion sera prise en compte. Merci", Snackbar.LENGTH_LONG);*/
            }
        });

        Dialog dialog = builder.create();

        return  dialog;
    }
}
