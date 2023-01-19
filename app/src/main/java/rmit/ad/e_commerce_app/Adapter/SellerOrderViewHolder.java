package rmit.ad.e_commerce_app.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import rmit.ad.e_commerce_app.R;

public class SellerOrderViewHolder extends RecyclerView.ViewHolder {
    ImageView product_image;
    TextView orderTitle;
    TextView orderStatus;
    ToggleButton orderUpdateButton;
    CardView parent;


    public SellerOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        product_image = itemView.findViewById(R.id.product_image);
        orderTitle = itemView.findViewById(R.id.orderTitle);
        orderStatus = itemView.findViewById(R.id.orderStatus);
        parent = itemView.findViewById(R.id.parent);
    }
}
