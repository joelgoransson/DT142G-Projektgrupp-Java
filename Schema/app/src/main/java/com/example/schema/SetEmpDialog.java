package com.example.schema;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.schema.Retrofit.ApiInterface;
import com.example.schema.Retrofit.EmPassList;
import com.example.schema.Retrofit.EmPassObject;
import com.example.schema.Retrofit.EmployeeList;
import com.example.schema.Retrofit.PassList;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class SetEmpDialog extends DialogFragment {
    private static final String BASE_URL="http://192.168.1.4:8080/Hemsida/webresources/";
    private String[] arr;
    private String str ;
    private int id;
    private PassCard passCard;

    public SetEmpDialog(int id, String[] arr, PassCard passCard) {
        this.arr = arr;
        this.id = id;
        this.passCard = passCard;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title).setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView view1= getActivity().findViewById(id);
                str = arr[which];
                view1.setText(str);
                passCard.setEmpName(str);
                Thread t = new updateThread();
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    private class updateThread extends Thread{
        @Override
        public void run(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            EmPassObject emPass = new EmPassObject(passCard);
            Call<ResponseBody> updateCall = apiInterface.updateEmployeePass(Integer.toString(passCard.getId()), emPass);
            try {
                updateCall.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




