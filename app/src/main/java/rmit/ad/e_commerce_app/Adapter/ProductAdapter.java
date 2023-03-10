package rmit.ad.e_commerce_app.Adapter;

import static rmit.ad.e_commerce_app.Activities.ProductDetails.KEY_ID_PRODUCT;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.GlobalUserAccess;
import rmit.ad.e_commerce_app.Activities.ProductDetails;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class ProductAdapter extends RecyclerView.Adapter<ViewHolder> {
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";
    private static final String TAG = "ProductAdapter";
    private ArrayList<Product> data = new ArrayList<>();
    Context context;
    String parentActivity;

    long jsonProductId;
    String jsonFavoriteString;
    String accessToken;

    GlobalUserAccess globalUserAccess;

    public ProductAdapter(Context context, String parentActivity, String accessToken) {
        this.context = context;
        this.parentActivity = parentActivity;
        this.accessToken = accessToken;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
        if (parentActivity.equals("AllProducts")){
            holder.DeleteBtn.setVisibility(View.GONE);
        }
        if (parentActivity.equals("Favorite")){
            holder.DeleteBtn.setVisibility(View.VISIBLE);
            holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder b = new AlertDialog.Builder(context);
                    b.setMessage("Do you want to delete " + data.get(position).getTitle() + " from favorites?");
                    b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            jsonProductId = data.get(position).getID();
                            Utils.obtainInstance().RemoveFavoriteProductList(data.get(position));
                            new doDeleteFavorite().execute();
                            notifyDataSetChanged();
                        }
                    });
                    b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    b.create().show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilteredList(ArrayList<Product> filteredList) {
        this.data = filteredList;
        notifyDataSetChanged();
    }

    public void SetUpProducts(ArrayList<Product> productModels) {
        this.data = productModels;
        notifyDataSetChanged();
    }


    private class doDeleteFavorite extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            jsonFavoriteString = HttpHandler.postFavorite("http://54.151.194.4:3000/deletefavorite", accessToken, jsonProductId);
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }
}
