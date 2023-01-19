package rmit.ad.e_commerce_app.CategoryActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.GlobalUserAccess;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class ShoesCategory extends AppCompatActivity {
    String local = "http://192.168.10.3:3000/filtersearch/10/1?title=car&category=";
    String server = "http://54.151.194.4:3000/filtersearch/10/1?title=car&category=";
    String category = "";
    String jsonString = "";
    ProductAdapter adapter;
    ArrayList<Product> products;
    RecyclerView recyclerView1;

    GlobalUserAccess globalUserAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_category);
        globalUserAccess = ((GlobalUserAccess) getApplicationContext());

        recyclerView1 = findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView1.setLayoutManager(gridLayoutManager);

        adapter = new ProductAdapter(ShoesCategory.this, "AllProducts", globalUserAccess.getAccessToken());


        category  = getIntent().getStringExtra("category");
        if(products != null){
            products.clear();
        }

        new GetData().execute();

    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.getRequest(local+category);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Utils utils = new Utils();
            utils.setData(jsonString);
            products = utils.getAllProducts();
            adapter.SetUpProducts(products);
            recyclerView1.setAdapter(adapter);
            System.out.println(local+category);
            Toast.makeText(ShoesCategory.this, local+category,
                    Toast.LENGTH_LONG).show();
        }

    }


}