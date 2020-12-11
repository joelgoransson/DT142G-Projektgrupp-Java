package com.example.orderapp1_2.tables;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderapp1_2.CardItem;
import com.example.orderapp1_2.OrderAdapter;
import com.example.orderapp1_2.R;
import com.example.orderapp1_2.retorofit.classes.Bill;
import com.example.orderapp1_2.retorofit.classes.BillList;
import com.example.orderapp1_2.retorofit.classes.MenuItem;
import com.example.orderapp1_2.retorofit.classes.MenuItemList;
import com.example.orderapp1_2.retorofit.classes.Order;
import com.example.orderapp1_2.retorofit.classes.OrderList;
import com.example.orderapp1_2.retorofit.classes.Orders;
import com.example.orderapp1_2.retorofit.classes.OrdersList;
import com.example.orderapp1_2.retorofit.classes.RestaurantClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TablesFragment extends Fragment {

    private TablesViewModel mViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RestaurantClient restaurantClient;
    private ArrayList<CardItem> cardList = new ArrayList<>();
    List<Bill> billList;
    List<Order> orderList;
    List<MenuItem> menuList;
    private View root;
    private final ArrayList<CardItem> orders = new ArrayList<>();

    public static TablesFragment newInstance() {
        return new TablesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.tables_fragment, container, false);
        readBillList();
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TablesViewModel.class);
        // TODO: Use the ViewModel


        System.out.println(cardList.size());

        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("vi är här");
                System.out.println(cardList.size());


            }
        }).start();








    }
    private void readOrdermenuList(){
        System.out.println("readOrdermenyList out println");
        restaurantClient = RestaurantClient.getINSTANCE();
        Call<OrderList> call = restaurantClient.getOrder();
        call.enqueue(new Callback<OrderList>() {
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
            public void onResponse(Call<OrderList> call, Response<OrderList> response) {
                Log.d("Respons sucess", response.message());

                orderList = response.body().getOrderList(); //Spara response från databasen till menuList
                for(CardItem carditem: cardList){
                    int tempbillID =carditem.getBillid();
                    ArrayList<Order> tempList = new ArrayList<>();
                    for(Order order: orderList){
                        int orderBillID = order.getBillnr();
                        if(tempbillID==orderBillID){
                            tempList.add(order);
                        }
                    }
                    carditem.setOrdersTV(tempList);
                }
                readPreptime();

            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<OrderList> call, Throwable t) {
                Log.d("Response fail", t.getMessage());
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
                for(int i=0;i<billList.size();i++){
                    Bill test =billList.get(i);

                    int tablenr = test.getTablenr();
                    int billid= test.getId();
                    cardList.add(new CardItem(billid,Integer.toString(tablenr)));


                }
                readOrdermenuList();
                //CardListSetter(cardList);



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
    private  void readPreptime(){

        System.out.println("pretime out println");
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




                Log.d("Respons sucess raedbill", response.message());

                menuList = response.body().getMenuList(); //Spara response från databasen till menuList
                //ArrayList<CardItem> templist = new ArrayList<>(billList.size());
                for(MenuItem menuitem: menuList){
                    String menuname =menuitem.getName();
                    for(CardItem carditem : cardList){
                        for(Order order : carditem.getOrdersTV()){
                            if(menuname.equals(order.getMenuitemname())){

                                order.setPreptime(menuitem.getPrepTime());

                            }
                            else if(order.getPreptime()!=null){

                            }
                            else{
                                order.setPreptime(0.0);
                            }
                        }
                    }
                }
                createRecyclerView();
                //CardListSetter(cardList);



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



    private void createRecyclerView(){
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new OrderAdapter(cardList);

        recyclerView.setAdapter(adapter);
    }
}