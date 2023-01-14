package rmit.ad.e_commerce_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.RecyclerViewAdapter;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private ArrayList<String> m_name = new ArrayList<>();
    private ArrayList<String> m_imageUrl = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_first, container, false);
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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(m_name, m_imageUrl, getContext());
        recyclerView.setAdapter(adapter);

        // Category slider
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);
        return root;
    }
}