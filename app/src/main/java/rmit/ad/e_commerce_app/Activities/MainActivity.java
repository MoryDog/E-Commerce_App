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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rmit.ad.e_commerce_app.Fragments.HomeFragment;
import rmit.ad.e_commerce_app.Fragments.ShoppingCartFragment;
import rmit.ad.e_commerce_app.Fragments.FavoriteFragment;
import rmit.ad.e_commerce_app.Fragments.OrderFragment;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.R;
import rmit.ad.e_commerce_app.Utils;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    static BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    FavoriteFragment favoriteFragment;
    OrderFragment thirdFragment;
    ShoppingCartFragment shoppingCartFragment;
    GlobalUserAccess globalUserAccess;
    String jsonString = "";
    TextView username;
    TextView userEmail;

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
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_sign_out:
                        new doLogOut().execute();
                        break;
                    case R.id.nav_chat:
                        break;
                    case R.id.nav_profile:
                    case R.id.nav_question:
                    case R.id.nav_about:
                }
                return true;
            }
        });
        View headerView = navigationView.getHeaderView(0);
        username = (TextView) headerView.findViewById(R.id.UserRegisterName);
        userEmail = (TextView) headerView.findViewById(R.id.UserEmail);
        new dogetUser().execute();
        ActionBarDrawerToggle Toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        SetBadge();
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

    public static void SetBadge(){
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.shopping_cart);
        badgeDrawable.setNumber(Utils.obtainInstance().getCartProducts().size());
        if (badgeDrawable.getNumber() == 0){
            badgeDrawable.setVisible(false);
        } else {
            badgeDrawable.setVisible(true);
        }
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                JSONArray jsonArray = new JSONArray(jsonString);
                for(int i = 0; i< jsonArray.length(); i++){
                    JSONObject product = jsonArray.getJSONObject(i);
                    username.setText(product.get("username").toString());
                    userEmail.setText(product.get("email").toString());
                }
                Toast.makeText(globalUserAccess, "Logged in as: " + username.getText(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private class doLogOut extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String url = "http://54.151.194.4:3000/signout";
            jsonString = HttpHandler.postSignOut(url, globalUserAccess.getAccessToken());
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            globalUserAccess.setAccessToken("");
            globalUserAccess.setRefreshToken("");
            globalUserAccess.setIdToken("");
            globalUserAccess.setUserRole("");
            Toast.makeText(globalUserAccess, jsonString,  Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}