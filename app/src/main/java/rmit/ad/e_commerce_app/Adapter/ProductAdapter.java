package rmit.ad.e_commerce_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.ModelClasses.ProductModel;
import rmit.ad.e_commerce_app.R;

public class ProductAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = "ProductAdapter";
    private ArrayList<ProductModel> data = new ArrayList<>();
    Context context;

    public ProductAdapter(ArrayList<ProductModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product_name.setText(data.get(position).getName());
        holder.product_price.setText(data.get(position).getPrice());
        Glide.with(context)
                .asBitmap()
                .load(data.get(position).getImg_name())
                .into(holder.product_image);

        //Trigger when click on the product image and toast their name
        holder.product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on an image: " + data.get(position).getName());
                Toast.makeText(context, data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilteredList(ArrayList<ProductModel> filteredList) {
        this.data = filteredList;
        notifyDataSetChanged();
    }
}
