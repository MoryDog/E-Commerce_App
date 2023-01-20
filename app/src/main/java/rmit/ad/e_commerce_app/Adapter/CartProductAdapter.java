package rmit.ad.e_commerce_app.Adapter;

import static java.security.AccessController.getContext;
import static rmit.ad.e_commerce_app.Activities.MainActivity.SetBadge;
import static rmit.ad.e_commerce_app.Activities.ProductDetails.KEY_ID_PRODUCT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.MainActivity;
import rmit.ad.e_commerce_app.Activities.ProductDetails;
import rmit.ad.e_commerce_app.Fragments.OrderFragment;
import rmit.ad.e_commerce_app.Fragments.ShoppingCartFragment;
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
    View parentView;
    public CartProductAdapter(Context context, View parent) {
        this.context = context;
        this.parentView = parent;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_item_list , parent, false);
        return new CartViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView totalPriceText =  parentView.findViewById(R.id.totalPrice);
        try{
            for(int i = 0; i< data.size(); i++){
                data.get(i).setQuantity(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        totalPriceText.setText(String.valueOf(calculateTotalPrice()));
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
                totalPriceText.setText(String.valueOf(calculateTotalPrice()));
            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt((String) holder.quantity.getText());
                if(quantity > 1){
                    holder.quantity.setText(String.valueOf(quantity - 1));
                    data.get(position).setQuantity(quantity);
                    totalPriceText.setText(String.valueOf(calculateTotalPrice()));
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
                SetBadge();
            }
        });

    }
    private int calculateTotalPrice(){
        int total = 0;
        try{
            for(int i = 0; i< data.size(); i++){
                total += data.get(i).getQuantity() * Integer.parseInt(data.get(i).getPrice());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  total;
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
