package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.R;

public class Register extends AppCompatActivity {
    ImageView back_button2;
    Button registerButton;
    View register_view;
    AutoCompleteTextView GenderTextView, RoleTextView;
    ArrayAdapter<String> adapterItemsGenders, adapterItemsRoles;
    EditText registerEmail, registerUsername, registerPassword, registerDOB;
    TextInputLayout registerGender,registerRole;

    // arrays for dropdown menus
    String[] genders = {"male","female"};
    String[] roles = {"buyer","seller"};

    GlobalUserAccess globalUserAccess;

    String email;
    String user_name;
    String user_pasword;
    String gender;
    String birthdate;
    String user_role;
    String jsonString ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        globalUserAccess = ((GlobalUserAccess) getApplicationContext());

        registerEmail = findViewById(R.id.registerEmail);
        registerUsername = findViewById(R.id.registerUsername);
        registerPassword = findViewById(R.id.registerPassword);
        registerDOB = findViewById(R.id.registerDOB);
        registerGender = findViewById(R.id.registerGender);
        registerRole = findViewById(R.id.registerRole);
        register_view = findViewById(android.R.id.content);


        back_button2 = findViewById(R.id.back_button2);
        back_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registerEmail.getText().toString().trim().isEmpty() || registerUsername.getText().toString().trim().isEmpty()
                        || registerPassword.getText().toString().isEmpty() || registerDOB.getText().toString().trim().isEmpty()
                || registerGender.getEditText().getText().toString().trim().isEmpty() || registerRole.getEditText().getText().toString().trim().isEmpty()) {
                    Snackbar.make(register_view, "Please enter information for all required fields", Snackbar.LENGTH_SHORT).show();

                } else  {
                    email = registerEmail.getText().toString();
                    user_name = registerUsername.getText().toString();
                    user_pasword = registerPassword.getText().toString();
                    birthdate = registerDOB.getText().toString();
                    gender = registerGender.getEditText().getText().toString();
                    user_role = registerRole.getEditText().getText().toString();
                    new doRegister().execute();

                }

            }
        });

        // Adapter for dropdown menu
        GenderTextView = findViewById(R.id.GenderTextView);
        RoleTextView = findViewById(R.id.RoleTextView);

        adapterItemsGenders = new ArrayAdapter<String>(this, R.layout.category_spinner_dropdown, genders);
        adapterItemsRoles = new ArrayAdapter<String>(this, R.layout.category_spinner_dropdown, roles);

        GenderTextView.setAdapter(adapterItemsGenders);
        RoleTextView.setAdapter(adapterItemsRoles);

        GenderTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String gender = adapterView.getItemAtPosition(i).toString();
            }
        });

        RoleTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String role = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private class doRegister extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.postRegister("http://54.151.194.4:3000/register", email, user_name, user_pasword, gender, birthdate, user_role);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            System.out.println("-----------------------------");
            System.out.println(jsonString);
            if (jsonString.equals("Register successfully username: "+user_name)) {
                Intent intent = new Intent(Register.this, Verification.class);
                // Send notification to user about new account creation
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Register.this,"Notifications");
                builder.setContentTitle("New Account Created");
                builder.setContentText("Please check your email to receive confirmation code");
                builder.setSmallIcon(R.drawable.ic_baseline_lock);
                builder.setAutoCancel(true);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Register.this);
                managerCompat.notify(10, builder.build());
                intent.putExtra("username", user_name);
                startActivity(intent);
            }
            else {
                Toast.makeText(Register.this, jsonString, Toast.LENGTH_SHORT).show();
                return;
            }

        }

    }
}