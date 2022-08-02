package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CandidatePortal extends AppCompatActivity {
    String Candidate_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_portal);

        Intent i = getIntent();
        Candidate_ID=i.getStringExtra("Candidate_ID");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


    }

    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId())
                    {


                        case R.id.nav_mail:
                            selectedFragment = new MailFragment();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new SearchFragment2();
                            break;

                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;


                        case R.id.nav_vote:
                            selectedFragment = new VoteFragment();
                            break;



                    }
                    Bundle args = new Bundle();
                    args.putString("Candidate_ID",Candidate_ID);
                    selectedFragment.setArguments(args);


                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;

                }
            };
}