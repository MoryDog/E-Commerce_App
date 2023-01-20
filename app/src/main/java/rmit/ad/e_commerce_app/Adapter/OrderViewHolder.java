package rmit.ad.e_commerce_app.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import rmit.ad.e_commerce_app.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    ImageView product_img;
    TextView orderTitle, orderStatus;
    CardView parent;

    public OrderViewHolder(@NonNull View orderView) {
        super(orderView);
        product_img = orderView.findViewById(R.id.product_image);
        orderTitle = orderView.findViewById(R.id.orderItemTitle);
        orderStatus = orderView.findViewById(R.id.orderItemPrice);
        parent = orderView.findViewById(R.id.parent);
    }
}
