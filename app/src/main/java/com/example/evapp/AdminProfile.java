package com.example.evapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

public class AdminProfile extends AppCompatActivity {
    Button Logout,AdminPortal;
    ImageView image;

    ListView name,dob;

    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayList<String> myArrayList2 = new ArrayList<>();
    DatabaseReference mRef;
    String Admin_ID;
    int a=1;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);



        Intent i = getIntent();
        Admin_ID = i.getStringExtra("Admin_ID");
        a=Integer.parseInt(Admin_ID);
        image = (ImageView) findViewById(R.id.imageView);
        Logout=(Button) findViewById(R.id.LogOut);
        AdminPortal=(Button) findViewById(R.id.Portal);
        //ChangeImage=(Button) findViewById(R.id.ChangeImage);
        name=(ListView) findViewById(R.id.List) ;
        dob =(ListView) findViewById(R.id.List1) ;
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(AdminProfile.this, android.R.layout.simple_list_item_1,myArrayList);
        ArrayAdapter<String> myArrayAdapter2 = new ArrayAdapter<String>(AdminProfile.this, android.R.layout.simple_list_item_1,myArrayList2);
        name.setAdapter(myArrayAdapter);
        dob.setAdapter(myArrayAdapter2);

        mRef= FirebaseDatabase.getInstance().getReference("Admin");

        Query checkUser = mRef.orderByChild("Admin_ID").equalTo(Admin_ID);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child(Admin_ID).child("Name").getValue(String.class);
                    myArrayList.add(name);
                    myArrayAdapter.notifyDataSetChanged();
                    String dob = snapshot.child(Admin_ID).child("DOB").getValue(String.class);
                    myArrayList2.add(dob + "");
                    myArrayAdapter2.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                counttimer();

            }

            public void counttimer()
            {
                counter++;

                if(counter == 1)
                {
                    alerts("Do you want to sign Out?");
                }

                if (counter == 2)
                {
                    Intent intent1 = new Intent(AdminProfile.this,HomeActivity.class);


                    startActivity(intent1);
                    //finish();

                }
            }

            public void alerts(String message)
            {
                AlertDialog dialog = new AlertDialog.Builder(AdminProfile.this).setTitle("Alert").setMessage(message)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                counttimer();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                counter=0;

                            }
                        }).create();
                dialog.show();

            }
        });


        AdminPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),CandidatePortal.class);
                startActivity(intent2);


            }
        });





    }
    @Override
    public void onBackPressed()
    {

    }
}