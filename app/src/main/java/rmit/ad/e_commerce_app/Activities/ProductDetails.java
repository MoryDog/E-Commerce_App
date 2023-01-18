package rmit.ad.e_commerce_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.Adapter.ProductImagesAdapter;
import rmit.ad.e_commerce_app.Fragments.HomeFragment;
import rmit.ad.e_commerce_app.ModelClasses.ProductModel;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class ProductDetails extends AppCompatActivity {
    ViewPager productImagesViewPager;
    TabLayout viewPagerIndicator;
    TextView product_detail_title;
    TextView product_price;
    public static final String KEY_ID_PRODUCT = "Product ID";
    FloatingActionButton addProductButton;


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
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewPager_indicator);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.image1);
        productImages.add(R.drawable.image2);
        productImages.add(R.drawable.image3);
        productImages.add(R.drawable.image4);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);
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


}