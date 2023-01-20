package rmit.ad.e_commerce_app.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import rmit.ad.e_commerce_app.R;

public class DisplayOrderContentViewHolder extends RecyclerView.ViewHolder {
    ImageView product_img;
    TextView orderTitle, orderStatus, productQuantity;
    CardView parent;

    public DisplayOrderContentViewHolder(@NonNull View orderView) {
        super(orderView);
        product_img = orderView.findViewById(R.id.product_image);
        orderTitle = orderView.findViewById(R.id.orderTitle);
        orderStatus = orderView.findViewById(R.id.orderStatus);
        productQuantity = orderView.findViewById(R.id.productQuantity);
        parent = orderView.findViewById(R.id.parent);
    }
}
