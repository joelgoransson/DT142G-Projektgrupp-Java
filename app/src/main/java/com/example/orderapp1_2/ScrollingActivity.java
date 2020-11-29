package com.example.orderapp1_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.orderapp1_2.retorofit.classes.Feed;
import com.example.orderapp1_2.retorofit.classes.MenuItem;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public class ScrollingActivity extends AppCompatActivity {
    //BASE_URL och SUB_URL tillsammans bildar url till API
    private static final String BASE_URL = "http://192.168.0.37:8080/";
    private static final String SUB_URL = "OrderWeb/webresources/menu.menu";

    List<MenuItem> menuList; //För att lagra alla datatyper från menyn
    List<String> starter = new ArrayList<>(); //en lista för namnen över alla olika starters
    List<String> main = new ArrayList<>(); //en lista för namnen över alla olika varmrätter
    List<String> efter = new ArrayList<>(); //en lista för namnen över alla olika efterrätter
    List<String> drink = new ArrayList<>(); //en lista för namnen över alla olika drinkar

    //De olika inmatningsfälten
    AutoCompleteTextView starterACTV;
    AutoCompleteTextView mainACTV;
    AutoCompleteTextView efterACTV;
    AutoCompleteTextView drinkACTV;

    //De olika lägg till knapparna
    private ImageButton addStarterBtn;
    private ImageButton addMainBtn;
    private ImageButton addEfterBtn;
    private ImageButton addDrinkBtn;

    //Layouts där nya inmatningsfält läggs till
    private LinearLayout starterLayout;
    private LinearLayout mainLayout;
    private LinearLayout efterLayout;
    private LinearLayout drinkLayout;

    private AutoCompleteListener listenerACTV; //Event lyssnare för AutoComplete listorna

    private Button okBtn; //Skicka beställnings knapp

    /**
     * Interface som retrofit använder sig av Feed för att läsa API
     */
    public interface ApiInterface{
        @GET(SUB_URL) //länk till till API på sidan
        Call<Feed> getXml();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        //retrofit functionen
        readXmlFeed();

        listenerACTV = new AutoCompleteListener();

        //autocomplete text fält
        starterACTV = findViewById(R.id.autoCompleteStart);
        addAdapter(starterACTV, starter);

        //Lägget till starter input fält
        starterLayout = findViewById(R.id.linearStarter);
        addStarterBtn = findViewById(R.id.addStarterBtn);
        addStarterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView temp = addACTV("Förrätt", starter);
                starterLayout.addView(temp);
            }
        });

        //Lägget till main input fält
        mainACTV = findViewById(R.id.autoCompleteMain);
        addAdapter(mainACTV, main);

        mainLayout = findViewById(R.id.linearMain);
        addMainBtn = findViewById(R.id.addMainBtn);
        addMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView temp = addACTV("Varmrätt", main);
                mainLayout.addView(temp);
            }
        });

        //Lägget till main input fält
        efterACTV = findViewById(R.id.autoCompleteEfter);
        addAdapter(efterACTV, efter);

        efterLayout = findViewById(R.id.linearEfter);
        addEfterBtn = findViewById(R.id.addEfterBtn);
        addEfterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView temp = addACTV("Efterrätt", efter);
                efterLayout.addView(temp);
            }
        });

        //Lägget till main input fält
        drinkACTV = findViewById(R.id.autoCompleteDrink);
        addAdapter(drinkACTV, drink);

        drinkLayout = findViewById(R.id.linearDrink);
        addDrinkBtn = findViewById(R.id.addDrinkBtn);
        addDrinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView temp = addACTV("Dryck", drink);
                drinkLayout.addView(temp);
            }
        });

        okBtn = findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = starterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    AutoCompleteTextView child = (AutoCompleteTextView) starterLayout.getChildAt(i);
                    System.out.println(child.getText());
                }
                count = mainLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    AutoCompleteTextView child = (AutoCompleteTextView) mainLayout.getChildAt(i);
                    System.out.println(child.getText());
                }
                count = efterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    AutoCompleteTextView child = (AutoCompleteTextView) efterLayout.getChildAt(i);
                    System.out.println(child.getText());
                }
                count = drinkLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    AutoCompleteTextView child = (AutoCompleteTextView) drinkLayout.getChildAt(i);
                    System.out.println(child.getText());
                }
            }
        });

    }

    /**
     * Lägger till arrayadapter till ett AutoCompleteTextView, lägger även till en onClickListener
     * @param actv AutoCompleteTextView som ska få en adapter
     * @param list Listan med String som ska auto completa till
     */
    void addAdapter(AutoCompleteTextView actv, List<String> list){
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        actv.setAdapter(adapter);
        actv.setOnItemClickListener(listenerACTV);
    }

    /**
     * Skapar ett nytt auto complete inmatningsfält och returnar det
     * @param hint Vilken hint som ska vara i fältet
     * @param list Listan med String som ska auto completa till
     * @return Det nya AutoCompleteTextView elementet
     */
    AutoCompleteTextView addACTV(String hint, List<String> list){
        AutoCompleteTextView newACTV = new AutoCompleteTextView(getApplicationContext());
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        newACTV.setAdapter(adapter);
        newACTV.setOnItemClickListener(listenerACTV);
        newACTV.setHint(hint);
        return newACTV;
    }

    /**
     * OnItemClickListener till AutoCompleteTextView som stänger inputen(tangentbordet) när en autoComplete blir vaLd
     */
    protected class AutoCompleteListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //Hitta tangentbordet
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0); //sätt tangentbordets token till 0
        }
    }


    /**
     * Funktionen använder ApiInterface och MenuItem klassen för att köra retrofit.builder på api
     */
    private void readXmlFeed(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //Bas URL till API
                .addConverterFactory(SimpleXmlConverterFactory.create()) //Vilken converter som ska användas SimpleXML i detta fall
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Feed> call = apiInterface.getXml(); //kallar getData() från ApiInterface
        call.enqueue(new Callback<Feed>(){
            //Vad som ska hända vid lyckat svar
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d("Respons sucess", response.message());
                
                menuList = response.body().getMenuList(); //Spara response från databasen till menuList

                //Sortera namnen(nyckeln) för alla MenuItems till olika listor
                for (MenuItem menuItem: menuList) {
                    String test = menuItem.getType();
                    if(test.equalsIgnoreCase("starter")){
                        starter.add(menuItem.getName());
                    }
                    else if(test.equalsIgnoreCase("main")){
                        main.add(menuItem.getName());
                    }
                    else if(test.equalsIgnoreCase("efter")){
                        efter.add(menuItem.getName());
                    }
                    else if(test.equalsIgnoreCase("drink")){
                        drink.add(menuItem.getName());
                    }
                }
            }

            //Vad som ska hända vid mislyckat svar
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.d("Response fail", t.getMessage());
            }
        });
    }
}