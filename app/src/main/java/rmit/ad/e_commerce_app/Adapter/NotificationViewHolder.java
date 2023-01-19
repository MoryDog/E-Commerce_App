package rmit.ad.e_commerce_app.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import rmit.ad.e_commerce_app.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    ImageView product_img;
    TextView orderTitle, orderStatus;
    CardView parent;

    public NotificationViewHolder(@NonNull View orderView) {
        super(orderView);
        product_img = orderView.findViewById(R.id.product_img);
        orderTitle = orderView.findViewById(R.id.orderTitle);
        orderStatus = orderView.findViewById(R.id.orderStatus);
    }
}
