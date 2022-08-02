package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class VP extends AppCompatActivity {
    Button Logout,AdminPortal;
    ImageView image;

    ListView name,dob,Constituency,Party,phone;

    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayList<String> myArrayList2 = new ArrayList<>();
    ArrayList<String> myArrayList3 = new ArrayList<>();
    ArrayList<String> myArrayList4 = new ArrayList<>();
    ArrayList<String> myArrayList5 = new ArrayList<>();
    DatabaseReference mRef;
    String Candidate_ID;

    ArrayAdapter myArrayAdapter, myArrayAdapter2,myArrayAdapter3,myArrayAdapter4,myArrayAdapter5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        Intent intent = getIntent();
        String Candidate_ID = intent.getStringExtra("Country");
        image = findViewById(R.id.imageView);

        //ChangeImage=(Button) findViewById(R.id.ChangeImage);
        name = findViewById(R.id.List) ;
        dob = findViewById(R.id.List1) ;
        Constituency = findViewById(R.id.List2);
        Party = findViewById(R.id.List3);
        phone = findViewById(R.id.List4);
        //setHasOptionsMenu(true);


        myArrayAdapter = new ArrayAdapter<String>(VP.this, android.R.layout.simple_list_item_1,myArrayList);
        myArrayAdapter2 = new ArrayAdapter<String>(VP.this, android.R.layout.simple_list_item_1,myArrayList2);
        myArrayAdapter3 = new ArrayAdapter<String>(VP.this, android.R.layout.simple_list_item_1,myArrayList3);
        myArrayAdapter4 = new ArrayAdapter<String>(VP.this, android.R.layout.simple_list_item_1,myArrayList4);
        myArrayAdapter5 = new ArrayAdapter<String>(VP.this, android.R.layout.simple_list_item_1,myArrayList5);
        name.setAdapter(myArrayAdapter);
        dob.setAdapter(myArrayAdapter2);
        Constituency.setAdapter(myArrayAdapter3);
        Party.setAdapter(myArrayAdapter4);
        phone.setAdapter(myArrayAdapter5);

        mRef= FirebaseDatabase.getInstance().getReference("Candidate");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        ImageView imageView = findViewById(R.id.imageView3);







        Query checkUser = mRef.orderByChild("Candidate_ID").equalTo(Candidate_ID);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child(Candidate_ID).child("Name").getValue(String.class);
                    myArrayList.add(name);
                    myArrayAdapter.notifyDataSetChanged();
                    String dob = snapshot.child(Candidate_ID).child("DOB").getValue(String.class);
                    myArrayList2.add(dob + "");
                    myArrayAdapter2.notifyDataSetChanged();
                    String constituency = snapshot.child(Candidate_ID).child("Constituency").getValue(String.class);
                    myArrayList3.add(constituency);
                    myArrayAdapter3.notifyDataSetChanged();
                    String party = snapshot.child(Candidate_ID).child("Party").getValue(String.class);
                    myArrayList4.add(party);
                    myArrayAdapter4.notifyDataSetChanged();
                    String phone = snapshot.child(Candidate_ID).child("phone").getValue(String.class);
                    myArrayList5.add(phone);
                    myArrayAdapter5.notifyDataSetChanged();




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}