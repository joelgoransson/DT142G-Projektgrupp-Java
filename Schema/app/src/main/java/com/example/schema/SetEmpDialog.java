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
import com.example.schema.Retrofit.EmployeeObject;
import com.example.schema.Retrofit.PassList;
import com.example.schema.Retrofit.PassObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class SetEmpDialog extends DialogFragment {
    protected static final String BASE_URL="http://192.168.0.37:8080/Hemsida/webresources/";
    protected String[] arr;
    protected String str ;
    protected int id;
    protected PassCard passCard;

    public SetEmpDialog(int id, PassCard passCard) {
        this.id = id;
        this.passCard = passCard;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        System.out.println(passCard);
        Thread thread = new ReadThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        builder.setTitle(R.string.dialog_title).setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView view= getActivity().findViewById(id);
                str = arr[which];
                view.setText(str);
                if(str.equals("===TOMT===")){
                    view.setText("");
                    if(passCard.getPassId()!=-1){
                        new DeleteThread().start();
                    }
                }
                else if(!(passCard.getEmpName().equals(""))){
                    passCard.setEmpName(str);
                    Thread updateThread = new UpdateThread();
                    updateThread.start();
                    try {
                        updateThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    if(passCard.getPassId() == -1){
                        passCard.setEmpName(str);
                        Thread createThread = new CreateThread();
                        createThread.start();
                        try {
                            createThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        passCard.setEmpName(str);
                        Thread addThread = new AddEmpPassThread();
                        addThread.start();
                        try {
                            addThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
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

    private class DeleteThread extends Thread{
        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            Call<ResponseBody> call = apiInterface.deleteEmployeePass(passCard.getId());
            try {
                call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReadThread extends Thread{
        @Override
        public void run(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            Call<EmployeeList> call = apiInterface.getEmpList();
            try {
                arr = getNames(call.execute().body().getEmployeeList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] getNames(List<EmployeeObject> in){
        List<String> list = new ArrayList<>();
        for(EmployeeObject employee : in){
            list.add(employee.getName());
        }
        list.add("===TOMT===");
        String[] ret = new String[list.size()];
        list.toArray(ret);
        return ret;
    }

    private class CreateThread extends Thread{
        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            PassObject pass = new PassObject(passCard.getPassNr(), passCard.getWeekday(), passCard.getWeekNr());
            System.out.println(pass);
            System.out.println("=============================");
            Call<PassObject> passCall = apiInterface.createPass(pass);
            System.out.println(passCard);
            try {
                Response<PassObject> response = passCall.execute();
                System.out.println(passCall.request().body());
                System.out.println(response.code()+" "+response.message());
                System.out.println(response.body());
                passCard.setPassId(response.body().getId());
                //passCard.setPassId(passCall.execute().body().getId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            EmPassObject object = new EmPassObject(passCard);
            Call<ResponseBody> employeePassCall = apiInterface.createEmployeePassAutoID(object);
            try {
                employeePassCall.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class AddEmpPassThread extends Thread{
        @Override
        public void run(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            EmPassObject object = new EmPassObject(passCard);
            Call<ResponseBody> employeePassCall = apiInterface.createEmployeePassAutoID(object);
            try {
                employeePassCall.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class UpdateThread extends Thread{
        @Override
        public void run(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            //PassObject pass = new PassObject(passCard.getPassNr(), passCard.getWeekday(), passCard.getWeekNr());
            EmPassObject object = new EmPassObject(passCard);
            Call<ResponseBody> call = apiInterface.updateEmployeePass(passCard.getId(), object);
            try {
                call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




