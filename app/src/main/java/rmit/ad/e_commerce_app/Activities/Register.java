package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import rmit.ad.e_commerce_app.R;

public class Register extends AppCompatActivity {
    ImageView back_button2;
    Button registerButton;
    View login_view;
    AutoCompleteTextView GenderTextView, RoleTextView;
    ArrayAdapter<String> adapterItemsGenders, adapterItemsRoles;

    // arrays for dropdown menus
    String[] genders = {"male","female"};
    String[] roles = {"buyer","seller"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        back_button2 = findViewById(R.id.back_button2);
        back_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LogIn.class);
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
                Intent intent = new Intent(Register.this, ConfirmationCode.class);
                // Send notification to user about new account creation
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Register.this,"Notifications");
                builder.setContentTitle("New Account Created");
                builder.setContentText("Please check your email to receive confirmation code");
                builder.setSmallIcon(R.drawable.ic_baseline_lock);
                builder.setAutoCancel(true);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Register.this);
                managerCompat.notify(10, builder.build());
                startActivity(intent);
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
}