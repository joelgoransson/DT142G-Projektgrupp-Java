package com.example.orderapp1_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.orderapp1_2.order.OrderFragment;
import com.example.orderapp1_2.retorofit.classes.Bill;
import com.example.orderapp1_2.retorofit.classes.BillList;
import com.example.orderapp1_2.retorofit.classes.MenuItemList;
import com.example.orderapp1_2.retorofit.classes.MenuItem;
import com.example.orderapp1_2.retorofit.classes.Order;
import com.example.orderapp1_2.retorofit.classes.Orders;
import com.example.orderapp1_2.retorofit.classes.OrdersList;
import com.example.orderapp1_2.retorofit.classes.OrderList;
import com.example.orderapp1_2.retorofit.classes.RestaurantClient;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.orderapp1_2.order.OrderFragment.EXTRA_VALUE;


public class ScrollingActivity extends AppCompatActivity {
    //BASE_URL och SUB_URL tillsammans bildar url till API
    private static final String BASE_URL = "http://192.168.1.4:8080/Hemsida/webresources/";
    private static final String SUB_URL = "entities.menuitem";
    private RestaurantClient restaurantClient;
    private int tableNr;



    List<MenuItem> menuList; //För att lagra alla datatyper från menyn
    List<Orders> ordersList;
    List<Bill> billList;
    List<Order> orderList;
    int maxBillID;
    List<String> starter = new ArrayList<>(); //en lista för namnen över alla olika starters
    List<String> main = new ArrayList<>(); //en lista för namnen över alla olika varmrätter
    List<String> efter = new ArrayList<>(); //en lista för namnen över alla olika efterrätter
    List<String> drink = new ArrayList<>(); //en lista för namnen över alla olika drinkar

    //De olika lägg till knapparna
    private ImageButton addStarterBtn;
    private ImageButton addMainBtn;
    private ImageButton addEfterBtn;
    private ImageButton addDrinkBtn;
    private Button okBtn; //Skicka beställnings knapp

    //Layouts där nya inmatningsfält läggs till
    private LinearLayout starterLayout;
    private LinearLayout mainLayout;
    private LinearLayout efterLayout;
    private LinearLayout drinkLayout;

    private AutoCompleteListener listenerACTV; //Event lyssnare för AutoComplete listorna





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        Intent intent = getIntent();

        tableNr = intent.getIntExtra(EXTRA_VALUE,0);

////
        //retrofit functionen
        readXmlFeed();
        //readOrdersList();


        listenerACTV = new AutoCompleteListener();

        //Lägget till starter input fält
        starterLayout = findViewById(R.id.linearStarter);
        addStarterBtn = findViewById(R.id.addStarterBtn);
        addStarterBtn.setOnClickListener(new AddButtonListener("Förrätt", starter, "linearStarter"));
        addStarterBtn.performClick();

