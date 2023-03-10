package rmit.ad.e_commerce_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import rmit.ad.e_commerce_app.CategoryActivities.LaptopCategory;
import rmit.ad.e_commerce_app.CategoryActivities.PhoneCategory;
import rmit.ad.e_commerce_app.CategoryActivities.ShirtCategory;
import rmit.ad.e_commerce_app.CategoryActivities.ShoesCategory;
import rmit.ad.e_commerce_app.CategoryActivities.WatchCategory;

import rmit.ad.e_commerce_app.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> m_name = new ArrayList<>();
    private ArrayList<String> m_imageUrl = new ArrayList<>();
    private Context m_context;

    
    public RecyclerViewAdapter(ArrayList<String> m_name, ArrayList<String> m_imageUrl, Context m_context) {
        this.m_name = m_name;
        this.m_imageUrl = m_imageUrl;
        this.m_context = m_context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(m_context)
                .asBitmap()
                .load(m_imageUrl.get(position))
                .into(holder.ImageView);
        holder.category_name.setText(m_name.get(position));
        holder.ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on an image: " + m_name.get(position));
                String category_name = m_name.get(position);

                Intent intent = new Intent(m_context, ShoesCategory.class);
                intent.putExtra("category", category_name);
                m_context.startActivity(intent);

                /*
                else if (category_name.equals("T-Shirts")) {
                    Intent intent1 = new Intent(m_context, ShoesCategory.class);
                    m_context.startActivity(intent1);
                } else if (category_name.equals("Phones")) {
                    Intent intent1 = new Intent(m_context, ShoesCategory.class);
                    m_context.startActivity(intent1);
                } else if (category_name.equals("Watches")) {
                    Intent intent1 = new Intent(m_context, ShoesCategory.class);
                    m_context.startActivity(intent1);
                } else if (category_name.equals("Laptops")) {
                    Intent intent1 = new Intent(m_context, ShoesCategory.class);
                    m_context.startActivity(intent1);
                }
                 */
                Toast.makeText(m_context, m_name.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return m_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        android.widget.ImageView ImageView;
        TextView category_name;

        public ViewHolder (View itemView){
            super(itemView);
            ImageView = itemView.findViewById(R.id.category_img);
            category_name = itemView.findViewById(R.id.category_name);
        }

    }
}
