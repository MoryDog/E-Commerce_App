package rmit.ad.e_commerce_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Adapter.CartProductAdapter;
import rmit.ad.e_commerce_app.Fragments.HomeFragment;
import rmit.ad.e_commerce_app.Fragments.ShoppingCartFragment;
import rmit.ad.e_commerce_app.Fragments.FavoriteFragment;
import rmit.ad.e_commerce_app.Fragments.OrderFragment;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    FavoriteFragment favoriteFragment;
    OrderFragment thirdFragment;
    ShoppingCartFragment shoppingCartFragment;
    Button openAddProductButton;
    GlobalUserAccess globalUserAccess;
    String jsonString = "";
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalUserAccess = ((GlobalUserAccess) getApplicationContext());

        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shoppefy");

        homeFragment = new HomeFragment(globalUserAccess.getAccessToken());
        favoriteFragment = new FavoriteFragment(globalUserAccess.getAccessToken());
        thirdFragment = new OrderFragment(globalUserAccess.getAccessToken(), globalUserAccess.getUserRole());
        shoppingCartFragment = new ShoppingCartFragment(globalUserAccess.getAccessToken());

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView leftNavigationView = findViewById(R.id.nav_view);
        View v = getLayoutInflater().inflate(R.layout.nav_header,null);
        TextView username = v.findViewById(R.id.UserRegisterName);
        TextView userEmail = v.findViewById(R.id.UserEmail);
        new dogetUser().execute();
        Toast.makeText(globalUserAccess, username.getText(), Toast.LENGTH_SHORT).show();
        Toast.makeText(globalUserAccess, userEmail.getText(), Toast.LENGTH_SHORT).show();
        ActionBarDrawerToggle Toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.shopping_cart);
        if (Utils.obtainInstance().getCartProducts().isEmpty()) {
            badgeDrawable.setVisible(false);
        } else {
            badgeDrawable.setVisible(true);
            badgeDrawable.setNumber(Utils.obtainInstance().getCartProduct());
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.favorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, favoriteFragment).commit();
                        return true;
                    case R.id.notifications:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).commit();
                        return true;
                    case R.id.shopping_cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, shoppingCartFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        Utils.obtainInstance().getCartProducts().clear();
    }



    private class dogetUser extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String url = "http://54.151.194.4:3000/getuserinfor?accessToken=" + globalUserAccess.getAccessToken();
            jsonString = HttpHandler.getRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                System.out.println(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                System.out.println(jsonObject.get("email"));
                System.out.println(jsonObject.get("username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}