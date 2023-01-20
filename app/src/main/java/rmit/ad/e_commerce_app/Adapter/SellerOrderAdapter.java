package rmit.ad.e_commerce_app.Adapter;

import static rmit.ad.e_commerce_app.Activities.ProductDetails.KEY_ID_PRODUCT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.DisplayOrderContent;
import rmit.ad.e_commerce_app.Activities.ProductDetails;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Order;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.SellerActivities.SellerOrderFragment;

public class SellerOrderAdapter extends RecyclerView.Adapter<SellerOrderViewHolder> {
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";
    private static final String TAG = "CartProductAdapter";
    private ArrayList<Order> orders = new ArrayList<>();
    Context context;
    private String accessToken;


    public SellerOrderAdapter(Context context, String accessToken) {
        this.context = context;
        this.accessToken = accessToken;
    }

    @NonNull
    @Override
    public SellerOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_order__item_list , parent, false);
        return new SellerOrderViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull SellerOrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderTitle.setText(String.valueOf(orders.get(position).getOrderId()));
        holder.orderStatus.setText(orders.get(position).getOrderStatus());
        holder.orderShippingAddress.setText(orders.get(position).getShippingAddress());
//        Glide.with(context)
//                .asBitmap()
//                .load(s3 + orders.get(position).get..())
//                .into(holder.product_image);

        //Trigger when click on the product image and toast their name
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayOrderContent.class);
                intent.putExtra("orderId", String.valueOf(orders.get(position).getOrderId()));

                context.startActivity(intent);
            }
        });
        holder.orderStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                orders.get(position).setOrderStatus(editable.toString());
            }
        });


        holder.orderUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
//                int quantity = Integer.parseInt((String) holder.quantity.getText());
//                data.get(position).setQuantity(quantity);
//                holder.quantity.setText(String.valueOf(quantity + 1));
                new updateOrderStatus(orders.get(position).getOrderId(), orders.get(position).getOrderStatus()).execute();
            }
        });

        holder.orderDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new deleteAnOrder(orders.get(position).getOrderId()).execute();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public void SetUpProducts(ArrayList<Order> productModels) {
        this.orders = productModels;
        notifyDataSetChanged();
    }

    private class updateOrderStatus extends AsyncTask<Void, Void, Void> {
        String jsonString = "";
        JSONObject payload;
        int orderId;
        String orderStatus;
        public updateOrderStatus(int orderId, String orderStatus) {
            this.orderId = orderId;
            this.orderStatus = orderStatus;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JSONObject payLoadUpdateStatus = new JSONObject();
            try {
                payLoadUpdateStatus.put("accessToken", accessToken);
                payLoadUpdateStatus.put("order_id", orderId);
                payLoadUpdateStatus.put("order_status", orderStatus);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            jsonString = HttpHandler.postRequest("http://54.151.194.4:3000/updateorderstatus", payLoadUpdateStatus);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(context, "Order Status Updated",
                    Toast.LENGTH_LONG).show();
        }

    }


    private class deleteAnOrder extends AsyncTask<Void, Void, Void> {
        String jsonString = "";
        JSONObject payload;
        int orderId;
        public deleteAnOrder(int orderId) {
            this.orderId = orderId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JSONObject payLoadUpdateStatus = new JSONObject();
            try {
                payLoadUpdateStatus.put("accessToken", accessToken);
                payLoadUpdateStatus.put("order_id", orderId);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonString = HttpHandler.postRequest("http://54.151.194.4:3000/deleteorder", payLoadUpdateStatus);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(context, "Order Status Updated",
                    Toast.LENGTH_LONG).show();
        }

    }
}
