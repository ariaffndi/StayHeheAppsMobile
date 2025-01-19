package com.example.stayheheapps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    String root_fragment = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        loadFragment(0, new HomeFragment());

        ImageView nav1 = findViewById(R.id.image_nav1);
        ImageView nav2 = findViewById(R.id.image_nav2);
        ImageView nav3 = findViewById(R.id.image_nav3);
        ImageView nav4 = findViewById(R.id.image_nav4);
        ImageView nav5 = findViewById(R.id.image_nav5);

        nav1.setOnClickListener(v -> loadFragment(1, new ProfileFragment()));
        nav2.setOnClickListener(v -> loadFragment(1, new OfferFragment()));
        nav3.setOnClickListener(v -> loadFragment(1, new HomeFragment()));
        nav4.setOnClickListener(v -> loadFragment(1, new HistoryFragment()));
        nav5.setOnClickListener(v -> loadFragment(1, new SavedFragment()));



    }

    public void loadFragment(int flag, Fragment fragment)  {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (flag == 0) {
            fragmentTransaction.add(R.id.frameLayout, fragment);
            fragmentManager.popBackStack(root_fragment, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.addToBackStack(root_fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    public void goToDetail(View view) {
        startActivity(new Intent(this, DetailActivity.class));
    }
}