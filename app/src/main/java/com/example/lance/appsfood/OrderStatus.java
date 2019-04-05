package com.example.lance.appsfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Pedido;
import ViewHolder.OrderViewHolder;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public  RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Pedido, OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_status );

        database = FirebaseDatabase.getInstance();
        pedidos = database.getReference("Requests");
        
        recyclerView = (RecyclerView)findViewById( R.id.listOrders );
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
        
        loadOrders(Common.currentUser.getPhone());

    }

    private void loadOrders(String phone) {

        adapter = new FirebaseRecyclerAdapter<Pedido, OrderViewHolder>(
                Pedido.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                pedidos.orderByChild( "phone" )
                .equalTo( phone )
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Pedido model, int position) {

                viewHolder.txtOrderId.setText( adapter.getRef( position ).getKey() );
                viewHolder.txtOrderStatus.setText( convertCodeToStatus(model.getStatus()) );
                viewHolder.txtOrderAddress.setText( model.getAddress() );
                viewHolder.txtOrderPhone.setText( model.getPhone() );

            }
        };

        recyclerView.setAdapter( adapter );

    }

    private String convertCodeToStatus(String status) {
        if(status.equals( "0" ))
            return "Pedido";
        else if(status.equals( "1" ))
            return "En camino";
        else
            return "Entregado";
    }
}
