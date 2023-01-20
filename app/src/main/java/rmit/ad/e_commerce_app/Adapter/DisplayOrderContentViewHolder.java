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
    TextView orderItemTitle, orderItemPrice, orderItemquantity;
    CardView parent;

    public DisplayOrderContentViewHolder(@NonNull View orderView) {
        super(orderView);
        product_img = orderView.findViewById(R.id.product_image);
        orderItemTitle = orderView.findViewById(R.id.orderItemTitle);
        orderItemPrice = orderView.findViewById(R.id.orderItemPrice);
        orderItemquantity = orderView.findViewById(R.id.orderItemsQuantity);
        parent = orderView.findViewById(R.id.parent);
    }
}
