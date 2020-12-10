package com.example.schema;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;



public class SetEmpDialog extends DialogFragment {
   private String[] arr;
   private String str ;
   private int idd;

    public SetEmpDialog(int idd) {
        this.idd = idd;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title).setItems(R.array.SetArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView view1= getActivity().findViewById(idd);
                arr = getResources().getStringArray(R.array.SetArray);
                str = arr[which];
                view1.setText(str);

            }
        }).setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }


}




