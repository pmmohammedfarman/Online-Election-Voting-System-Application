package com.example.evapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ViewMessageAdmin extends AppCompatActivity {
    String Mail_ID,Candidate_ID,me;
    TextView sender,subject,message;

    Button button,b1,b2;
    String s;

    DatabaseReference reference,mRef,mRef2,mRef3,mRef4;
    String value;
    int value1,mail_ID,new_mailID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message2);
        Intent intent = getIntent();
        Mail_ID = intent.getStringExtra("Country");
        Candidate_ID = intent.getStringExtra("Candidate_ID");


        Toast.makeText(this, ""+Mail_ID+"", Toast.LENGTH_SHORT).show();
        sender=findViewById(R.id.textView14);
        subject = findViewById(R.id.textView13);
        message = findViewById(R.id.textView15);
        button = findViewById(R.id.REPLY);
        b1 = findViewById(R.id.Approve);
        b2 = findViewById(R.id.Reject);


        reference = FirebaseDatabase.getInstance().getReference("Chats");
        mRef2 = FirebaseDatabase.getInstance().getReference("Admin");
        mRef =  FirebaseDatabase.getInstance().getReference("Candidate");
        mRef3 =  FirebaseDatabase.getInstance().getReference();
        mRef4 = FirebaseDatabase.getInstance().getReference("MailID");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String senderr = snapshot.child(Mail_ID).child("sender").getValue(String.class);
                String subjects = snapshot.child(Mail_ID).child("subject").getValue(String.class);
                String messages = snapshot.child(Mail_ID).child("message").getValue(String.class);
                s=senderr;
                sender.setText(senderr);
                subject.setText(subjects);
                message.setText(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(ViewMessageAdmin.this, MessageFromCandidateToAdminForVerification2.class);
                intent3.putExtra("To",s);
                intent3.putExtra("From",Candidate_ID);
                startActivity(intent3);

            }
        });
    }




}