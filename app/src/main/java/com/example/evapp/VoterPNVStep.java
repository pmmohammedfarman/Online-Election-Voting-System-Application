package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;

public class VoterPNVStep extends AppCompatActivity {

    EditText phn,OTP;
    Button EnterNum,Verify;
    int OTPP, SentOTP;
    String adhaarId,userEnteredVoterOTP;
    String AdhaarIDFromDB,VoterPhoneNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_pnvstep);
        phn = (EditText) findViewById(R.id.CanPhn);
        OTP = (EditText) findViewById(R.id.CanOTP);
        EnterNum = (Button) findViewById(R.id.CanGetOTP);
        Verify = (Button) findViewById(R.id.CanVerifyOTP);

        Intent i = getIntent();
        adhaarId = i.getStringExtra("adhaar_ID");
        EnterNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEnteredVoterPhnNum = phn.getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("adhaar_ID").equalTo(adhaarId);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            phn.setError(null);
                            String VPhoneFromDB = dataSnapshot.child(adhaarId).child("phone").getValue(String.class);
                            if(VPhoneFromDB.equals(userEnteredVoterPhnNum))
                            {

                                SentOTP = sendOtp();
                                AdhaarIDFromDB = dataSnapshot.child(adhaarId).child("adhaar_ID").getValue(String.class);
                                VoterPhoneNum = dataSnapshot.child(adhaarId).child("phone").getValue(String.class);


                            }
                            else
                            {
                                phn.setError("Please Enter the correct phone Number");
                                phn.requestFocus();

                            }
                        }
                        else
                        {
                            phn.setError("Please Enter a valid phone Number");
                            phn.requestFocus();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEnteredVoterOTP = OTP.getText().toString().trim();
                OTPP = Integer.parseInt(userEnteredVoterOTP);
                if(SentOTP == OTPP)
                {
                    Intent intent = new Intent(VoterPNVStep.this,VoterPP0ortal.class);
                    intent.putExtra("adhaar_ID",AdhaarIDFromDB);
                    Intent intent3 = new Intent(getApplicationContext(),VoterPP0ortal.class);

                    startActivity(intent3);
                    Toast.makeText(getApplicationContext(),"Message Accepted",Toast.LENGTH_LONG).show();
                   // startActivity(intent);

                }
                else
                {
                    OTP.setError("Enter the correct OTP");
                    OTP.requestFocus();

                }

            }
        });

    }


    public int  sendOtp()
    {
        int OTP1 = GenerateOTP();
        SendingOTP(OTP1);
        return OTP1;

    }
    public int GenerateOTP()
    {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);

        return num;

    }

    public void SendingOTP(int a) {
        String b = Integer.toString(a);
        String number =phn.getText().toString();

        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,b,null,null);
            Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Some fields is Empty",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed()
    {

    }



}

