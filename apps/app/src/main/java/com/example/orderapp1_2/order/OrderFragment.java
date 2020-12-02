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

public class OrderFragment extends Fragment implements View.OnClickListener {
    private OrderViewModel orderViewModel;
    public final static int NORMAL_REQUEST = 42;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orderViewModel =
                new ViewModelProvider(this).get(OrderViewModel.class);
        View root = inflater.inflate(R.layout.order_fragment, container, false);

        Button btn = (Button) root.findViewById(R.id.button);
        btn.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        Intent orderActivityIntent = new Intent(getActivity(), ScrollingActivity.class);
        startActivityForResult(orderActivityIntent, NORMAL_REQUEST);
    }
}