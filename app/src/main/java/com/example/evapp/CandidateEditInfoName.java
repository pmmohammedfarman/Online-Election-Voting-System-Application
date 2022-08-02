package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CandidateEditInfoName extends AppCompatActivity {
    EditText Name;
    Button Save,Update;
    String Candidate_ID;
    DatabaseReference mRef;
    String userEnteredCandidateName;
    int a=3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_edit_info_name);

        Intent intent=getIntent();
        Candidate_ID=intent.getStringExtra("Candidate_ID");

        Name = (EditText) findViewById(R.id.Name);
        Save = (Button)  findViewById(R.id.Save);
        Update= (Button) findViewById(R.id.Update);


        mRef = FirebaseDatabase.getInstance().getReference("Candidate");

        String DOBB = "Name";





        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEnteredCandidateName = Name.getText().toString().trim();
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
                            mRef.child(Candidate_ID).child("Name").setValue(userEnteredCandidateName);

                        }
                        Intent intent5 = new Intent(CandidateEditInfoName.this,MessageFromCandidateToAdminForVerification.class);

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