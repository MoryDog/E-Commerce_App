package rmit.ad.e_commerce_app.SellerActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import rmit.ad.e_commerce_app.Adapter.CartProductAdapter;
import rmit.ad.e_commerce_app.Adapter.SellerOrderAdapter;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerOrderFragment extends Fragment {
    SellerOrderAdapter adapter;
    EditText orderStatus;
    TextView orderTittle;
    View root, temp;
    ToggleButton orderUpdateButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellerOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellerOrderFragment newInstance(String param1, String param2) {
        SellerOrderFragment fragment = new SellerOrderFragment();
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
        root = inflater.inflate(R.layout.fragment_seller_order, container, false);
        RecyclerView recyclerView1 = root.findViewById(R.id.selllerOrder_Rec);

        orderTittle = root.findViewById(R.id.orderTitle);
        orderStatus = root.findViewById(R.id.orderStatus);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        adapter = new SellerOrderAdapter(this.getContext());

        adapter.SetUpProducts(Utils.obtainInstance().getOrders());
        recyclerView1.setAdapter(adapter);
        return root;
    }
}