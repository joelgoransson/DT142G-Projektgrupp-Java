package com.example.schema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.example.schema.Retrofit.ApiInterface;
import com.example.schema.Retrofit.EmPassList;
import com.example.schema.Retrofit.EmPassObject;
import com.example.schema.Retrofit.EmployeeList;
import com.example.schema.Retrofit.EmployeeObject;
import com.example.schema.Retrofit.PassList;
import com.example.schema.Retrofit.PassObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class My_schema extends AppCompatActivity {
    private final String BASE_URL= "http://192.168.0.37:8080/Hemsida/webresources/";
    private String emp;
    private int week;
    public List<PassObject> passList;
    public List<EmPassObject> empPassList;
    public List<PassCard> passCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schema);
        emp = getIntent().getStringExtra("SELECTED_NAME");
        week = getIntent().getIntExtra("SELECTED_WEEK", 0);
        TextView textView = findViewById(R.id.weekText);
        textView.setText("Vecka: "+week);
        Thread t = new GetThread();
        t.start();
    }

    private class GetThread extends Thread{
        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            Call<EmPassList> call = apiInterface.getEmpPassByName(emp);
            try {
                empPassList = call.execute().body().getEmPassList();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(empPassList != null){
                for(EmPassObject empPass : empPassList){
                    Call<PassObject> passCall = apiInterface.getPass(empPass.getPassid());
                    PassObject pass = null;
                    try {
                        pass = passCall.execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(pass.getWeeknr()==week) {
                        passCards.add(new PassCard(empPass.getId(), empPass.getPassid(), pass.getPass(), pass.getWeekday(), empPass.getEmployeename(), pass.getWeeknr()));
                    }
                }
                schema();
            }
        }
    }

    private void schema(){
        Resources res = getResources();
        System.out.println(passCards.size());
        for(PassCard card : passCards){
            int id = res.getIdentifier("day_"+card.getWeekday(), "id", this.getPackageName());
            TextView textView = findViewById(id);
            if(card.getPassNr() == 0){
                 textView.append("11-14" + "\n");
             }else{
                 if (card.getWeekday() == 5) {
                     textView.append("16-23" + "\n");
                 } else {
                     textView.append("16-20" + "\n");
                 }
             }
        }
    }
}