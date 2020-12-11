package com.example.orderapp1_2.kitchen;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import com.example.orderapp1_2.R;

public class KitchenFragment extends Fragment implements View.OnClickListener {

    private KitchenViewModel mViewModel;
    public static KitchenFragment newInstance() {
        return new KitchenFragment();
    }

    private View root;
    private Button button;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.kitchen_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KitchenViewModel.class);
        button = root.findViewById(R.id.button2);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        System.out.println("test11");
    }
}