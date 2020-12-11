package com.example.orderapp1_2;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp1_2.retorofit.classes.Bill;
import com.example.orderapp1_2.retorofit.classes.BillList;
import com.example.orderapp1_2.retorofit.classes.Order;
import com.example.orderapp1_2.retorofit.classes.RestaurantClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    private ArrayList<CardItem> cardlist;
    private RestaurantClient restaurantClient;
    List<Bill> billList;

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public TextView bordTV;
        public TextView infoTV;

        public OrderViewHolder(View itemView) {
            super(itemView);
            bordTV = itemView.findViewById(R.id.bordTextView);
            infoTV = itemView.findViewById(R.id.infoTextView);
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card,parent,false);
        OrderViewHolder ovh = new OrderViewHolder(v);
        return ovh;
    }

    public OrderAdapter(ArrayList<CardItem> cardlist){
        this.cardlist = cardlist;
    }
    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        CardItem item = cardlist.get(position);
        holder.bordTV.setText("Bord " + item.getBordTV());
        String Dishes = "";
        ArrayList<Order> orderlist = item.getOrdersTV();
        Collections.sort(orderlist,(o1, o2) ->o2.getPreptime().compareTo(o1.getPreptime()));


        Order tidOrder = orderlist.get(0);
        double tid = tidOrder.getPreptime();
        int riktigTid = (int) tid;

        for(Order order: orderlist){
            Dishes += order.getMenuitemname()+" - " + order.getComment() + "  " +order.getPreptime()+ "\n";
        }
        holder.infoTV.setText(Dishes);


        TimedEvent timedEvent = new TimedEvent(item);
        Timer timer = new Timer();
        timer.schedule(timedEvent, riktigTid*200);

    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return cardlist.size();
    }

    class TimedEvent extends TimerTask
    {
        private CardItem item;
        TimedEvent(CardItem item)
        {
            this.item = item;
        }

        public void run ()
        {
            readBillList(item);
        }
    }
    private void Billupdater(CardItem item)
    {
        System.out.println("Skiten körs!!!!");

        restaurantClient = RestaurantClient.getINSTANCE();
        Bill billupdater = new Bill(item.getBillid(),"Tillagad" ,Integer. parseInt(item.getBordTV()) ,1,item.getTime());

        int id = item.getBillid();
        String idString = Integer.toString(id);

        Call<Bill> call = restaurantClient.updateBill(idString, billupdater);
        call.enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {
                Log.d("Response successful", response.message());
                Log.d(Order.class.toString(),call.request().toString());
                Log.d(Order.class.toString(),call.request().body().toString());
            }
            @Override
            public void onFailure(Call<Bill> call, Throwable t) {
                Log.d("Response fail", t.getMessage());
                System.out.println("Response fail");
            }
        });
    }

    private  void readBillList(CardItem item){

        System.out.println("readbilllist out println");
        restaurantClient = RestaurantClient.getINSTANCE();
        Call<BillList> call = restaurantClient.getBill();
        call.enqueue(new Callback<BillList>() {

            @Override
            public void onResponse(Call<BillList> call, Response<BillList> response) {

                Log.d("Respons sucess raedbill", response.message());

                billList = response.body().getBillList(); //Spara response från databasen till menuList
                for(Bill bill: billList){
                    if (bill.getId() == item.getBillid())
                    {
                        if(bill.getStatus().equals("KÖKET"))
                        {
                            Billupdater(item);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BillList> call, Throwable t) {

                Log.d("Response fail", t.getMessage());

            }
        });

    }
}