        //Lägget till main input fält
        mainLayout = findViewById(R.id.linearMain);
        addMainBtn = findViewById(R.id.addMainBtn);
        addMainBtn.setOnClickListener(new AddButtonListener("Huvudrätt", main, "linearMain"));
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
                Timestamp time = new Timestamp(System.currentTimeMillis());
                String time2 = time.toString();
                String tomorder="";
                int count = starterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) starterLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) starterLayout.getChildAt(i));
                    if(test != null && !test.trim().isEmpty()){
                        tomorder+=test;
                    }
                }
                count = mainLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) mainLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) mainLayout.getChildAt(i));
                    //System.out.println(getOrderItem((LinearLayout) mainLayout.getChildAt(i)));
                    if(test != null && !test.trim().isEmpty()){
                        tomorder+=test;
                    }
                }
                count = efterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) efterLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) efterLayout.getChildAt(i));
                    if(test != null && !test.trim().isEmpty()){
                        tomorder+=test;
                    }
                }
                count = drinkLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) drinkLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) drinkLayout.getChildAt(i));
                    if(test != null && !test.trim().isEmpty()){
                        tomorder+=test;
                    }
                }
                System.out.println("tomorder:" + tomorder);

                if(tomorder  == null || tomorder.trim().isEmpty()){
                        System.out.println("tom Order");
                }else{
                    generateBill("KÖKET",1,1, time2);
                    System.out.println("ORDERN ÄR TOM FÖR FAN!");
                }

                finish();



            }

            public void createOrdersEtc(){

                System.out.println("neeeeeeeeeeeej!");
                System.out.println(maxBillID);
                int count = starterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) starterLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) starterLayout.getChildAt(i));
                    if(test != null && !test.trim().isEmpty()){
                        generateOrder(1,test, "Kall", maxBillID);
                    }
                }
                count = mainLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) mainLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) mainLayout.getChildAt(i));
                    //System.out.println(getOrderItem((LinearLayout) mainLayout.getChildAt(i)));
                    if(test != null && !test.trim().isEmpty()){
                        generateOrder(1,test, "Kall", maxBillID);
                    }
                }
                count = efterLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) efterLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) efterLayout.getChildAt(i));
                    if(test != null && !test.trim().isEmpty()){
                        generateOrder(1,test, "Kall", maxBillID);
                    }
                }
                count = drinkLayout.getChildCount();
                for(int i = 1; i < count; i++){
                    //System.out.println(getOrderItem((LinearLayout) drinkLayout.getChildAt(i)));
                    String test = getOrderItem((LinearLayout) drinkLayout.getChildAt(i));
                    if(test != null && !test.trim().isEmpty()){
                        generateOrder(1,test, "Kall", maxBillID);
                    }
                }


            }
            private void generateBill(String status, int tableid, int empid, String time)
            {

                System.out.println("generatingBill");
                restaurantClient = RestaurantClient.getINSTANCE();
                Bill bill = new Bill(status,tableid,empid,time);

                Call<Bill> call = restaurantClient.createBill(bill);
                call.enqueue(new Callback<Bill>() {

                    @Override
                    public void onResponse(Call<Bill> call, Response<Bill> response) {


                        Log.d("Response successful", response.message());
                        Log.d(Order.class.toString(),call.request().toString());
                        Log.d(Order.class.toString(),call.request().body().toString());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        readBillList();


                    }
                    @Override
                    public void onFailure(Call<Bill> call, Throwable t) {

                        Log.d("Response fail", t.getMessage());
                        System.out.println("Response fail");

                    }
                });

            }
            private  void readBillList(){

                System.out.println("readbilllist out println");
                restaurantClient = RestaurantClient.getINSTANCE();
                Call<BillList> call = restaurantClient.getBill();
                call.enqueue(new Callback<BillList>() {
                    /**
                     * Invoked for a received HTTP response.
                     * <p>
                     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
                     *
                     * @param call
                     * @param response
                     */
                    @Override
                    public void onResponse(Call<BillList> call, Response<BillList> response) {



                        Log.d("Respons sucess raedbill", response.message());

                        billList = response.body().getBillList(); //Spara response från databasen till menuList
                        //haha :/
                        Bill temp =billList.get(billList.size()-1);
                        int test2 =temp.getId();
                        setMaxID(test2);
                        createOrdersEtc();

                    }

                    /**
                     * Invoked when a network exception occurred talking to the server or when an unexpected
                     * exception occurred creating the request or processing the response.
                     *
                     * @param call
                     * @param t
                     */
                    @Override
                    public void onFailure(Call<BillList> call, Throwable t) {

                        Log.d("Response fail", t.getMessage());

                    }

                });

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
            LinearLayout layout = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
            LayoutInflater inflater = LayoutInflater.from(ScrollingActivity.this);
            View row = inflater.inflate(R.layout.order_row, null);

            AutoCompleteTextView ACTV = row.findViewById(R.id.actvIn);
            ImageButton imgBtn = row.findViewById(R.id.comBtn);

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
            ACTV.setAdapter(adapter);
            ACTV.setOnItemClickListener(listenerACTV);
            ACTV.setHint(hint);

            //order comment
            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LinearLayout layout = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                    LayoutInflater inflater = LayoutInflater.from(ScrollingActivity.this);
                    View row = inflater.inflate(R.layout.comment_row, null);

                    EditText commentTV = row.findViewById(R.id.commentET);
                    layout.addView(row);
                }
            });

            layout.addView(row);
        }
    }

    /**
     * Funktionen använder ApiInterface och MenuItem klassen för att köra retrofit.builder på api
     */
    private void readXmlFeed(){

        restaurantClient = RestaurantClient.getINSTANCE();
        Call<MenuItemList> call = restaurantClient.getMenu();
        call.enqueue(new Callback<MenuItemList>() {
            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             *
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<MenuItemList> call, Response<MenuItemList> response) {
                Log.d("Respons sucess", response.message());

                menuList = response.body().getMenuList(); //Spara response från databasen till menuList
                for (MenuItem menuItem: menuList) {
                    String test = menuItem.getType();
                    if(test.equalsIgnoreCase("Förrätt")){
                        starter.add(menuItem.getName());
                    }
                    else if(test.equalsIgnoreCase("Huvudrätt")){
                        main.add(menuItem.getName());
                    }
                    else if(test.equalsIgnoreCase("Efterrätt")){
                        efter.add(menuItem.getName());
                    }
                    else if(test.equalsIgnoreCase("Dryck")){
                        drink.add(menuItem.getName());
                    }
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<MenuItemList> call, Throwable t) {
                Log.d("Response fail", t.getMessage());
            }
        });

    }

    private  void setMaxID(int maxid){
        maxBillID = maxid;

    }

    private void generateOrder(int tableid, String dish, String comment, int billid)
    {


        restaurantClient = RestaurantClient.getINSTANCE();
        Order order = new Order(comment,dish,billid);

        Call<Order> call = restaurantClient.createOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.d("Response successful", response.message());
                Log.d(Order.class.toString(),call.request().toString());
                Log.d(Order.class.toString(),call.request().body().toString());


            }
            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("Response fail", t.getMessage());
                System.out.println("Response fail");
            }
        });




    }

}