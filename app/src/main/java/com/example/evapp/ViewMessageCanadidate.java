package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewMessageCanadidate extends AppCompatActivity {
    String Mail_ID,Candidate_ID;
    TextView sender,subject,message;

    Button button;
    String s;

    DatabaseReference reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message3);
        Intent intent = getIntent();
        Mail_ID = intent.getStringExtra("Country");
        Candidate_ID = intent.getStringExtra("Candidate_ID");


        Toast.makeText(this, ""+Mail_ID+"", Toast.LENGTH_SHORT).show();
        sender=findViewById(R.id.textView14);
        subject = findViewById(R.id.textView13);
        message = findViewById(R.id.textView15);
        button = findViewById(R.id.REPLY);

        reference = FirebaseDatabase.getInstance().getReference("Chats");
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
                Intent intent3 = new Intent(ViewMessageCanadidate.this, MessageFromCandidateToAdminForVerification2.class);
                intent3.putExtra("To",s);
                intent3.putExtra("From",Candidate_ID);
                startActivity(intent3);

            }
        });





    }
}