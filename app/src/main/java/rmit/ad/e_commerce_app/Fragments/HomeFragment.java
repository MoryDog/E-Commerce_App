package rmit.ad.e_commerce_app.Fragments;

import android.os.Bundle;

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

import rmit.ad.e_commerce_app.ModelClasses.ProductModel;
import rmit.ad.e_commerce_app.Adapter.ProductAdapter;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Adapter.RecyclerViewAdapter;

public class HomeFragment extends Fragment {

    private ArrayList<String> m_name = new ArrayList<>();
    private ArrayList<String> m_imageUrl = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mImageUrl = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    ProductAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        RecyclerView recyclerView1 = root.findViewById(R.id.new_product_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(InitProductData(), this.getContext());
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

    public ArrayList<ProductModel> InitProductData(){
        ArrayList<ProductModel> holder = new ArrayList<>();
        ProductModel ob1 = new ProductModel(1, "iPhone 13", "99999 $", "https://www.svstore.vn/uploads/source/iphone-13prm/iphone-13-pro-max-blue-select.png", "Phone", "Apple", 1);
        holder.add(ob1);

        ProductModel ob2 = new ProductModel(1, "T-Shirt", "99999 $", "https://chapel.vn/wp-content/uploads/2021/07/hn.jpg", "Phone", "Apple", 1);
        holder.add(ob2);

        ProductModel ob3 = new ProductModel(1, "Rolex", "99999 $", "https://transform.octanecdn.com/fitLogo/400x500/https://dynamix-cdn.s3.amazonaws.com/jacobandcocom/jacobandcocom_423193262.png", "Phone", "Apple", 1);
        holder.add(ob3);

        ProductModel ob4 = new ProductModel(1, "Shoes", "99999 $", "https://cdn.shopify.com/s/files/1/1626/5391/products/Balenciaga-Triple-S-Nude-Transparent-Sole-Crepslocker-Side_f26facf3-2c43-448b-b5f5-a75381a6b209.jpg?v=1652088899", "Phone", "Apple", 1);
        holder.add(ob4);

        ProductModel ob5 = new ProductModel(1, "Cosmetic", "99999 $", "https://product.hstatic.net/1000341646/product/hera-sensual-powder-matte-499-rosy-suede-2_60f6f3f63e0e414c9a3e63d333e19e11.jpg", "Phone", "Apple", 1);
        holder.add(ob5);

        ProductModel ob6 = new ProductModel(1, "Household", "99999 $", "https://cdn.nguyenkimmall.com/images/detailed/727/10049167-binh-dun-sieu-toc-sharp-ekj-10dvps-bk-1.jpg", "Phone", "Apple", 1);
        holder.add(ob6);

        ProductModel ob7 = new ProductModel(1, "Health", "99999 $", "https://bucket.nhanh.vn/store/4726/ps/20210819/19082021040855_DSCF0825.png", "Phone", "Apple", 1);
        holder.add(ob7);

        ProductModel ob8 = new ProductModel(1, "Laptops", "99999 $", "https://m.media-amazon.com/images/I/71NIJloNGoL._SL1500_.jpg", "Phone", "Apple", 1);
        holder.add(ob8);


        return holder;
    }
}