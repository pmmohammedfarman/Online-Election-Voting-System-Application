package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Candidate extends AppCompatActivity {
    EditText CanID,CanPass;
    Button CanLogin;
    DatabaseReference ref;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);
        CanID=(EditText)findViewById(R.id.CanID);
        CanPass=(EditText) findViewById(R.id.CanPass);
        CanLogin=(Button) findViewById(R.id.CanLogin);


        CanLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                candidatelogin();

            }
        });
    }
    public void candidatelogin()
    {
        String userEnteredCandidateId = CanID.getText().toString().trim();
        String userEnteredCandidatePass = CanPass.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Candidate");
        Query checkUser = reference.orderByChild("Candidate_ID").equalTo(userEnteredCandidateId);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    CanID.setError(null);
                    String CPasswordFromDB = dataSnapshot.child(userEnteredCandidateId).child("password").getValue(String.class);
                    if(CPasswordFromDB.equals(userEnteredCandidatePass))
                    {
                        CanID.setError(null);

                        String CanIDFromDB = dataSnapshot.child(userEnteredCandidateId).child("Candidate_ID").getValue(String.class);
                        //Intent intent = new Intent(getApplicationContext(),CandidatePNVStep.class);
                        Intent intent = new Intent(getApplicationContext(),CandidatePortal.class);
                        intent.putExtra("Candidate_ID",CanIDFromDB);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        CanPass.setError("Wrong Password");
                        CanPass.requestFocus();

                    }
                }
                else
                {
                    CanID.setError("No User Exists");
                    CanID.requestFocus();

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Candidate.this,"Password is wrong", Toast.LENGTH_LONG).show();


            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent l = new Intent(Candidate.this,HomeActivity.class);
        startActivity(l);
        finish();

    }
}