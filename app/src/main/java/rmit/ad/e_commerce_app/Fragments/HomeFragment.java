package rmit.ad.e_commerce_app.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Adapter.RecyclerViewAdapter;
import rmit.ad.e_commerce_app.Utils;

public class HomeFragment extends Fragment {

    private ArrayList<String> m_name = new ArrayList<>();
    private ArrayList<String> m_imageUrl = new ArrayList<>();
    List<String> SortTypelist;
    private SearchView searchView;
    ImageButton filterBtn;
    RecyclerView recyclerView1;
    ProductAdapter adapter;
    Utils utils;

    ArrayList<Product> test;

    static String accessToken;

    public HomeFragment(String accessToken) {
        this.accessToken = accessToken;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils();

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        filterBtn = root.findViewById(R.id.filter_button);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAlertDialog();
            }
        });

        searchView = root.findViewById(R.id.SearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                new GetSearchGeneral(query).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });


        recyclerView1 = root.findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(getContext(), "AllProducts", accessToken);
        if (!(test == null)){
            test.clear();
        }
        new GetData().execute();


        m_imageUrl = new ArrayList<>();
        m_name = new ArrayList<>();
        m_imageUrl.add("https://pkshoes.store/wp-content/uploads/2021/12/ff3d2f6029d4fde56fd49f8dd441780-600x600.jpg");
        m_name.add("Shoes");

        m_imageUrl.add("https://chapel.vn/wp-content/uploads/2021/07/hn.jpg");
        m_name.add("Shirts");

        m_imageUrl.add("https://cdn.watchstore.vn/uploads/images/601.OM.0183.LR-1653461927957.jpg");
        m_name.add("Watches");

        m_imageUrl.add("https://cuongplus.vn/uploads/product/full_thlhhu6s-1179-iphone-13-pro-max-128gb-chinh-hang-vn-a.png");
        m_name.add("Phones");

        RecyclerView recyclerView = root.findViewById(R.id.rec_category);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(m_name, m_imageUrl, this.getContext());
        recyclerView.setAdapter(adapter);


        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.image6, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image5, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image7, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image8, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);
        return root;
    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        String productData = "";
        @Override
        protected Void doInBackground(Void... voids) {
            productData = HttpHandler.getRequest("http://54.151.194.4:3000/getall/100/1");
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

    private void CreateAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Sort Product");
        builder.setSingleChoiceItems(R.array.SortType, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                String arr[] = getResources().getStringArray(R.array.SortType);
                if (position == 0) {
                    new sortProduct("cheap").execute();
                } else if (position == 1){
                    new sortProduct("expensive").execute();
                }
            }
        });
        builder.create();
        builder.show();
    }


    private class GetSearchGeneral extends AsyncTask<Void, Void, Void> {
        String productData = "";
        String query = "";
        public GetSearchGeneral(String query) {
            this.query = query;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productData = HttpHandler.getRequest("http://54.151.194.4:3000/search/100/1?input=" + query);
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

    private class sortProduct extends AsyncTask<Void, Void, Void> {
        String productData = "";
        String query = "";
        public sortProduct(String query) {
            this.query = query;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(query.equals("cheap")){
                productData = HttpHandler.getRequest("http://54.151.194.4:3000/sortprice?price=cheap");
            } else if (query.equals("expensive")) {
                productData = HttpHandler.getRequest("http://54.151.194.4:3000/sortprice?price=expensive");
            }
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