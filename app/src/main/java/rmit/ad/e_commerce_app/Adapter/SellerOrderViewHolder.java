package rmit.ad.e_commerce_app.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import rmit.ad.e_commerce_app.R;

public class SellerOrderViewHolder extends RecyclerView.ViewHolder {
    ImageView product_image;
    TextView orderTitle;
    TextView orderStatus;
    TextView orderShippingAddress;
    ToggleButton orderUpdateButton;
    ImageButton orderDeleteButton;
    CardView parent;


    public SellerOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        product_image = itemView.findViewById(R.id.product_image);
        orderTitle = itemView.findViewById(R.id.orderItemTitle);
        orderStatus = itemView.findViewById(R.id.orderItemPrice);
        orderShippingAddress = itemView.findViewById(R.id.orderShipAddress);
        orderUpdateButton = itemView.findViewById(R.id.orderUpdateButton);
        orderDeleteButton = itemView.findViewById(R.id.orderDeleteButton);
        parent = itemView.findViewById(R.id.parent);
    }
}
