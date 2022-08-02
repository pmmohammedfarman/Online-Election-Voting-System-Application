package com.example.evapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VoterPP0ortal extends AppCompatActivity {
    String adhaar_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_portal);

        Intent i = getIntent();
        adhaar_ID=i.getStringExtra("adhaar_ID");



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
                            selectedFragment = new MailFragment2();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new SearchFragment22();
                            break;

                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragmentProfileFragment2();
                            break;


                        case R.id.nav_vote:
                            selectedFragment = new VoteFragment2();
                            break;



                    }
                    Bundle args = new Bundle();
                    args.putString("adhaar_ID",adhaar_ID);
                    selectedFragment.setArguments(args);


                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;

                }
            };
}