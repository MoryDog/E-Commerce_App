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

import rmit.ad.e_commerce_app.Activities.DisplayOrderContent;
import rmit.ad.e_commerce_app.Activities.ProductDetails;
import rmit.ad.e_commerce_app.ModelClasses.Order;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";
    private static final String TAG = "NotiProductAdapter";
    private ArrayList<Order> data = new ArrayList<>();
    Context context;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_list, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderTitle.setText(String.valueOf(data.get(position).getOrderId()));
        holder.orderStatus.setText(data.get(position).getOrderStatus());
        /*
        Glide.with(context)
                .asBitmap()
                .load(s3 + data.get(position).getThumbnail())
                .into(holder.product_img);
                */


        //Trigger when click on the product image and toast their name
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: Clicked on an image: " + data.get(position).getTitle());
                Intent intent = new Intent(context, DisplayOrderContent.class);
                intent.putExtra("orderId", String.valueOf(data.get(position).getOrderId()));
                Toast.makeText(context, "What is going on " + data.get(position).getOrderId(),
                        Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void SetUpProducts(ArrayList<Order> productModels) {
        this.data = productModels;
        notifyDataSetChanged();
    }
}



