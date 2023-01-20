package rmit.ad.e_commerce_app.Adapter;

import static rmit.ad.e_commerce_app.Activities.ProductDetails.KEY_ID_PRODUCT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.ProductDetails;
import rmit.ad.e_commerce_app.ModelClasses.Order;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;

public class SellerOrderAdapter extends RecyclerView.Adapter<SellerOrderViewHolder> {
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";
    private static final String TAG = "CartProductAdapter";
    private ArrayList<Order> orders = new ArrayList<>();
    Context context;

    public SellerOrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SellerOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_order__item_list , parent, false);
        return new SellerOrderViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull SellerOrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderTitle.setText(orders.get(position).getOrderTitle());
        holder.orderStatus.setText(orders.get(position).getOrderStatus());
//        Glide.with(context)
//                .asBitmap()
//                .load(s3 + orders.get(position).get..())
//                .into(holder.product_image);

        //Trigger when click on the product image and toast their name
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(TAG, "onClick: Clicked on an image: " + orders.get(position).getTitle());
//                Intent intent = new Intent(context, ProductDetails.class);
//                intent.putExtra(KEY_ID_PRODUCT, orders.get(position).getID());
//                context.startActivity(intent);
            }
        });

        holder.orderUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int quantity = Integer.parseInt((String) holder.quantity.getText());
//                data.get(position).setQuantity(quantity);
//                holder.quantity.setText(String.valueOf(quantity + 1));
            }
        });


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setFilteredList(ArrayList<Order> filteredList) {
        this.orders = filteredList;
        notifyDataSetChanged();
    }

    public void SetUpProducts(ArrayList<Order> productModels) {
        this.orders = productModels;
        notifyDataSetChanged();
    }
}
