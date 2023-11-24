package com.example.nguyendinhtrung_pk02294_asm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.fragments.DiemFragment;
import com.example.nguyendinhtrung_pk02294_asm.fragments.LichHocFragment;
import com.example.nguyendinhtrung_pk02294_asm.fragments.TienIchFragment;
import com.example.nguyendinhtrung_pk02294_asm.fragments.TinTucFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        selectedFragment = new TinTucFragment();
                        break;
                    case R.id.navigation_schedule:
                        selectedFragment = new LichHocFragment();
                        break;
                    case R.id.navigation_grades:
                        selectedFragment = new DiemFragment();
                        break;
                    case R.id.navigation_utility:
                        selectedFragment = new TienIchFragment();
                        break;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }

                return true;
            }
        });

        // Nếu savedInstanceState là null (ứng dụng được khởi động lần đầu), hãy chọn TinTucFragment làm Fragment mặc định.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TinTucFragment()).commit();
        }
    }
}