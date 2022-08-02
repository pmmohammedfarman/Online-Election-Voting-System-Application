package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Resultzs extends AppCompatActivity {
    ListView l1,l2;
    ArrayList<String> A = new ArrayList<>();
    ArrayList<String> B = new ArrayList<>();
    ArrayAdapter<String> a,b;
    DatabaseReference mRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultzs);
        l1 = findViewById(R.id.g123);
        l2 = findViewById(R.id.g124);
        a= new ArrayAdapter<>(Resultzs.this, android.R.layout.simple_list_item_1,A);
        l1.setAdapter(a);
        b= new ArrayAdapter<>(Resultzs.this, android.R.layout.simple_list_item_1,B);
        l2.setAdapter(b);
        mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("Votes").child("Mangalore North").getChildren())
                {
                    String data = dataSnapshot.getKey();
                    A.add(data);
                    String vote = snapshot.child("Votes").child("Mangalore North").child(data).getValue(String.class);
                    B.add(vote);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}