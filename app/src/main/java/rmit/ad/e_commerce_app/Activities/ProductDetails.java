package rmit.ad.e_commerce_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.Adapter.ProductImagesAdapter;
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
    ToggleButton toggleFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        InitViews();

        Intent intent = getIntent();
        if (intent != null){
            long ProductID = intent.getLongExtra(KEY_ID_PRODUCT, -1);
            if (ProductID != -1){
                ProductModel UpComingProducts = Utils.obtainInstance().GetProductByID(ProductID);
                if (UpComingProducts != null){
                    InitProductData(UpComingProducts);
                }
            }
        }

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewPager_indicator);

        List<String> productImages = new ArrayList<>();
        productImages.add("https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/318751445_2569676449842092_1285895474581622008_n.jpg.png");
        productImages.add("https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/318751445_2569676449842092_1285895474581622008_n.jpg.png");
        productImages.add("https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/318751445_2569676449842092_1285895474581622008_n.jpg.png");
        productImages.add("https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/318751445_2569676449842092_1285895474581622008_n.jpg.png");

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages, this);
        productImagesViewPager.setAdapter(productImagesAdapter);
        product_detail_view = findViewById(android.R.id.content);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);

        addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(product_detail_view, "Product added to cart", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void InitViews() {
        product_detail_title = findViewById(R.id.ProductTitle);
        product_price = findViewById(R.id.PriceText);
    }

    private void InitProductData(ProductModel productModel) {
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
}