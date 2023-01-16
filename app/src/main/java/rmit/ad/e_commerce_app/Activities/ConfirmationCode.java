package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import rmit.ad.e_commerce_app.R;

public class ConfirmationCode extends AppCompatActivity {
    TextView CodeField;
    Button ConfirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);

        CodeField = findViewById(R.id.codeField);
        ConfirmButton = findViewById(R.id.ConfirmButton);
    }
}