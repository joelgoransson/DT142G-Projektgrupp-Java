package com.example.orderapp1_2;

import android.os.Bundle;

import com.example.orderapp1_2.retorofit.classes.Feed;
import com.example.orderapp1_2.retorofit.classes.MenuItem;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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

    Button btn;

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

        //Lägget till starter input fält
        starterLayout = findViewById(R.id.linearStarter);
        addStarterBtn = findViewById(R.id.addStarterBtn);
        addStarterBtn.setOnClickListener(new AddButtonListener("Förrätt", starter, "linearStarter"));
        addStarterBtn.performClick();

        //Lägget till main input fält
        mainLayout = findViewById(R.id.linearMain);
        addMainBtn = findViewById(R.id.addMainBtn);
        addMainBtn.setOnClickListener(new AddButtonListener("Varmrätt", main, "linearMain"));
        addMainBtn.performClick();

        //Lägget till main input fält
        efterLayout = findViewById(R.id.linearEfter);
        addEfterBtn = findViewById(R.id.addEfterBtn);
        addEfterBtn.setOnClickListener(new AddButtonListener("Efterrätt", efter, "linearEfter"));
        addEfterBtn.performClick();

        //Lägget till main input fält
        drinkLayout = findViewById(R.id.linearDrink);
        addDrinkBtn = findViewById(R.id.addDrinkBtn);
        addDrinkBtn.setOnClickListener(new AddButtonListener("Dryck", drink, "linearDrink"));
        addDrinkBtn.performClick();

        okBtn = findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = starterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    System.out.println(getOrderItem((LinearLayout) starterLayout.getChildAt(i)));
                }
                count = mainLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    System.out.println(getOrderItem((LinearLayout) mainLayout.getChildAt(i)));
                }
                count = efterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    System.out.println(getOrderItem((LinearLayout) efterLayout.getChildAt(i)));
                }
                count = drinkLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    System.out.println(getOrderItem((LinearLayout) drinkLayout.getChildAt(i)));
                }
            }
        });
    }

    private String getOrderItem(LinearLayout row){
        AutoCompleteTextView child = (AutoCompleteTextView) row.getChildAt(0);
        return String.valueOf(child.getText());
    }


    private class AddButtonListener implements View.OnClickListener{
        private String hint;
        private List<String> list;
        private String id;

        AddButtonListener(String hint, List<String> list, String id){
            this.hint = hint;
            this.list = list;
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            LinearLayout ll1 = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
            LayoutInflater inflater = LayoutInflater.from(ScrollingActivity.this);
            View row = inflater.inflate(R.layout.order_row, null);

            AutoCompleteTextView ACTV = row.findViewById(R.id.actvIn);
            ImageButton imgBtn = row.findViewById(R.id.comBtn);

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
            ACTV.setAdapter(adapter);
            ACTV.setOnItemClickListener(listenerACTV);
            ACTV.setHint(hint);

            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "This does nothing yet...;)", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            ll1.addView(row);
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