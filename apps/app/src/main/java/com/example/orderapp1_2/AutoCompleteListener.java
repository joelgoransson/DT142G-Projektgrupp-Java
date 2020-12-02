package com.example.orderapp1_2;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * AutoCompleteListener skapar en event lyssnare som ska stänga inputen(tangentbordet) när en autoComplete blir vaLd
 */
public class AutoCompleteListener implements AdapterView.OnItemClickListener{

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Context context = view.getContext();
        InputMethodManager in = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE); //Hitta tangentbordet
        in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0); //sätt tangentbordets token till 0
    }
}
