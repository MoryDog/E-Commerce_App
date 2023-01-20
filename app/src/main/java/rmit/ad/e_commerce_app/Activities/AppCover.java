package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import rmit.ad.e_commerce_app.R;


public class AppCover extends AppCompatActivity {

    ConstraintLayout welcomeView;
    TextView ContinueText, appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_cover);

        welcomeView = findViewById(R.id.welcomeView);
        ContinueText = findViewById(R.id.ContinueText);
        appName = findViewById(R.id.appName);
        welcomeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppCover.this, Login.class);
                startActivity(intent);
            }
        });
        // Set gradient for app name
        TextPaint paint = appName.getPaint();
        float width = paint.measureText("Shoply");

        Shader textShader = new LinearGradient(0, 0, width, appName.getTextSize(),
                new int[]{
                        Color.parseColor("#e90986"),
                        Color.parseColor("#a43794"),
                        Color.parseColor("#e24a5f"),
                        Color.parseColor("#f89d36"),
                        Color.parseColor("#f9e039"),
                }, null, Shader.TileMode.CLAMP);
        appName.getPaint().setShader(textShader);


        // Set animation for continue text
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(765); //You can manage the blinking time with this parameter
        anim.setStartOffset(25);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        ContinueText.startAnimation(anim);
    }
}