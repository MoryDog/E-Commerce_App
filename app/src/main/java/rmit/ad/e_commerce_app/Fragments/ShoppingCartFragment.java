package rmit.ad.e_commerce_app.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.Checkout;
import rmit.ad.e_commerce_app.Activities.MainActivity;
import rmit.ad.e_commerce_app.Adapter.CartProductAdapter;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.OrderItems;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment {
    CartProductAdapter adapter;
    Button checkOutButton;
    EditText shippingAddress;
    TextView totalPrice;
    Context context;
    AlertDialog.Builder builder;
    View root, cartView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    static String  accessToken;

    public ShoppingCartFragment(String accessToken) {
        // Required empty public constructor
        this.accessToken = accessToken;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCartFragment newInstance(String param1, String param2) {
        ShoppingCartFragment fragment = new ShoppingCartFragment(accessToken);
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
        root = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        RecyclerView recyclerView1 = root.findViewById(R.id.shoppingCart_rec);
        shippingAddress = root.findViewById(R.id.shippingAddress);
        totalPrice = root.findViewById(R.id.totalPrice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        adapter = new CartProductAdapter(this.getContext());

        adapter.SetUpProducts(Utils.obtainInstance().getCartProducts());
        recyclerView1.setAdapter(adapter);

        // Function for checkout button
        checkOutButton = root.findViewById(R.id.checkOutButton);
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shippingAddress.getText().toString().trim().isEmpty()) {
                    Snackbar.make(root, "Shipping address must not be empty!", Snackbar.LENGTH_SHORT).show();
                } else if (shippingAddress.getText().toString().trim().length() < 10) {
                    Snackbar.make(root, "Your address must have at least 10 characters", Snackbar.LENGTH_SHORT).show();
                } else if (shippingAddress.getText().toString().trim().length() > 10) {
                    AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                    b.setMessage("Do you want to place order?");
                    b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getContext(), "Thank you for your purchase!", Toast.LENGTH_SHORT).show();

                            JSONObject orderPayload = new JSONObject();
                            try {
                                ArrayList<Product> products = Utils.obtainInstance().getCartProducts();
                                ArrayList<OrderItems> orderItems = new ArrayList<>();
                                for(Product p : products){
                                    orderItems.add(new OrderItems ((int)p.getID(), p.getQuantity()));
                                }
                                orderPayload.put("accessToken", accessToken);
                                orderPayload.put("total", totalPrice.getText().toString());
                                orderPayload.put("shipping_address", shippingAddress.getText().toString());
                                JSONArray orderItemsArray = new JSONArray();
                                for(int j = 0; j< orderItems.size(); j++){
                                    JSONObject item = new JSONObject();
                                    item.put("product_id", orderItems.get(j).getID());
                                    item.put("quantity", orderItems.get(j).getQuantity());
                                    orderItemsArray.put(item);
                                }
                                orderPayload.put("items",orderItemsArray);
                                System.out.println(orderPayload);

                                adapter.SetUpProducts(new ArrayList<Product>());
                                recyclerView1.setAdapter(adapter);
                                new sendOrderPayload(orderPayload).execute();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    b.create().show();
                } else {
                    Snackbar.make(root, "Please check your input for missing characters", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    private class sendOrderPayload extends AsyncTask<Void, Void, Void> {
        String jsonString = "";
        JSONObject payload;
        public sendOrderPayload(JSONObject orderPayload) {
            this.payload = orderPayload;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.postRequest("http://54.151.194.4:3000/neworder", payload);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(root.getContext(), "Placed Order", Toast.LENGTH_SHORT).show();
        }

    }
}