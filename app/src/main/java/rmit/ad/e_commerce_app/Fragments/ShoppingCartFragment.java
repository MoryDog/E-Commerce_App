package rmit.ad.e_commerce_app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import rmit.ad.e_commerce_app.Activities.Checkout;
import rmit.ad.e_commerce_app.Activities.MainActivity;
import rmit.ad.e_commerce_app.Adapter.CartProductAdapter;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
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
        View root = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        RecyclerView recyclerView1 = root.findViewById(R.id.shoppingCart_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        adapter = new CartProductAdapter(this.getContext());

        adapter.SetUpProducts(Utils.obtainInstance().getCartProducts());
        recyclerView1.setAdapter(adapter);

        checkOutButton = root.findViewById(R.id.checkOutButton);

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppingCartFragment.class);
                Toast.makeText(getActivity(), "Proceed to check out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        return root;
    }
}