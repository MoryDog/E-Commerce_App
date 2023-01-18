package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class LaptopCategory extends AppCompatActivity {

    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_category);
        RecyclerView recyclerView1 = findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(this);
        adapter.SetUpProducts(Utils.obtainInstance().getLaptopProducts());
        recyclerView1.setAdapter(adapter);
    }
}