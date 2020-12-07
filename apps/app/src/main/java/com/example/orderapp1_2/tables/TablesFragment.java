package com.example.orderapp1_2.tables;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderapp1_2.CardItem;
import com.example.orderapp1_2.OrderAdapter;
import com.example.orderapp1_2.R;

import java.util.ArrayList;

public class TablesFragment extends Fragment {

    private TablesViewModel mViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private final ArrayList<CardItem> orders = new ArrayList<>();

    public static TablesFragment newInstance() {
        return new TablesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.tables_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TablesViewModel.class);
        // TODO: Use the ViewModel

        orders.add(new CardItem("Bord 2","fisk"));
        orders.add(new CardItem("Bord 3","korv"));
        orders.add(new CardItem("Bord 4","majs"));

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(root.getContext());
        adapter = new OrderAdapter(orders);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);





    }

}