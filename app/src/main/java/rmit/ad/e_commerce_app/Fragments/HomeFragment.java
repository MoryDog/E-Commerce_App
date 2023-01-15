package rmit.ad.e_commerce_app.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import rmit.ad.e_commerce_app.ProductAdapter;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.RecyclerViewAdapter;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private ArrayList<String> m_name = new ArrayList<>();
    private ArrayList<String> m_imageUrl = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mImageUrl = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        HomeFragment homeFragment = new HomeFragment();

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

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rec_category);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(m_name, m_imageUrl, this.getContext());
        recyclerView.setAdapter(adapter);


        mImageUrl = new ArrayList<>();
        mName = new ArrayList<>();
        mPrice = new ArrayList<>();
        mImageUrl.add("https://orbitvu.com/wp-content/uploads/2021/11/sport-shoe-white-background.jpg");
        mPrice.add("999999 VNĐ");
        mName.add("Shoes");

        mImageUrl.add("https://chapel.vn/wp-content/uploads/2021/07/hn.jpg");
        mPrice.add("999999 VNĐ");
        mName.add("T-Shirts");

        mImageUrl.add("https://transform.octanecdn.com/fitLogo/400x500/https://dynamix-cdn.s3.amazonaws.com/jacobandcocom/jacobandcocom_423193262.png");
        mPrice.add("999999 VNĐ");
        mName.add("Watches");

        mImageUrl.add("https://www.svstore.vn/uploads/source/iphone-13prm/iphone-13-pro-max-blue-select.png");
        mPrice.add("999999 VNĐ");
        mName.add("iPhone 13 Pro");

        mImageUrl.add("https://product.hstatic.net/1000341646/product/hera-sensual-powder-matte-499-rosy-suede-2_60f6f3f63e0e414c9a3e63d333e19e11.jpg");
        mPrice.add("999999 VNĐ");
        mName.add("Cosmetic");

        mImageUrl.add("https://cdn.nguyenkimmall.com/images/detailed/727/10049167-binh-dun-sieu-toc-sharp-ekj-10dvps-bk-1.jpg");
        mPrice.add("999999 VNĐ");
        mName.add("Household");

        mImageUrl.add("https://bucket.nhanh.vn/store/4726/ps/20210819/19082021040855_DSCF0825.png");
        mPrice.add("999999 VNĐ");
        mName.add("Health");

        mImageUrl.add("https://m.media-amazon.com/images/I/71NIJloNGoL._SL1500_.jpg");
        mPrice.add("999999 VNĐ");
        mName.add("Laptops");

        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView1 = (RecyclerView) root.findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        ProductAdapter adapter1 = new ProductAdapter(mName, mImageUrl, mPrice, this.getContext());
        recyclerView1.setAdapter(adapter1);

        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}