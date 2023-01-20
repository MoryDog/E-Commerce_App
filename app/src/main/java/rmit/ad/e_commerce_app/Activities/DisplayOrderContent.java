package rmit.ad.e_commerce_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Adapter.DisplayOrderContentAdapter;
import rmit.ad.e_commerce_app.Adapter.OrderAdapter;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class DisplayOrderContent extends AppCompatActivity {


    GlobalUserAccess globalUserAccess;
    Utils utils;

    ArrayList<Product> test;
    RecyclerView recyclerView1;
    DisplayOrderContentAdapter adapter;
    String order_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_content);
        globalUserAccess = ((GlobalUserAccess)getApplicationContext());
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        order_id = getIntent().getStringExtra("orderId");

        recyclerView1 = findViewById(R.id.orderContent_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        adapter = new DisplayOrderContentAdapter(this);

        new orderItemsData().execute();

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

    private class orderItemsData extends AsyncTask<Void, Void, Void> {
        String productData = "";
        @Override
        protected Void doInBackground(Void... voids) {
            productData = HttpHandler.getRequest("http://54.151.194.4:3000/getorderitems?accessToken="+globalUserAccess.getAccessToken()+"&order_id=" + order_id);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            utils = new Utils();
            utils.setData(productData);
            test = utils.getAllProducts();
            adapter.SetUpProducts(test);
            recyclerView1.setAdapter(adapter);
        }
    }

}