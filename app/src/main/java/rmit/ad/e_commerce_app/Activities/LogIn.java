package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import rmit.ad.e_commerce_app.R;

public class LogIn extends AppCompatActivity {
    ImageView back_button;
    TextView signup_button, forgotPassword;
    Button login_button2;
    EditText username_field, password_field;
    View login_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Set transparent status bar
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });

        forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(login_view, "Try to remember it next time then :)", Snackbar.LENGTH_SHORT).show();
            }
        });

        login_button2 = findViewById(R.id.login_button2);
        username_field = findViewById(R.id.registerUsername);
        password_field = findViewById(R.id.password_field);
        login_view = findViewById(android.R.id.content);

        login_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username_field.getText().toString().trim().equals("") || password_field.getText().toString().equals("")) {
                    Snackbar.make(login_view, "Username and Password cannot be empty!", Snackbar.LENGTH_SHORT).show();
                } else if (username_field.getText().toString().trim().length() > 0 || password_field.getText().toString().length() > 0) {
                    Intent intent = new Intent(LogIn.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(login_view, "An error has occurred", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }
}