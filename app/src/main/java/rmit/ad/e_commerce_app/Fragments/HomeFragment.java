package rmit.ad.e_commerce_app.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.ModelClasses.ProductModel;
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

    ProductAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        RecyclerView recyclerView1 = root.findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(this.getContext());
        adapter.SetUpProducts(Utils.obtainInstance().getAllProducts());
        recyclerView1.setAdapter(adapter);


        m_imageUrl = new ArrayList<>();
        m_name = new ArrayList<>();
        m_imageUrl.add("https://cdn-icons-png.flaticon.com/512/314/314434.png");
        m_name.add("Shoes");

        m_imageUrl.add("https://cdn-icons-png.flaticon.com/512/164/164579.png");
        m_name.add("T-Shirts");

        m_imageUrl.add("https://img.favpng.com/5/4/19/watch-icon-png-favpng-7DbuprFWeg0QNjWuthWARZvZz.jpg");
        m_name.add("Watches");

        m_imageUrl.add("https://icons.iconarchive.com/icons/designbolts/free-multimedia/1024/iPhone-icon.png");
        m_name.add("Phones");

        m_imageUrl.add("https://cdn-icons-png.flaticon.com/512/3501/3501241.png");
        m_name.add("Cosmetic");

        m_imageUrl.add("https://static.vecteezy.com/system/resources/previews/005/092/618/original/flat-style-electric-kettle-icon-on-light-green-background-illustration-vector.jpg");
        m_name.add("Household");

        m_imageUrl.add("https://media1.thehungryjpeg.com/thumbs2/ori_3701521_c85xly460opv0ewhvckm48jc05583w53tucpqb7g_medicine-icon-app.png");
        m_name.add("Health");

        m_imageUrl.add("https://cdn-icons-png.flaticon.com/512/641/641825.png");
        m_name.add("Laptops");

        RecyclerView recyclerView = root.findViewById(R.id.rec_category);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(m_name, m_imageUrl, this.getContext());
        recyclerView.setAdapter(adapter);


        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);
        return root;
    }

    private void CreateAlertDialog() {
        SortTypelist = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Sort Product");
        builder.setSingleChoiceItems(R.array.SortType, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                String arr[] = getResources().getStringArray(R.array.SortType);
                if(position == 0){
                    SortTypelist.add(arr[0]);
                    SortTypelist.remove(arr[1]);
                    SortTypelist.remove(arr[2]);
                } else if (position == 1) {
                    SortTypelist.add(arr[1]);
                    SortTypelist.remove(arr[0]);
                    SortTypelist.remove(arr[2]);
                } else if (position == 2){
                    SortTypelist.add(arr[2]);
                    SortTypelist.remove(arr[1]);
                    SortTypelist.remove(arr[0]);
                }
            }
        });

        builder.setPositiveButton("Sort", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String data = "";
                for (String item:SortTypelist){
                    data = data + "" + item;
                }
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.create();
        builder.show();
    }

    private void filterList(String newText) {
        ArrayList<ProductModel> filteredList = new ArrayList<>();
        for (ProductModel product: Utils.obtainInstance().getAllProducts()){
            if (product.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(product);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this.getContext(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }
}