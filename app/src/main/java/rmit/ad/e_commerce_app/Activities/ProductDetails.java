package rmit.ad.e_commerce_app.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.Adapter.ProductImagesAdapter;

import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;

import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class ProductDetails extends AppCompatActivity {
    public static final String KEY_ID_PRODUCT = "Product ID";
    ViewPager productImagesViewPager;
    TabLayout viewPagerIndicator;
    TextView product_detail_title;
    TextView product_price;
    TextView product_colors;
    TextView product_size;
    TextView product_description;
    TextView product_brand;



    View product_detail_view;
    Button addToCartButton;
    ToggleButton toggleFavorite;
    String jsonString = "";
    String jsonFavoriteString = "";
    long jsonProductId;
    long ProductID;
    List<String> imageLinks = new ArrayList<>();
    Product UpComingProducts;
    private String s3 = "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/";

    GlobalUserAccess globalUserAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        InitViews();
        globalUserAccess = ((GlobalUserAccess) getApplicationContext());

        addToCartButton = findViewById(R.id.addToCartButton);

        Intent intent = getIntent();

            ProductID = intent.getLongExtra(KEY_ID_PRODUCT, -1);
            if (ProductID != -1){
                //UpComingProducts = Utils.obtainInstance().GetProductByID(ProductID);
                new getAProduct().execute();
                new getData().execute();
            }


        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewPager_indicator);
        product_detail_view = findViewById(android.R.id.content);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void handleFavoriteProducts(final Product productModel) {
        ArrayList<Product> favoriteProducts = Utils.obtainInstance().getFavoriteProducts();
        boolean existInFavoriteProducts = false;
        for (Product productModel1: favoriteProducts){
            if (productModel1.getID() == productModel.getID()){
                existInFavoriteProducts = true;
            }
        }
        if (existInFavoriteProducts) {
            System.out.println(productModel.getID());
            toggleFavorite.setEnabled(false);
        } else  {
            toggleFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jsonProductId = productModel.getID();
                    new doAddFavorite().execute();
                }
            });
        }
    }

    private void handleCartProducts(final Product cartProductModel) {
        ArrayList<Product> cartProducts = Utils.obtainInstance().getCartProducts();
        boolean existInCartProducts = false;
        for (Product cartProductModel_temp : cartProducts) {
            if (cartProductModel_temp.getID() == cartProductModel.getID()) {
                existInCartProducts = true;
            }
        }
        if (existInCartProducts) {
            addToCartButton.setEnabled(false);
        } else {
            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Utils.obtainInstance().AddToCart(cartProductModel)) {
                        Snackbar.make(product_detail_view, "Product added to Cart", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(product_detail_view, "Something Wrong Happened, try again", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void InitViews() {
        toggleFavorite = findViewById(R.id.toggleFavorite);
        product_detail_title = findViewById(R.id.ProductTitle);
        product_price = findViewById(R.id.PriceText);
        product_colors = findViewById(R.id.colorString);
        product_size = findViewById(R.id.sizeString);
        product_description = findViewById(R.id.desciptionText);
        product_brand = findViewById(R.id.sellerName);
    }

    private void InitProductData(Product productModel) {
        for(int i =0; i < imageLinks.size(); i++){
            System.out.println(imageLinks);
        }
        List<String> productImages = new ArrayList<>(imageLinks);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages, this);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);

        try {
            product_detail_title.setText(productModel.getTitle());
            product_price.setText(productModel.getPrice());
            product_colors.setText(productModel.getColors());
            product_size.setText(productModel.getSizes());
            product_brand.setText(productModel.getBrand());
            product_description.setText(productModel.getDescription());
            System.out.println(productModel.getBrand());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if (UpComingProducts != null){
            //InitProductData(UpComingProducts);
            handleFavoriteProducts(UpComingProducts);
            handleCartProducts(UpComingProducts);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Respond to the action bar's Up/Home button
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // favorite button toggle
    public void onCustomToggleClick(View view) {

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

    private class doAddFavorite extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            jsonFavoriteString = HttpHandler.postFavorite("http://54.151.194.4:3000/addfavorite", globalUserAccess.getAccessToken(), jsonProductId);
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }

    private class getAProduct extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.getRequest("http://54.151.194.4:3000/getaproduct?id="+ProductID);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                Utils utils = new Utils();
                utils.setData(jsonString);
                Toast.makeText(ProductDetails.this, "This is my product id!" + ProductID,
                        Toast.LENGTH_LONG).show();
                UpComingProducts = utils.getAllProducts().get(0);

                InitProductData(UpComingProducts);
            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();
            }

        }
    }
}