package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import rmit.ad.e_commerce_app.R;

public class Verification extends AppCompatActivity {
    TextView CodeField;
    Button ConfirmButton;
    View snackbar_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);

        CodeField = findViewById(R.id.codeField);
        ConfirmButton = findViewById(R.id.ConfirmButton);
        snackbar_view = findViewById(android.R.id.content);

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Verification.this, LogIn.class );
                Snackbar.make(snackbar_view, "Account Verified! Enter your credentials to login)", Snackbar.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}