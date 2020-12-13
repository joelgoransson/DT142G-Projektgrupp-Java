package com.example.schema;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.schema.Retrofit.ApiInterface;
import com.example.schema.Retrofit.EmPassObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MySchemaDialog extends DialogFragment {
    private static final String BASE_URL="http://192.168.100.101:8080/Hemsida/webresources/";
    private String[] arr;
    private String str;
    private int id;
    private PassCard passCard;

    public MySchemaDialog(int id, String[] arr) {
        this.arr = arr;
        this.id = id;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title).setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), My_schema.class);
                intent.putExtra("SELECTED_NAME", arr[which]);
                startActivity(intent);
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
