package com.example.evapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VoteFragment2 extends Fragment {
    Button b1,b2;
    DatabaseReference reference;
    String Candidate_ID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vote,container,false);
        b1 = view.findViewById(R.id.Button21);

        reference = FirebaseDatabase.getInstance().getReference();
        Candidate_ID = getArguments().getString("adhaar_ID");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Intent intent = new Intent(getContext(),Voting.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        return view;

    }

    private void Vote( String candidate_id, String constituency) {
        //sd=1;


        //intent.putExtra("C",candidate_id);
        //intent.putExtra("Co",constituency);

    }
}
