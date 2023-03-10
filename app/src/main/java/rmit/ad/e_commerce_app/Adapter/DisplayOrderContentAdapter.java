package rmit.ad.e_commerce_app.Adapter;

import static rmit.ad.e_commerce_app.Activities.ProductDetails.KEY_ID_PRODUCT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.ProductDetails;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Order;
import rmit.ad.e_commerce_app.ModelClasses.OrderItems;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class DisplayOrderContentAdapter extends RecyclerView.Adapter<DisplayOrderContentViewHolder>{
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";
    private static final String TAG = "NotiProductAdapter";
    private ArrayList<Product> data = new ArrayList<>();
    Context context;

    public DisplayOrderContentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DisplayOrderContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_order__content_item_list, parent, false);
        return new DisplayOrderContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayOrderContentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderItemTitle.setText(data.get(position).getTitle());
        holder.orderItemquantity.setText(String.valueOf(data.get(position).getQuantityOrdered()));
        holder.orderItemPrice.setText(String.valueOf(data.get(position).getPrice()));
        Glide.with(context)
                .asBitmap()
                .load(s3 + data.get(position).getThumbnail())
                .into(holder.product_img);


        //Trigger when click on the product image and toast their name
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: Clicked on an image: " + data.get(position).getTitle());
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra(KEY_ID_PRODUCT, data.get(position).getID());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void SetUpProducts(ArrayList<Product> productModels) {
        this.data = productModels;
        notifyDataSetChanged();
    }

}
