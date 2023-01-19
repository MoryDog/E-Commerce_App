package rmit.ad.e_commerce_app.CategoryActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class ShoesCategory extends AppCompatActivity {
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_category);
        RecyclerView recyclerView1 = findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(this, "AllProducts");
        adapter.SetUpProducts(Utils.obtainInstance().getShoeProducts());
        recyclerView1.setAdapter(adapter);
    }
}