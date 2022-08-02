package com.example.evapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MailFragment3 extends Fragment {

    ListView myList, my,List;
    //String userid,subject;
    ArrayList<String> Array0 = new ArrayList<>();
    ArrayList<String> Array = new ArrayList<>();
    ArrayList<String> Array2 = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter0, arrayAdapter, arrayAdapter2;
    String Candidate_ID;
    String mailID;
    int i = 0;
    String nooo;
    int yes;


    DatabaseReference reference, reference2, reference3, RedLineRouteReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail, container, false);
        Candidate_ID = getArguments().getString("Candidate_ID");

        RedLineRouteReference = FirebaseDatabase.getInstance().getReference().child("UserMessages").child("Received");

        reference = FirebaseDatabase.getInstance().getReference().child("Chats");
        reference2 = FirebaseDatabase.getInstance().getReference().child("MailID");
        my = view.findViewById(R.id.M0);
        myList = view.findViewById(R.id.M1);
        List = view.findViewById(R.id.M2);


        //Array.add("sjsjs000");
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, Array);
        myList.setAdapter(arrayAdapter);
        arrayAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, Array2);
        arrayAdapter0 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, Array0);
        my.setAdapter(arrayAdapter0);
        List.setAdapter(arrayAdapter2);
        nsd();


        my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i;
                String ll = my.getItemAtPosition(i).toString();
                Intent intent1 = new Intent(getContext(), ViewMessageAdmin.class);
                intent1.putExtra("Country",my.getItemAtPosition(i).toString());
                startActivity(intent1);
            }
        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i;
                String ll = my.getItemAtPosition(i).toString();

                //String Ma = M[i];

                Intent intent1 = new Intent(getContext(), ViewMessageCanadidate.class);
                intent1.putExtra("Country",my.getItemAtPosition(i).toString());
                intent1.putExtra("Candidate_ID",Candidate_ID);
                // Object a = myList.getItemAtPosition(i);
                //nss(a);
                startActivity(intent1);
            }
        });
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i;
                String ll = my.getItemAtPosition(i).toString();

                //String Ma = M[i];

                Intent intent1 = new Intent(getContext(), ViewMessageCanadidate.class);
                intent1.putExtra("Country",my.getItemAtPosition(i).toString());
                startActivity(intent1);
            }
        });


        return view;
    }

    private void nsd() {
        RedLineRouteReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.child(Candidate_ID).getChildren()) {
                    String data = dsp.getKey();
                    reference.child(data).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String message = snapshot.child("message").getValue(String.class);
                            String subject = snapshot.child("subject").getValue(String.class);
                            String sender = snapshot.child("sender").getValue(String.class);
                            String dg = snapshot.child("MailID").getValue(String.class);
                            if (message!=null && subject!=null & sender!=null & dg!=null)
                            {
                                Array0.add(dg);
                                System.out.println(subject);
                                arrayAdapter0.notifyDataSetChanged();
                                Array.add(sender);
                                System.out.println(subject);
                                arrayAdapter.notifyDataSetChanged();
                                Array2.add(subject);
                                arrayAdapter2.notifyDataSetChanged();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

