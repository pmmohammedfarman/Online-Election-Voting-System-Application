package com.example.evapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    SearchView searchView;
    ListView list;
    RecyclerView recyclerView;
    ArrayList<Dual> myArrayList = new ArrayList<>();
    ArrayAdapter myArrayAdapter;
    DatabaseReference mRef;
    String Candidate_ID;
    int Candidate_I=1110;
    String s;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        searchView = view.findViewById(R.id.searchview);
        recyclerView= view.findViewById(R.id.listt);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        mRef= FirebaseDatabase.getInstance().getReference("Candidate");
        //Candidate_ID = getArguments().getString("Candidate_ID");
        //myArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,myArrayList);
        //list.setAdapter(myArrayAdapter);





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);


                //myArrayAdapter.getFilter().filter(newText);


                return true;
            }

        });



        return view;

    }

    private void search(String str) {
        ArrayList<Dual> myList=new ArrayList<>();
        for (Dual object : myArrayList)
        {

                myList.add(object);


        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }

    @Override
    public void onStart() {

        super.onStart();
        if (mRef!=null)
        {
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists())
                    {
                        myArrayList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren())
                        {
                            myArrayList.add(ds.getValue(Dual.class));

                        }
                        AdapterClass adapterClass = new AdapterClass(myArrayList);
                        recyclerView.setAdapter(adapterClass);


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {


                }
            });

        }

    }
}
