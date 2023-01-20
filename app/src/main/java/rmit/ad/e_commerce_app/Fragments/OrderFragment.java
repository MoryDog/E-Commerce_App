package rmit.ad.e_commerce_app.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONObject;

import rmit.ad.e_commerce_app.Adapter.OrderAdapter;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    OrderAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static String accessToken;

    public OrderFragment(String accessToken) {

        // Required empty public constructor
        this.accessToken = accessToken;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment(accessToken);
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
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        RecyclerView recyclerView1 = root.findViewById(R.id.notifications_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        adapter = new OrderAdapter(this.getContext());
        adapter.SetUpProducts(Utils.obtainInstance().getCartProducts());
        recyclerView1.setAdapter(adapter);

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

            //Toast.makeText(root.getContext(), "Placed Order", Toast.LENGTH_SHORT).show();
        }

    }
}