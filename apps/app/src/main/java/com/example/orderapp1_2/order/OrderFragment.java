package com.example.orderapp1_2.order;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.orderapp1_2.R;
import com.example.orderapp1_2.ScrollingActivity;

public class OrderFragment extends Fragment {
    private OrderViewModel orderViewModel;
    private Button tabel1Button;
    private Button tabel2Button;
    private Button tabel3Button;
    private Button tabel4Button;
    private Button tabel5Button;
    private Button tabel6Button;
    private Button tabel7Button;
    private Button tabel8Button;
    private int tableNr;

    public final static int NORMAL_REQUEST = 42;
    public final static String EXTRA_VALUE = "OrderFragment";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        View root = inflater.inflate(R.layout.order_fragment, container, false);

        tabel1Button = root.findViewById(R.id.table1Button);
        tabel1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 1;
                openPage();
            }
        });


        tabel2Button = root.findViewById(R.id.table2Button);
        tabel2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 2;
                openPage();
            }
        });

        tabel3Button = root.findViewById(R.id.table3Button);
        tabel3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 3;
                openPage();
            }
        });

        tabel4Button = root.findViewById(R.id.table4Button);
        tabel4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 4;
                openPage();
            }
        });

        tabel5Button = root.findViewById(R.id.table5Button);
        tabel5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 5;
                openPage();
            }
        });

        tabel6Button = root.findViewById(R.id.table6Button);
        tabel6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 6;
                openPage();
            }
        });

        tabel7Button = root.findViewById(R.id.table7Button);
        tabel7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 7;
                openPage();
            }
        });

        tabel8Button = root.findViewById(R.id.table8Button);
        tabel8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNr = 8;
                openPage();
            }
        });


        return root;
    }

    public void openPage()
    {
        Intent orderActivityIntent = new Intent(getActivity(), ScrollingActivity.class);
        orderActivityIntent.putExtra(EXTRA_VALUE,tableNr);
        startActivityForResult(orderActivityIntent, NORMAL_REQUEST);
    }

}