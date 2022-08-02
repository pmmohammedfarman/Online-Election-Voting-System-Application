package com.example.evapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class MessageFromCandidateToAdminForVerification2 extends AppCompatActivity {
    private EditText To,Subject,Message;
    ImageButton Send,Attach;
    String Candidate_ID;
    String a;

    int mail_ID,value2,new_mailID;
    String value;
    int value1;


    DatabaseReference mRef,mRef2,mRef3,mRef4;

    String message,recipientList,MessageSubject,me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_from_candidate_to_admin_for_verification);
        To = findViewById(R.id.ToID);
        Subject = findViewById(R.id.SubjectContent);
        Message = findViewById(R.id.message);
        Send = (ImageButton) findViewById(R.id.Send);
        Attach = (ImageButton) findViewById(R.id.attach);

        mRef2 = FirebaseDatabase.getInstance().getReference("Admin");
        mRef =  FirebaseDatabase.getInstance().getReference("Candidate");
        mRef3 =  FirebaseDatabase.getInstance().getReference();
        mRef4 = FirebaseDatabase.getInstance().getReference("MailID");
        Intent intent3 = getIntent();
        Candidate_ID = intent3.getStringExtra("To");
        a = intent3.getStringExtra("From");
        String no = intent3.getStringExtra("Message");




        // String mes = " Your Request has been approved";





        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lol();
                message = Message.getText().toString();
                recipientList = To.getText().toString();
                MessageSubject = Subject.getText().toString();
                if(!message.equals(""))
                {
                    sendmessage(me,Candidate_ID,recipientList,MessageSubject,message);
                }
                else
                {
                    Toast.makeText(MessageFromCandidateToAdminForVerification2.this, "Please Enter a message to send", Toast.LENGTH_SHORT).show();
                }
                Message.setText("");
                Subject.setText("");
                To.setText("");
                MessageFromCandidateToAdminForVerification2.super.finish();
            }
        });
        Attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attachFile();
            }
        });
    }

    private void lol() {
        mRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value = snapshot.child("MailID").getValue(String.class);
                if (!TextUtils.isEmpty(value) && TextUtils.isDigitsOnly(value)) {
                    value1 = Integer.parseInt(value);
                } else {
                    value1 = 0;
                }
                mail_ID = value1;
                new_mailID = mail_ID+1;
                me = Integer.toString(new_mailID);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void attachFile() {


    }
    private void sendmessage(String maills,String candidate_ID, String recipientList, String messageSubject, String message) {
        Query checkUser = mRef.orderByChild("Candidate_ID").equalTo(recipientList);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    HashMap<String , Object> hashMap = new HashMap<>();
                    hashMap.put("MailID",maills);
                    hashMap.put("sender",candidate_ID);
                    hashMap.put("receiver",recipientList);
                    hashMap.put("subject",messageSubject);
                    hashMap.put("message",message);
                    mRef3.child("Chats").child(me).setValue(hashMap);
                    mRef3.child("Chats").child(me).child("MailID").setValue(maills);
                    mRef3.child("Chats").child(me).child("sender").setValue(candidate_ID);
                    mRef3.child("Chats").child(me).child("receiver").setValue(recipientList);
                    mRef3.child("Chats").child(me).child("subject").setValue(messageSubject);
                    mRef3.child("Chats").child(me).child("message").setValue(message);
                    mRef4.child("MailID").setValue(maills);
                    mRef3.child("UserMessages").child("Sent").child(candidate_ID).child(me).setValue(me);
                    mRef3.child("UserMessages").child("Received").child(recipientList).child(me).setValue(me);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}