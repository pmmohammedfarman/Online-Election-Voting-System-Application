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

import java.lang.reflect.Member;

public class Admin extends AppCompatActivity {
    EditText AdminID,AdminPass;
    Button AdminLoginButton;

    Member member;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        AdminID=(EditText)findViewById(R.id.AdminID);
        AdminPass=(EditText) findViewById(R.id.AdminPass);
        AdminLoginButton=(Button) findViewById(R.id.AdminLoginButton);

        AdminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminlogin();


            }
        });

    }
    public void adminlogin()
    {
        String userEnteredAdminId = AdminID.getText().toString().trim();
        String userEnteredAdminPass = AdminPass.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin");
        Query checkUser = reference.orderByChild("Admin_ID").equalTo(userEnteredAdminId);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    AdminID.setError(null);
                    String APasswordFromDB = dataSnapshot.child(userEnteredAdminId).child("password").getValue(String.class);
                    if(APasswordFromDB.equals(userEnteredAdminPass))
                    {
                        AdminID.setError(null);
                        String AdminIDFromDB = dataSnapshot.child(userEnteredAdminId).child("Admin_ID").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(),AdminPNVStep.class);
                        intent.putExtra("Admin_ID",AdminIDFromDB);
                        startActivity(intent);
                    }
                    else
                    {
                        AdminPass.setError("Wrong Password");
                        AdminPass.requestFocus();
                    }
                }
                else
                {
                    AdminID.setError("No User Exists");
                    AdminID.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Admin.this,"Password is wrong", Toast.LENGTH_LONG).show();


            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent l = new Intent(Admin.this,HomeActivity.class);
        startActivity(l);
        finish();

    }
}