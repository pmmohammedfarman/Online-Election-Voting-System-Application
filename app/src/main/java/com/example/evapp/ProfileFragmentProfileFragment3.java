package com.example.evapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ProfileFragmentProfileFragment3 extends Fragment {

    Button Logout,AdminPortal;
    ImageView image;

    ListView name,dob,Constituency,Party,phone;

    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayList<String> myArrayList2 = new ArrayList<>();
    ArrayList<String> myArrayList3 = new ArrayList<>();
    ArrayList<String> myArrayList4 = new ArrayList<>();
    ArrayList<String> myArrayList5 = new ArrayList<>();
    DatabaseReference mRef;
    String Candidate_ID;

    ArrayAdapter myArrayAdapter, myArrayAdapter2,myArrayAdapter3,myArrayAdapter4,myArrayAdapter5;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        Candidate_ID = getArguments().getString("Candidate_ID");
        image = view.findViewById(R.id.imageView);

        //ChangeImage=(Button) findViewById(R.id.ChangeImage);
        name = view.findViewById(R.id.List) ;
        dob = view.findViewById(R.id.List1) ;
        Constituency = view.findViewById(R.id.List2);
        Party = view.findViewById(R.id.List3);
        phone = view.findViewById(R.id.List4);
        setHasOptionsMenu(true);


        myArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,myArrayList);
        myArrayAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,myArrayList2);
        myArrayAdapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,myArrayList3);
        myArrayAdapter4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,myArrayList4);
        myArrayAdapter5 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,myArrayList5);
        name.setAdapter(myArrayAdapter);
        dob.setAdapter(myArrayAdapter2);
        Constituency.setAdapter(myArrayAdapter3);
        Party.setAdapter(myArrayAdapter4);
        phone.setAdapter(myArrayAdapter5);

        mRef= FirebaseDatabase.getInstance().getReference("Candidate");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        ImageView imageView = view.findViewById(R.id.imageView3);







        Query checkUser = mRef.orderByChild("Admin_ID").equalTo(Candidate_ID);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String name = snapshot.child(Candidate_ID).child("Name").getValue(String.class);
                        myArrayList.add(name);
                        myArrayAdapter.notifyDataSetChanged();
                        String dob = snapshot.child(Candidate_ID).child("DOB").getValue(String.class);
                        myArrayList2.add(dob + "");
                        myArrayAdapter2.notifyDataSetChanged();
                        String constituency = snapshot.child(Candidate_ID).child("Constituency").getValue(String.class);
                        myArrayList3.add(constituency);
                        myArrayAdapter3.notifyDataSetChanged();
                        String party = snapshot.child(Candidate_ID).child("Party").getValue(String.class);
                        myArrayList4.add(party);
                        myArrayAdapter4.notifyDataSetChanged();
                        String phone = snapshot.child(Candidate_ID).child("phone").getValue(String.class);
                        myArrayList5.add(phone);
                        myArrayAdapter5.notifyDataSetChanged();




                    }
            }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





    return view;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)

    {
        inflater.inflate(R.menu.mymenu,menu);



    }


    public  boolean onOptionsItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.LogOut:
                Toast.makeText(getContext(), "LogOut", Toast.LENGTH_SHORT).show();

                AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("Alert").setMessage("Do you want to sign out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(getContext(),Candidate.class);
                                startActivity(intent);
                                ((Activity) getActivity()).finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.EditProfile:
                Bundle gameData = new Bundle();
                gameData.putString("Candidate_ID",Candidate_ID);
                Intent intent = new Intent(getContext(),CandidateEditProfile.class);
                intent.putExtras(gameData);
                startActivity(intent);
                break;
        }

    return true;
    }
}
