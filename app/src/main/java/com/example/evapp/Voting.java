package com.example.evapp;



import static java.lang.System.exit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evapp.databinding.MainActivityBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Voting extends AppCompatActivity {

    Button Vote;
    DatabaseReference Reference;
    String Constituency;
    ArrayList<String> youNameArray = new ArrayList<>();
    ListView listView;
    String Candidate_ID;
    DatabaseReference mRef3;
    DatabaseReference mRef;
    String l;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        Vote = findViewById(R.id.vote);


        listView = findViewById(R.id.nojno);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Voting.this, android.R.layout.simple_list_item_1,youNameArray);
        listView.setAdapter(arrayAdapter);
        Reference = FirebaseDatabase.getInstance().getReference();
       // if (Constituency!=null)
    //    {
           // mRef = Reference.child("Constituency").child(Constituency);
        mRef = Reference.child("Constituency");
     //   }

        DatabaseReference mRef2 = Reference.child("Users");

        mRef3 = Reference.child("Votes");





            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshots : snapshot.child("Mangalore North").getChildren()){
                        String data = snapshots.getKey();
                        youNameArray.add(data);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        RadioGroup rb = (RadioGroup) findViewById(R.id.radio_group);
       ;//rb.getCheckedRadioButtonId();
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int n =rb.getCheckedRadioButtonId();
                System.out.println(n);
                switch (n) {
                  //  rb.getCheckedRadioButtonId();
                    case 2131230899:
                        int i=0;

                        String a = listView.getItemAtPosition(i).toString();
                        l =a;
                        break;
                    case 2131230900:
                        int j=1;
                        String b = listView.getItemAtPosition(j).toString();
                        l=b;
                        break;
                    case 2131230901:
                        int k=2;
                        String c = listView.getItemAtPosition(k).toString();
                        l=c;
                        break;
                }
            }
        });
        Vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    vc(l);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Toast.makeText(Voting.this, "xsdsx"+i+"", Toast.LENGTH_SHORT).show();


                                            }

                                        }


        );

    }


    private void vc(String a) {

            mRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String VB = snapshot.child("Mangalore North").child(a).getValue(String.class);
                        int no = Integer.parseInt(VB);
                        int yes = no + 1;
                        String yas = Integer.toString(yes);
                        mRef3.child("Mangalore North").child(a).setValue(yas);
                        finish();
                        exit(0);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        @Override
        public void onBackPressed ()
    {
        Intent  i = new Intent(Voting.this, MainActivity.class);
        startActivity(i);
    }



}