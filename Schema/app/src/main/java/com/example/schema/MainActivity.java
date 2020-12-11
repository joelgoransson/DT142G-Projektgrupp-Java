package com.example.schema;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    SetEmpDialog SetEmpDialog = new SetEmpDialog(i);
    SetEmpDialog.show(getSupportFragmentManager(), "SetEMpDialog");


    }


}