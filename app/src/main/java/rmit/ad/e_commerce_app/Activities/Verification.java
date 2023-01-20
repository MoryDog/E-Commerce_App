package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.R;

public class Verification extends AppCompatActivity {
    EditText CodeField;
    Button ConfirmButton;
    View snackbar_view;

    String username;
    String confirm_code;
    String jsonString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        CodeField = findViewById(R.id.codeField);
        ConfirmButton = findViewById(R.id.ConfirmButton);
        snackbar_view = findViewById(android.R.id.content);

        Intent intent = getIntent();
        username = intent.getExtras().get("username").toString();

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CodeField.getText().toString().trim().isEmpty()) {
                    Snackbar.make(snackbar_view, "Enter your confirmation code to verify your account!)", Snackbar.LENGTH_SHORT).show();
                } else {
                    confirm_code = CodeField.getText().toString();
                    new doVerification().execute();
                }
            }
        });
    }

    private class doVerification extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.postVerification("http://54.151.194.4:3000/confirm", username, confirm_code);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            System.out.println("-----------------------------");
            System.out.println(jsonString);
            if (jsonString.equals("Confirm successfully")) {
                Intent intent = new Intent(Verification.this, Login.class);
                Snackbar.make(snackbar_view, "Account Verified! Enter your credentials to login)", Snackbar.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else {
                Toast.makeText(Verification.this, jsonString, Toast.LENGTH_SHORT).show();
                return;
            }

        }

    }
}