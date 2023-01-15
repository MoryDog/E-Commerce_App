package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import rmit.ad.e_commerce_app.R;

public class AddProduct extends AppCompatActivity {
    EditText TitleField, DescriptionField, StockQuantityField, PriceField;
    Button AddProductButton;
    ImageButton Image1, Image2, Image3, Image4;
    Spinner CategoryPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        // Initialize views
        TitleField = findViewById(R.id.TitleField);
        DescriptionField = findViewById(R.id.DescriptionField);
        StockQuantityField = findViewById(R.id.StockQuantityField);
        PriceField = findViewById(R.id.PriceField);
        Image1 = findViewById(R.id.Image1);
        Image2 = findViewById(R.id.Image2);
        Image3 = findViewById(R.id.Image3);
        Image4 = findViewById(R.id.Image4);
        AddProductButton = findViewById(R.id.AddProductButton);
        CategoryPicker = findViewById(R.id.CategoryPicker);

        // Adapter for category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategoryPicker.setAdapter(adapter);
    }

}