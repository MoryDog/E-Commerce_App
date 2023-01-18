package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rmit.ad.e_commerce_app.Fragments.HomeFragment;
import rmit.ad.e_commerce_app.R;

public class AddProduct extends AppCompatActivity {
    private DrawerLayout drawerLayout;
   AutoCompleteTextView autoCompleteTextView;
   ArrayAdapter<String> adapterItems;
   Button addProductButton;
   FloatingActionButton addImageButton;
   ImageView ProductImage;
   Bitmap bitmap;
   String fileURL;

   String[] categories = {"Shoes","Shirts","Watches","Phones"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Adapter for dropdown menu
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(this, R.layout.category_spinner_dropdown, categories);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String category = adapterView.getItemAtPosition(i).toString();
            }
        });

        addProductButton = findViewById(R.id.addProductButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduct.this, HomeFragment.class);
                startActivity(intent);
            }
        });

        addImageButton = findViewById(R.id.addImageButton);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thieu code lay link URL tu database
                // new LoadImage().execute(fileUrl) (tren mang);
                Toast.makeText(AddProduct.this, "Missing add image from url function", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.back_button) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // Function load image tu tren mang (not sure if work :)))
    // https://stackoverflow.com/questions/34124222/user-inputs-a-url-for-an-image-and-it-displays-in-a-imageview

//    public Bitmap getBitmapFromURL(String src)
//    {
//        try
//        {
//            java.net.URL url = new java.net.URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//
//            InputStream input = connection.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(input);
//            return bitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    private class LoadImage extends AsyncTask<String, String, Bitmap>
//    {
//        @Override
//        protected Bitmap doInBackground(String... args) {
//            try {
//                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected void onPreExecute()
//        {
//            super.onPreExecute();
//            Toast.makeText(AddProduct.this, "Loading Image ...", Toast.LENGTH_SHORT).show();
//        }
//
//        protected void onPostExecute(Bitmap image) {
//
//            if(image != null){
//                ImageView img = new ImageView(AddProduct.this);
//                img.setImageBitmap(bitmap);
//            }else{
//                Toast.makeText(AddProduct.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    }


}