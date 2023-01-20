package rmit.ad.e_commerce_app.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.Util;
import rmit.ad.e_commerce_app.Activities.GlobalUserAccess;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    ProductAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static String accessToken;
    RecyclerView recyclerView1;
    String jsonString ="";

    public FavoriteFragment(String accessToken) {
        // Required empty public constructor

        this.accessToken = accessToken;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment(accessToken);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView1 = root.findViewById(R.id.favorite_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(this.getContext(), "Favorite", accessToken);
        new doGetAllFavorites().execute();
        return root;
    }

    private class doGetAllFavorites extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "http://54.151.194.4:3000/getallfavorite?accessToken=" + accessToken;
            jsonString = HttpHandler.getRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                if (!Utils.obtainInstance().getFavoriteProducts().isEmpty()) {
                    Utils.obtainInstance().RemoveAllFavoriteProducts();
                }
                JSONArray jsonArray = new JSONArray(jsonString);
                for(int i = 0; i< jsonArray.length(); i++){
                    JSONObject product = jsonArray.getJSONObject(i);
                    int id = (int) product.get("Id");
                    int seller_id = (int) product.get("seller_id");
                    String category = product.get("category").toString();
                    String title = product.get("title").toString();
                    String price = product.get("price").toString();
                    String color = product.get("colors").toString();
                    String sizes = product.get("sizes").toString();

                    int stars  = (int) product.get("stars");
                    String brand = product.get("brand").toString();
                    String thumbnail = product.get("thumbnail").toString();
                    String description = product.get("descriptions").toString();

                    Utils.obtainInstance().AddToFavorite(new Product(title, price, thumbnail, id, category, brand, 1, seller_id, color, sizes, description, stars));                }
                    adapter.SetUpProducts(Utils.obtainInstance().getFavoriteProducts());
                    recyclerView1.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}