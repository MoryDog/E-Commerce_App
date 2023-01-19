package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import rmit.ad.e_commerce_app.R;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Review your Order");
    }
}