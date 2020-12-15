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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL= "http://192.168.0.37:8080/Hemsida/webresources/";
    public String[] employeeNames;
    public List<EmployeeObject> employeeList;
    public List<EmPassObject> empPassList;
    public List<PassObject> passList;
    public List<PassCard> passCards = new ArrayList<>();
    private int weekNr = 1;

    protected Calendar calendar;
    protected TextView weekText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Stockholm"));
        weekText = findViewById(R.id.Vecka);
        weekText.setText("Vecka "+calendar.get(Calendar.WEEK_OF_YEAR));
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

    /**
     * Thread class responsible for creating the PassCard object
     */
    private class WriteThread implements Runnable{
        @Override
        /**
         * Runs through all objects in employeeList and stores names to employeeNames[].
         * Runs through all EmPassObjects in empPassList and stores them as PassCards to passCards list
         */
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
                PassObject passObject = passList.get(passID);
                PassCard pass=new PassCard();
                pass.setId(item.getId());
                pass.setEmpNr(item.getEmployeenr());
                pass.setPassId(passID);
                pass.setEmpName(item.getEmployeename());
                pass.setPassNr(passObject.getPassnr());
                pass.setWeekday(weekday(passList.get(passID).getWeekday()));
                pass.setWeekNr(passObject.getWeeknr());
                passCards.add(pass);
            }
        }
    }

    /**
     * Converts a integer to a string
     * @param index the index of the weekday 0-5
     * @return returns the name of the day
     */
    private String weekday(int index){
        switch (index) {
            case 0: return "Måndag";
            case 1: return "Tisdag";
            case 2: return "Onsdag";
            case 3: return "Torsdag";
            case 4: return "Fredag";
            case 5: return "Lördag";
            default: return "";
        }
    }

    /**
     * Takes the name of a weekday and returns the index
     * @param day name of the day
     * @return int index of the day
     */
    private int weekindex(String day){
        switch (day) {
            case "Måndag": return 0;
            case "Tisdag": return 1;
            case "Onsdag": return 2;
            case "Torsdag": return 3;
            case "Fredag": return 4;
            case "Lördag": return 5;
            default: return -1;
        }
    }

    public void my_scheduler(View view){
        Intent intent = new Intent(MainActivity.this, My_schema.class);
        startActivity(intent);
    }

    /**
     * On click listener for the clickable textviews. Opens dialog and sends id and PassCard to dialog
     * @param view The TextView item clicked on
     */
    public void Set_Emp(View view) {
        int viewID = view.getId();
        String id = view.getResources().getResourceName(viewID);
        String day = weekday(Integer.parseInt(String.valueOf(id.charAt(id.length()-3))));
        int passNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-2)));
        int empNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-1)));

        PassCard passCard = findPassCard(day, passNr, empNr, weekNr);
        SetEmpDialog SetEmpDialog = new SetEmpDialog(viewID, employeeNames, passCard);
        SetEmpDialog.show(getSupportFragmentManager(), "SetEMpDialog");
    }

    public void openSchedule(View view){
        int viewID = view.getId();
        String id = view.getResources().getResourceName(viewID);
        /*String day = weekday(Integer.parseInt(String.valueOf(id.charAt(id.length()-3))));
        int passNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-2)));
        int empNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-1)));*/
        //PassCard passCard = findPassCard("Måndag", 1, 1);
        MySchemaDialog dialog = new MySchemaDialog(viewID, employeeNames);
        dialog.show(getSupportFragmentManager(), "MySchemaDialog");
    }

    public void currentSchema(View view){
        weekNr = 1;
        weekText.setText("Vecka " + calendar.get(Calendar.WEEK_OF_YEAR));
        schema();
    }
    public void nextSchema(View view){
        weekNr = 2;
        int week = calendar.get(Calendar.WEEK_OF_YEAR)+1;
        weekText.setText("Vecka " + week);
        schema();
    }

    /**
     * Print the schedule to the layout
     */
    public void schema(){
        int startIndex, stopIndex;
        if(weekNr == 1){
            startIndex = 0;
            stopIndex = 36;
        }else{
            startIndex = 36;
            stopIndex = 69;
        }
        //for(PassCard item : passCards){
        for(int i = startIndex; i < stopIndex; i++){
            PassCard item = passCards.get(i);
            int day = weekindex(item.getWeekday());
            int pass = item.getPassNr()-1;
            int emp = item.getEmpNr();
            System.out.println(day+" "+pass+" "+emp);
            int id = getResources().getIdentifier("P"+day+pass+emp, "id", this.getPackageName());
            TextView textView = findViewById(id);
            if(textView != null){
                textView.setText(item.getEmpName());
            }
        }
    }

    /**
     * Find and return a PassCard object from the passCards list
     * @param day The int for the day, 0-5
     * @param passNr The pass number, 1-2
     * @param empNr The employee number, 0-2
     * @param weekNr the current week being viewed
     * @return A PassCard with the give day, pass, and emp number.
     */
    public PassCard findPassCard(String day, int passNr, int empNr, int weekNr){
        int startIndex, stopIndex;
        if(weekNr == 1){
            startIndex = 0;
            stopIndex = 36;
        }else{
            startIndex = 36;
            stopIndex = 69;
        }
        for(int i = startIndex; i < stopIndex; i++){
            PassCard item = passCards.get(i);
            if(item.getWeekday().equalsIgnoreCase(day) && item.getPassNr() == passNr+1 && item.getEmpNr() == empNr){
                System.out.println(item.getEmpName());
                 return item;
            }
        }
        return null;
    }
}