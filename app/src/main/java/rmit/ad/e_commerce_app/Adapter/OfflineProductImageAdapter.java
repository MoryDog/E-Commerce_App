package rmit.ad.e_commerce_app.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class OfflineProductImageAdapter extends PagerAdapter {
    private List<Bitmap> productImages;
    Context context;

    public OfflineProductImageAdapter(List<Bitmap> productImages, Context context) {
        this.productImages = productImages;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext());
        productImage.setScaleType(ImageView.ScaleType.FIT_XY);
        //Glide.with(context).load(productImages.get(position)).into(productImage);
        productImage.setImageBitmap(productImages.get(position));
        //productImage.setImageResource(productImages.get(position));
        container.addView(productImage, 0);
        return productImage;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
