package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class CandidateEditProfile extends AppCompatActivity {
    Button button1,button2,button3,button4,button5,button6,button7;
    String Candidate_ID;
    TextView Text1,Text2,Text3,Text4,Text5,Text6;
    String name,party,phone,dob,constituency;
    int a;
    FirebaseStorage storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_edit_profile);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.REPLY);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        Text1 = (TextView) findViewById(R.id.List);
        Text2 = (TextView) findViewById(R.id.List1);
        Text3 = (TextView) findViewById(R.id.List2);
        Text4 = (TextView) findViewById(R.id.List3);
        Text5 = (TextView) findViewById(R.id.List4);
        Text6 = (TextView) findViewById(R.id.List5);




        //Intent intent = getIntent();
        //Candidate_ID = intent.getStringExtra("Candidate_ID");

        Bundle gameData = getIntent().getExtras();
        if (gameData!=null)
        {
            Candidate_ID = gameData.getString("Candidate_ID");
        }
        else if (gameData == null)
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        if (Candidate_ID!=null)
        {
            Toast.makeText(CandidateEditProfile.this, "Can", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(CandidateEditProfile.this, "C", Toast.LENGTH_SHORT).show();
        }

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Candidate");
        Query checkUser = mRef.orderByChild("Candidate_ID").equalTo(Candidate_ID);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    name = snapshot.child(Candidate_ID).child("Name").getValue(String.class);
                    dob= snapshot.child(Candidate_ID).child("DOB").getValue(String.class);
                    constituency = snapshot.child(Candidate_ID).child("Constituency").getValue(String.class);
                    party = snapshot.child(Candidate_ID).child("Party").getValue(String.class);
                    phone = snapshot.child(Candidate_ID).child("phone").getValue(String.class);

                    Text1.setText(name);
                    Text2.setText(dob);
                    Text3.setText(constituency);
                    Text4.setText(party);
                    Text5.setText(phone);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CandidateEditProfile.this,CandidateChangeImage.class);
                a=1;
                intent.putExtra("Candidate_ID",Candidate_ID);
                intent.putExtra("a",a);

                startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(CandidateEditProfile.this,CandidateEditInfoName.class);
                intent2.putExtra("Candidate_ID",Candidate_ID);
                startActivity(intent2);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(CandidateEditProfile.this,CandidateEditInfoDateOfBirth.class);
                intent3.putExtra("Candidate_ID",Candidate_ID);
                startActivity(intent3);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(CandidateEditProfile.this,CandidateEditInfoConstituency.class);
                intent4.putExtra("Candidate_ID",Candidate_ID);
                startActivity(intent4);

            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(CandidateEditProfile.this,CandidateEditInfoParty.class);
                intent5.putExtra("Candidate_ID",Candidate_ID);
                startActivity(intent5);

            }
        });


        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(CandidateEditProfile.this,CandidateEditInfoPhone.class);
                intent6.putExtra("Candidate_ID",Candidate_ID);
                startActivity(intent6);

            }
        });

    }

    /*@Override
    public void onBackPressed()
    {




    }*/




}



