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
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class My_schema extends AppCompatActivity {
    private final String BASE_URL= "http://192.168.0.37:8080/Hemsida/webresources/";
    private String emp;
    public List<PassObject> passList;
    public List<EmPassObject> empPassList;
    public List<PassCard> passCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schema);
        emp = getIntent().getStringExtra("SELECTED_NAME");
        try {
            runThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schema();
    }

    private void runThreads() throws InterruptedException {
        Thread t1 = new Thread(new My_schema.ReadThread());
        Thread t2 = new Thread(new My_schema.WriteThread());
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }

    private class ReadThread implements Runnable{
        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            Call<EmPassList> empPassCall = apiInterface.getEmPassList();
            Call<PassList> passCall = apiInterface.getPassList();
            try {
                empPassList = empPassCall.execute().body().getEmPassList();
                passList = passCall.execute().body().getPassList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String weekday(int index){
        switch (index) {
            case 0: return "Måndag";
            case 1: return "Tisdag";
            case 2: return "Onsdag";
            case 3: return "Torsdag";
            case 4: return "Fredag";
            case 5: return "Lördag";
            default: return " ";
        }

    }

    private class WriteThread implements Runnable{
        @Override
        public void run() {
            for(int i = 0; i < empPassList.size(); i++){
                EmPassObject item = empPassList.get(i);
                int passID = item.getPassid();
                PassCard temp=new PassCard();
                temp.setId(item.getId());
                temp.setEmpNr(item.getEmployeenr());
                temp.setPassId(passID);
                temp.setEmpName(item.getEmployeename());
                temp.setPassNr(passList.get(passID).getPassnr());
                temp.setWeekday(weekday(passList.get(passID).getWeekday()-1));
                passCards.add(temp);
            }
        }
    }

    private void schema(){
        TextView textView = findViewById(R.id.textView);
        for(PassCard card : passCards){
            System.out.println(card);
            System.out.println(card.getEmpName());
            if(card.getEmpName().equals(emp)){
                if(card.getPassNr() == 0){
                    textView.append(card.getWeekday() + ", kl 11-14" + "\n");
                }else {
                    if (card.getWeekday() == weekday(5)) {
                        textView.append(card.getWeekday() + ", kl 16-23" + "\n");
                    } else {
                        textView.append(card.getWeekday() + ", kl 16-20" + "\n");
                    }
                }
            }
        }
    }
}