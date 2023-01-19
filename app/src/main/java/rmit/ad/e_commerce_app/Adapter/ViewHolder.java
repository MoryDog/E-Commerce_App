package rmit.ad.e_commerce_app.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import rmit.ad.e_commerce_app.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView product_image;
    TextView product_name;
    TextView product_price;
    CardView parent;
    Button DeleteBtn;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        product_image = itemView.findViewById(R.id.product_image);
        product_name = itemView.findViewById(R.id.orderTitle);
        product_price = itemView.findViewById(R.id.orderStatus);
        parent = itemView.findViewById(R.id.parent);
        DeleteBtn = itemView.findViewById(R.id.DeleteBtn);
    }
}
