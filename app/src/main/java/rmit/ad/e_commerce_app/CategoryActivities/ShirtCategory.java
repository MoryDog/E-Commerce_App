package rmit.ad.e_commerce_app.CategoryActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import rmit.ad.e_commerce_app.Activities.GlobalUserAccess;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class ShirtCategory extends AppCompatActivity {
    ProductAdapter adapter;
    GlobalUserAccess globalUserAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalUserAccess = ((GlobalUserAccess) getApplicationContext());
        setContentView(R.layout.activity_shirt_category);
        RecyclerView recyclerView1 = findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(this, "AllProducts", globalUserAccess.getAccessToken());
        adapter.SetUpProducts(Utils.obtainInstance().getShirtProducts());
        recyclerView1.setAdapter(adapter);
    }
}