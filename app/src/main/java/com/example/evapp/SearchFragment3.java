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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment3 extends Fragment {
    SearchView mySearchView;
    ListView myList;
    ArrayList<String> list,B;
    ArrayAdapter<String> adapter;
    ArrayList<Long> Candidate_ID = new ArrayList<>();
    ArrayList<String> youNameArray = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search2,container,false);


        //DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Candidate");

        DatabaseReference RedLineRouteReference = FirebaseDatabase.getInstance().getReference().child("Candidate");
        RedLineRouteReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = snapshot.getKey();
                    youNameArray.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mySearchView =view.findViewById(R.id.searchview);
        myList=view.findViewById(R.id.id);
        adapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,youNameArray);
        myList.setAdapter(adapter);


        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),VP.class);
                intent.putExtra("Country",myList.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        return view;


    }


}
