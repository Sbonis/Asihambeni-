package com.example.asihambeni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private NotificationFragment notificationFragment;
    private ProfileFragment profileFragment;
    private SettingsFragment settingsFragment;

    private BottomNavigationView BottomNav;
    private FrameLayout FrameNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        BottomNav = (BottomNavigationView) findViewById(R.id.bottom_nav);
        FrameNav = (FrameLayout) findViewById(R.id.frame_layout);


        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();
        settingsFragment = new SettingsFragment();

        loadFragment(homeFragment);

        BottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        BottomNav.setItemBackgroundResource(R.color.colorGrey);
                        loadFragment(homeFragment);
                        return true;

                    case R.id.nav_notification:
                        BottomNav.setItemBackgroundResource(R.color.colorGrey);
                        loadFragment(notificationFragment);
                        return true;

                    case R.id.nav_profile:
                        BottomNav.setItemBackgroundResource(R.color.colorGrey);
                        loadFragment(profileFragment);
                        return true;

                    case R.id.nav_settings:
                        BottomNav.setItemBackgroundResource(R.color.colorGrey);
                        loadFragment(settingsFragment);
                        return true;

                }
                return false;

            }
        });
    }

    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}
