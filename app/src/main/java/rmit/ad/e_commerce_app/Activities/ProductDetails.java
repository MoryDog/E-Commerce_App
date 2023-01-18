package rmit.ad.e_commerce_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.Adapter.ProductImagesAdapter;

import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;

import rmit.ad.e_commerce_app.Fragments.FavoriteFragment;

import rmit.ad.e_commerce_app.ModelClasses.ProductModel;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class ProductDetails extends AppCompatActivity {
    public static final String KEY_ID_PRODUCT = "Product ID";
    ViewPager productImagesViewPager;
    TabLayout viewPagerIndicator;
    TextView product_detail_title;
    TextView product_price;
    View product_detail_view;
    Button addToCartButton;
    ImageView back_button;
    ToggleButton toggleFavorite;
    String jsonString = "";
    long ProductID;
    List<String> imageLinks = new ArrayList<>();
    ProductModel UpComingProducts;
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        InitViews();

        Intent intent = getIntent();
        if (intent != null){
            ProductID = intent.getLongExtra(KEY_ID_PRODUCT, -1);
            if (ProductID != -1){
                UpComingProducts = Utils.obtainInstance().GetProductByID(ProductID);
                if (UpComingProducts != null){

                    Toast.makeText(ProductDetails.this, "Product id = " + ProductID, Toast.LENGTH_SHORT).show();
                    //InitProductData(UpComingProducts);
                    new getData().execute();

                    InitProductData(UpComingProducts);
                    handleFavoriteProducts(UpComingProducts);

                }
            }
        }

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewPager_indicator);
        product_detail_view = findViewById(android.R.id.content);


        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // add to shopping cart button
        addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(product_detail_view, "Product added to cart", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFavoriteProducts(final ProductModel productModel) {
        ArrayList<ProductModel> favoriteProducts = Utils.obtainInstance().getFavoriteProducts();
        boolean existInFavoriteProducts = false;
        for (ProductModel productModel1: favoriteProducts){
            if (productModel1.getID() == productModel.getID()){
                existInFavoriteProducts = true;
            }
        }
        if (existInFavoriteProducts) {
            toggleFavorite.setEnabled(false);
        } else  {
            toggleFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.obtainInstance().AddToFavorite(productModel)){
                        Toast.makeText(ProductDetails.this, "Products Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductDetails.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProductDetails.this, "Something Wrong Happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void InitViews() {
        toggleFavorite = findViewById(R.id.toggleFavorite);
        product_detail_title = findViewById(R.id.ProductTitle);
        product_price = findViewById(R.id.PriceText);
    }

    private void InitProductData(ProductModel productModel) {
        for(int i =0; i < imageLinks.size(); i++){
            System.out.println(imageLinks);
        }
        List<String> productImages = new ArrayList<>(imageLinks);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages, this);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);

        product_detail_title.setText(productModel.getTitle());
        product_price.setText(productModel.getPrice());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button) {
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // favorite button toggle
    public void onCustomToggleClick(View view) {
        Snackbar.make(product_detail_view, "Product added to favorite", Snackbar.LENGTH_SHORT).show();
    }

    private class getData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.getRequest("http://54.151.194.4:3000/getimages?product_id="+ProductID);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for(int i =0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    imageLinks.add(s3 + jsonObject.get("link").toString());
                }
                InitProductData(UpComingProducts);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}