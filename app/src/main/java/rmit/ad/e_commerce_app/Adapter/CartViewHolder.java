package rmit.ad.e_commerce_app.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import rmit.ad.e_commerce_app.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView product_image;
    TextView product_name;
    TextView product_price;
    ImageButton addButton;
    ImageButton minusButton;
    TextView quantity;
    CardView parent;
    Button checkOutButton;
    TextView totalPrice;
    EditText shippingAddress;
    ImageButton deleteButton;


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        product_image = itemView.findViewById(R.id.product_image);
        product_name = itemView.findViewById(R.id.orderItemTitle);
        product_price = itemView.findViewById(R.id.orderItemPrice);
        parent = itemView.findViewById(R.id.parent);
        addButton = itemView.findViewById(R.id.addButton);
        minusButton = itemView.findViewById(R.id.minusButton);
        quantity = itemView.findViewById(R.id.quantity);
        checkOutButton = itemView.findViewById(R.id.checkOutButton);
        totalPrice = itemView.findViewById(R.id.totalPrice);
        shippingAddress = itemView.findViewById(R.id.shippingAddress);
        deleteButton = itemView.findViewById(R.id.deleteButton);

    }
}
