package rmit.ad.e_commerce_app.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rmit.ad.e_commerce_app.Adapter.OfflineProductImageAdapter;
import rmit.ad.e_commerce_app.Adapter.ProductImagesAdapter;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.HttpClasses.UploadApis;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.seller.SellerActivity;

public class AddProduct extends AppCompatActivity {
    private DrawerLayout drawerLayout;
   AutoCompleteTextView autoCompleteTextView;
   ArrayAdapter<String> adapterItems;
   Button addProductButton;
   ViewPager addProductImagesViewPager;
   TabLayout viewPagerIndicator2;
   FloatingActionButton addImageButton;
   ImageView ProductImage;
   Bitmap bitmap;
   String fileURL;
   AutoCompleteTextView category;
   TextInputEditText title;
   TextInputEditText price;
   TextInputEditText size;
   TextInputEditText color;
   TextInputEditText brand;
   TextInputEditText quantity;
   TextInputEditText description;

    GlobalUserAccess globalUserAccess;

    String local = "http://192.168.10.3:3000/";
    String server = "http://54.151.194.4:3000/";

    List<Uri> uris = new ArrayList<>();
    List<Bitmap> bitmaps = new ArrayList<>();

   String[] categories = {"Shoes","Shirts","Watches","Phones"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        category = findViewById(R.id.categoryText);
        title = findViewById(R.id.titleText);
        price = findViewById(R.id.priceText);
        size = findViewById(R.id.sizeText);
        color = findViewById(R.id.colorText);
        brand = findViewById(R.id.brandText);
        quantity = findViewById(R.id.quantityText);
        description = findViewById(R.id.desciptionText);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add New Product");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Adapter for dropdown menu
        autoCompleteTextView = findViewById(R.id.categoryText);
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
                //Intent intent = new Intent(AddProduct.this, HomeFragment.class);
                //startActivity(intent);
                String des = description.getText().toString();

                new uploadImageAsysnc().execute();


            }
        });

        addImageButton = findViewById(R.id.addImageButton);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thieu code lay link URL tu database
                // new LoadImage().execute(fileUrl) (tren mang);
                Toast.makeText(AddProduct.this, "Missing add image from url function", Toast.LENGTH_SHORT).show();
                pickImages();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.back_button) {
            Intent intent = new Intent(AddProduct.this, SellerActivity.class);
            startActivity(intent);
        }
        Toast.makeText(AddProduct.this,"Error occured", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void pickImages(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            //ImageView imageView = findViewById(R.id.imageView);

            ClipData clipData = data.getClipData();

            if(clipData != null){
                for( int i =0; i < clipData.getItemCount(); i++){
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    uris.add(imageUri);
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(imageUri);

                        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

                        bitmaps.add(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }


            } else{
                Uri imageUri = data.getData();
                uris.add(imageUri);
                try{
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                    bitmaps.add(bitmap);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }

                /*
                try {
                    uploadImages(this,uris,bitmaps);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                 */
            }

            // Add images for new product
            addProductImagesViewPager = findViewById(R.id.addProduct_images_viewpager);
            viewPagerIndicator2 = findViewById(R.id.viewPager_indicator2);


        List<Bitmap> productImages = new ArrayList<>();
        for(int i = 0; i< bitmaps.size();i++){
            productImages.add(bitmaps.get(i));
        }


            OfflineProductImageAdapter productImagesAdapter = new OfflineProductImageAdapter(productImages, this);
            addProductImagesViewPager.setAdapter(productImagesAdapter);

            viewPagerIndicator2.setupWithViewPager(addProductImagesViewPager, true);

            ;
        }
    }


    private void uploadImages  (Context context, List<Uri> uris, List<Bitmap> bitmaps, String category, String title, String price, String colors, String sizes, String description, String brand, String quantity) throws IOException {

        List<File> files = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();

        for (int i  =0; i < bitmaps.size(); i ++) {

            String imageName = uris.get(i).toString();
            int lastSlash = imageName.lastIndexOf('%') + 1;
            imageName = imageName.substring(lastSlash,imageName.length());

            File f = new File(context.getCacheDir(), imageName);
            f.createNewFile();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmaps.get(i).compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            files.add(f);
            imageNames.add(imageName);
        }

        globalUserAccess = ((GlobalUserAccess)getApplicationContext());

        //String testJson = "eyJraWQiOiJWT3JcL1RvY1BKU3hpQWJqUXdDTU5YYU9sdnBtcUkxRkZwb0JMYVlEejBYQT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMzQzNjljZi00NzZkLTQ5YzYtOTI1Yy0wYTU5MWVjNmNiZWYiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfRnM3SWNIMWtyIiwiY2xpZW50X2lkIjoiN3VnaDNxbWhlZTlhNGtqbDdvODNrMmg4ZDEiLCJvcmlnaW5fanRpIjoiNTc1ODgxMzktZDM5NS00Mzg2LWJkYTMtZTA2NmFhNjIxNjMzIiwiZXZlbnRfaWQiOiJkNDI1NTAyMi0zZTQ2LTQzNzctOGEwYy0xY2Q1NjEzOTRlNWMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjc0MDIyMzAwLCJleHAiOjE2NzQwMjU5MDAsImlhdCI6MTY3NDAyMjMwMCwianRpIjoiMzZiNGRkMjMtZjM1Yy00MTQxLTg4MDMtZjkzM2YwYWZlZTE0IiwidXNlcm5hbWUiOiJtaW5odnU1In0.giwRaDJh6qOE-PSAkGuF4f3PveDXVrBr3Z9UZnnf2APXVfRlqJF7huIYjunccomTLDAQG_3mj7HiAP6rTp5ueZrRZ2xSx2LKITS-h8pFxyqBgyWSx9kNXn1qhsryIy4aOz1bq8HHBlndBLp_1LVAjFHnRoJG10NjiqvOwIselSsK2f9TkJEf9C4XA0Buh7pa8RpH9-qS80xpUYr7me6SqOKWuJ51Ts2XqZmQO_qRs2pUePjvlbDM_jSo0LjJNiOHi0wmIVR1W2PyWMli6AD3k0Xscj5EVdi7-Vw1ywBdmk2uc9C3UVKbIWqjejrbWlMtdykhbKcz5J7sSlTCRZRLoA";
        //RequestBody test = RequestBody.create( MediaType.parse("text/plain"), testJson);

        RequestBody accessToken = RequestBody.create(MediaType.parse("text/plain"), globalUserAccess.getAccessToken());
        RequestBody categoryBody = RequestBody.create(MediaType.parse("text/plain"), category);
        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody colorsBody = RequestBody.create(MediaType.parse("text/plain"), colors);
        RequestBody sizesBody = RequestBody.create(MediaType.parse("text/plain"), sizes);
        RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody brandBody = RequestBody.create(MediaType.parse("text/plain"), brand);
        RequestBody quantityBody = RequestBody.create(MediaType.parse("text/plain"), quantity);
        MultipartBody.Part[] multipart = new MultipartBody.Part[files.size()];
        for(int i = 0; i < files.size(); i ++){
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), files.get(i));
            multipart[i] = MultipartBody.Part.createFormData("images", files.get(i).getPath(), surveyBody);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UploadApis uploadApis = retrofit.create(UploadApis.class);

        uploadApis.uploadMultiImage(multipart, accessToken, categoryBody, titleBody, priceBody, colorsBody, sizesBody,descriptionBody, brandBody, quantityBody).enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                System.out.println(response);
                System.out.println("It's here");
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    private class uploadImageAsysnc extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                uploadImages(AddProduct.this,uris,bitmaps, category.getText().toString(), title.getText().toString(), price.getText().toString(), color.getText().toString(), size.getText().toString(), description.getText().toString(), brand.getText().toString() ,quantity.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

        }

    }

}