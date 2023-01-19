package rmit.ad.e_commerce_app.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


import rmit.ad.e_commerce_app.Fragments.HomeFragment;
import rmit.ad.e_commerce_app.Fragments.ShoppingCartFragment;
import rmit.ad.e_commerce_app.Fragments.FavoriteFragment;
import rmit.ad.e_commerce_app.Fragments.NotificationFragment;
import rmit.ad.e_commerce_app.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();
    NotificationFragment thirdFragment = new NotificationFragment();
    ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
    Button openAddProductButton;
    GlobalUserAccess globalUserAccess;

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


        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle Toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}