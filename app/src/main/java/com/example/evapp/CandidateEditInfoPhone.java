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

public class CandidateEditInfoPhone extends AppCompatActivity {
    EditText Phone;
    Button Save,Update;
    String Candidate_ID;
    DatabaseReference mRef;
    String userEnteredCandidateName;
    int a=5;
    String DOBB = "Phone";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_edit_info_phone);

        Intent intent=getIntent();
        Candidate_ID=intent.getStringExtra("Candidate_ID");

        Phone = (EditText) findViewById(R.id.Name);
        Save = (Button)  findViewById(R.id.Save);
        Update= (Button) findViewById(R.id.Update);


        mRef = FirebaseDatabase.getInstance().getReference("Candidate");




        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEnteredCandidateName = Phone.getText().toString().trim();




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
                            mRef.child(Candidate_ID).child("Phone").setValue(userEnteredCandidateName);

                        }
                        Intent intent5 = new Intent(CandidateEditInfoPhone.this,MessageFromCandidateToAdminForVerification.class);

                        intent5.putExtra("Edit",DOBB);
                        intent5.putExtra("Edit",DOBB);
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