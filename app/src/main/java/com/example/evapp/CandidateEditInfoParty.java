package com.example.evapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CandidateEditInfoParty extends AppCompatActivity {
    EditText Party;
    Button Save,Update;
    String Candidate_ID;
    DatabaseReference mRef;
    String userEnteredCandidateName;
    int a=4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_edit_info_party);

        Intent intent=getIntent();
        Candidate_ID=intent.getStringExtra("Candidate_ID");

        Party = (EditText) findViewById(R.id.Name);
        Save = (Button)  findViewById(R.id.Save);
        Update= (Button) findViewById(R.id.Update);


        mRef = FirebaseDatabase.getInstance().getReference("Candidate");
        String DOBB = "Party";




        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEnteredCandidateName = Party.getText().toString().trim();




            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query checkUser = mRef.orderByChild("Candidate_ID").equalTo(Candidate_ID);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists())
                        {
                            mRef.child(Candidate_ID).child("Party").setValue(userEnteredCandidateName);

                        }
                        Intent intent5 = new Intent(CandidateEditInfoParty.this,MessageFromCandidateToAdminForVerification.class);

                        intent5.putExtra("Edit",DOBB);
                        intent5.putExtra("Candidate_ID",Candidate_ID);
                        startActivity(intent5);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });




    }
}