package com.example.schema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
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
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL="http://192.168.0.37:8080/Hemsida/webresources/";
    public String[] employeeNames;
    public List<EmployeeObject> employeeList;
    public List<EmPassObject> empPassList;
    public List<PassObject> passList;
    public List<PassCard> passCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            runThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schema();
    }

    private void runThreads() throws InterruptedException {
        Thread t1 = new Thread(new ReadThread());
        Thread t2 = new Thread(new WriteThread());
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

            Call<EmployeeList> empCall = apiInterface.getEmpList();
            Call<EmPassList> empPassCall = apiInterface.getEmPassList();
            Call<PassList> passCall = apiInterface.getPassList();
            try {
                employeeList = empCall.execute().body().getEmployeeList();
                empPassList = empPassCall.execute().body().getEmPassList();
                passList = passCall.execute().body().getPassList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class WriteThread implements Runnable{
        @Override
        public void run() {
            List<String> tempList = new ArrayList<>();
            for(EmployeeObject emp : employeeList){
                tempList.add(emp.getName());
            }
            employeeNames = new String[tempList.size()];
            tempList.toArray(employeeNames);
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
                //System.out.println(temp.getWeekday() + " " + temp.getPassNr() + " " + temp.getEmpNr());
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

    public void my_scheduler(View view){
        Intent intent = new Intent(MainActivity.this, My_schema.class);
        startActivity(intent);
    }

    public void Set_Emp(View view) {
        openDialog(view.getId());
        Log.d("MyApp","I am here");
    }

    private void openDialog(int i) {
        SetEmpDialog SetEmpDialog = new SetEmpDialog(i, employeeNames);
        SetEmpDialog.show(getSupportFragmentManager(), "SetEMpDialog");
    }

    private void schema(){
        int maxPass = 2;
        for(int day = 0; day != 6; day++){
            if(day==5){
                maxPass = 1;
            }
            for(int pass = 0; pass < maxPass; pass++){
                for(int emp = 0; emp != 3; emp++){
                    Resources res = getResources();
                    System.out.println(day+" "+pass + " "+emp);
                    int id = res.getIdentifier("P"+day+pass+emp, "id", this.getPackageName());
                    TextView textView = findViewById(id);
                    textView.setText(findPassCard(weekday(day), pass+1, emp).getEmpName());
                }
            }
        }
    }

    private PassCard findPassCard(String day, int passNr, int empNr){
        for(PassCard i : passCards){
            if(i.getWeekday().equalsIgnoreCase(day) && i.getPassNr()==passNr && i.getEmpNr()==empNr){
                System.out.println(i.getEmpName());
                 return i;
            }
        }
        return null;
    }

}