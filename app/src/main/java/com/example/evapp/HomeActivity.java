package com.example.evapp;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    int counter = 0;
    int a=0;

    @Override
    public void onBackPressed()
    {
        counter++;
        if(counter == 1)
        {
            alert("Do you want to exit the application??");


        }
        else
        {
            if (counter == 2)
            {
                super.onBackPressed();
                finish();
            }
            /*else
            {

            }*/
        }

    }
    public void alert(String message)
    {
        AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).setTitle("Alert").setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        onBackPressed();


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        counter=0;




                    }
                }).create();
        dialog.show();
        /*if (dialog.isShowing()==false)
        {
            counter=0;
            onBackPressed();

        }*/








    }
    /*public void onPause() {

        super.onPause();
    }*/
    public void calc(int a)
    {
        if (a==0)
        {
            counter=-1;
            onBackPressed();


        }

    }



    public void Admin(View v)
    {
        Intent i = new Intent(this,Admin.class);
        startActivity(i);
        //finish();
    }
    public void Candidate(View v)
    {
        Intent i1 = new Intent(this,Candidate.class);
        startActivity(i1);
        finish();
    }
    public void Voter(View v)
    {
        Intent i2 = new Intent(this,Voter.class);
        startActivity(i2);
        //finish();
    }
    public void Resultzs(View v)
    {
        Intent i3 = new Intent(this,Resultzs.class);
        startActivity(i3);
        //finish();
    }

}
