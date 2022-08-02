package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;




public class Voter extends AppCompatActivity {
    EditText VotID,votPass;
    Button VoterLoginButton;
    DatabaseReference refer;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter);
        VotID=(EditText)findViewById(R.id.VoterId);
        votPass=(EditText) findViewById(R.id.Voterpass);
        VoterLoginButton=(Button) findViewById(R.id.LoginButton);


        VoterLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginvoter();




            }

        });







    }
    public void loginvoter()
    {
        String userEnteredVoterId = VotID.getText().toString().trim();
        String userEnteredVoterPass = votPass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("adhaar_ID").equalTo(userEnteredVoterId);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    VotID.setError(null);
                    String VPasswordFromDB = dataSnapshot.child(userEnteredVoterId).child("password").getValue(String.class);

                    if(VPasswordFromDB.equals(userEnteredVoterPass))
                    {
                        VotID.setError(null);

                        String AdhaarIDFromDB = dataSnapshot.child(userEnteredVoterId).child("adhaar_ID").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(),VoterPNVStep.class);
                        intent.putExtra("adhaar_ID",AdhaarIDFromDB);
                        Intent intent3 = new Intent(getApplicationContext(),VoterPP0ortal.class);

                        //startActivity(intent3);
                        startActivity(intent);

                    }
                    else
                    {
                        votPass.setError("Wrong Password");
                        votPass.requestFocus();

                    };

                }
                else
                {
                    VotID.setError("No User Exists");
                    VotID.requestFocus();
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Voter.this,"Password is wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent l = new Intent(Voter.this,HomeActivity.class);
        startActivity(l);
        finish();
    }


}