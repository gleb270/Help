package com.papalam.help;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView title;
    AllTestsFragment allTestsFragment = new AllTestsFragment();
    ChatFragment chatFragment = new ChatFragment();
    ContactsFragment contactsFragment = new ContactsFragment();
    PainFragment painFragment = new PainFragment();
    CalendarFragment calendarFragment = new CalendarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(MainActivity.this);
        navView.setSelectedItemId(R.id.navigation_tests);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        painFragment.applyImage();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_tests:
                setFragment("Тесты", allTestsFragment);
                break;
            case R.id.navigation_diary:
                setFragment("Дневник", calendarFragment);
                break;
            case R.id.navigation_chat:
                setFragment("Чат", chatFragment);
                break;
            case R.id.navigation_contacts:
                setFragment("Контакты", contactsFragment);
                break;

        }

        return true;
    }

    public void setFragment(String textTitle, Fragment fragment) {
        title.setText(textTitle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.place_holder, fragment).commit();

    }
}
