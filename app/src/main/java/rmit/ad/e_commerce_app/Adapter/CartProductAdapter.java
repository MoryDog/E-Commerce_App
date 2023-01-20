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
import rmit.ad.e_commerce_app.ModelClasses.OrderItems;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class CartProductAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";
    private static final String TAG = "CartProductAdapter";
    private ArrayList<Product> data = new ArrayList<>();
    private final ArrayList<OrderItems> orderItems = new ArrayList<>();
    Context context;

    public CartProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_item_list , parent, false);
        return new CartViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product_name.setText(data.get(position).getTitle());
        holder.product_price.setText(data.get(position).getPrice());
        Glide.with(context)
                .asBitmap()
                .load(s3 + data.get(position).getThumbnail())
                .into(holder.product_image);

        //Trigger when click on the product image and toast their name
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on an image: " + data.get(position).getTitle());
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra(KEY_ID_PRODUCT, data.get(position).getID());
                context.startActivity(intent);
            }
        });
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt((String) holder.quantity.getText());
                data.get(position).setQuantity(quantity);
                holder.quantity.setText(String.valueOf(quantity + 1));
            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt((String) holder.quantity.getText());
                if(quantity > 1){
                    holder.quantity.setText(String.valueOf(quantity - 1));
                    data.get(position).setQuantity(quantity);
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for (OrderItems item : orderItems) {
//                    orderItems.remove(item);
//                }
                System.out.println("Clicked Delete");
                System.out.println(data.get(position).getID());
                Utils.obtainInstance().RemoveProductInCart(data.get(position));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilteredList(ArrayList<Product> filteredList) {
        this.data = filteredList;

    }

    public void SetUpProducts(ArrayList<Product> productModels) {
        this.data = productModels;
        notifyDataSetChanged();
    }
}
