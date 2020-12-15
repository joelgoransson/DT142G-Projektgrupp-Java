package com.example.schema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    public List<EmployeeObject> employeeList = new ArrayList<>();
    public List<EmPassObject> emPassObjectList = new ArrayList<>();
    public List<PassObject> passList;
    public List<PassCard> passCards = new ArrayList<>();
    private int weekNr;

    protected Calendar calendar;
    protected TextView weekText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("============================");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Stockholm"));
        weekNr = calendar.get(Calendar.WEEK_OF_YEAR);
        weekText = findViewById(R.id.Vecka);
        weekText.setText("Vecka "+ weekNr);

        GetThread getter = new GetThread(weekNr);
        getter.start();
        try {
            getter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schema();
    }

    protected class GetThread extends Thread{
        private int weekNr;
        public GetThread(int weekNr){
            this.weekNr = weekNr;
        }

        @Override
        public void run(){
            employeeList.clear();
            emPassObjectList = new ArrayList<>();
            passCards.clear();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            Call<EmPassList> call = apiInterface.getEmPassList();
            Call<EmployeeList> empCall = apiInterface.getEmpList();
            try {
                emPassObjectList = call.execute().body().getEmPassList();
                employeeList = empCall.execute().body().getEmployeeList();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(emPassObjectList!=null){
                for(EmPassObject item : emPassObjectList){
                    Call<PassObject> passCall = apiInterface.getPass(item.getPassid());
                    PassObject pass = null;
                    try {
                        pass = passCall.execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    passCards.add(new PassCard(item.getId(), item.getPassid(), pass.getPass(), pass.getWeekday(), item.getEmployeename(), pass.getWeeknr()));
                }
            }

            List<String> tempList = new ArrayList<>();
            for(EmployeeObject employee : employeeList){
                tempList.add(employee.getName());
            }
            employeeNames = new String[tempList.size()];
            tempList.toArray(employeeNames);
        }
    }

    protected class PassThread extends Thread {
        private int passNr;
        private int day;
        private int week;
        private PassCard passCard;

        public PassThread(int passNr, int day, int week, PassCard passCard) {
            this.passNr = passNr;
            this.day = day;
            this.week = week;
            this.passCard = passCard;
        }

        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            Call<PassObject> call = apiInterface.findPass(passNr,day,week);
            PassObject pass = null;
            try {
                pass = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!(pass == null)){
                passCard.setPassId(pass.getId());
                passCard.setWeekNr(pass.getWeeknr());
                passCard.setWeekday(pass.getWeekday());
                passCard.setPassNr(pass.getPass());
            }else{
                passCard.setPassId(-1);
                passCard.setPassNr(passNr);
                passCard.setWeekday(day);
                passCard.setWeekNr(week);
            }
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
        refresh();
        TextView txt = (TextView) view;

        int viewID = view.getId();
        String id = view.getResources().getResourceName(viewID);
        int day = Integer.parseInt(String.valueOf(id.charAt(id.length()-3)));
        int passNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-2)));
        int empNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-1)));

        PassCard pass = new PassCard();

        Thread thread = new PassThread(passNr, day, weekNr, pass);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(txt.getText().equals("")){
            pass.setEmpName("");
        }else{
            String name = (String) txt.getText();
            pass = findPassCard(day, passNr, name, weekNr);
        }
        SetEmpDialog setEmpDialog = new SetEmpDialog(viewID, pass);
        setEmpDialog.show(getSupportFragmentManager(), "SetEMpDialog");
    }

    public void clear(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 3; k++){
                    int textID = getResources().getIdentifier("P"+i+j+k, "id", this.getPackageName());
                    TextView textView = findViewById(textID);
                    if(textView!=null)
                        textView.setText("");
                }
            }
        }
    }

    public void refresh(){
        clear();
        Thread t = new GetThread(weekNr);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        weekText.setText("Vecka " + weekNr);
        schema();
    }

    public void openSchedule(View view){
        int viewID = view.getId();
        String id = view.getResources().getResourceName(viewID);
        /*String day = weekday(Integer.parseInt(String.valueOf(id.charAt(id.length()-3))));
        int passNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-2)));
        int empNr = Integer.parseInt(String.valueOf(id.charAt(id.length()-1)));*/
        //PassCard passCard = findPassCard("Måndag", 1, 1);
        MySchemaDialog dialog = new MySchemaDialog(viewID, employeeNames, weekNr);
        dialog.show(getSupportFragmentManager(), "MySchemaDialog");
        refresh();
    }

    public void currentSchema(View view){
        if(weekNr != calendar.get(Calendar.WEEK_OF_YEAR)) {
            clear();
            weekNr = calendar.get(Calendar.WEEK_OF_YEAR);
            Thread t = new GetThread(weekNr);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            weekText.setText("Vecka " + weekNr);
            schema();
        }
    }

    public void nextSchema(View view){
        if(weekNr == calendar.get(Calendar.WEEK_OF_YEAR)) {
            clear();
            weekNr = calendar.get(Calendar.WEEK_OF_YEAR) + 1;
            Thread t = new GetThread(weekNr);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            weekText.setText("Vecka " + weekNr);
            schema();
        }
    }

    public void schema(){
        for(int day = 0; day < 6; day++){
            List<PassCard> list = findPasses(day, 0, weekNr);
            for(int i = 0; i < list.size(); i++){
                int textID = getResources().getIdentifier("P"+day+"0"+i, "id", this.getPackageName());
                TextView textView = findViewById(textID);
                if(!(textView==null)){
                    textView.setText(list.get(i).getEmpName());
                }
            }
            list = findPasses(day, 1, weekNr);
            for(int i = 0; i < list.size(); i++){
                int textID = getResources().getIdentifier("P"+day+"1"+i, "id", this.getPackageName());
                TextView textView = findViewById(textID);
                textView.setText(list.get(i).getEmpName());
            }
        }
    }

    public List<PassCard> findPasses(int day, int pass, int weekNr){
        List<PassCard> ret = new ArrayList<>();
        for(PassCard item : passCards){
            if(item.getWeekday()==day && item.getPassNr()==pass && item.getWeekNr()==weekNr){
                ret.add(item);
            }
        }
        return ret;
    }

    public PassCard findPassCard(int day, int passNr, String empName, int weekNr){
        for(PassCard item : passCards){
            if(item.getWeekday() == day && item.getPassNr() == passNr && item.getWeekNr() == weekNr && item.getEmpName().equals(empName)){
                return item;
            }
        }
        return null;
    }
}