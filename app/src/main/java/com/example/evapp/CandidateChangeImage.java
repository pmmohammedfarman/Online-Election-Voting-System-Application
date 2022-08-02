package com.example.evapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.evapp.databinding.ActivityCandidateChangeImageBinding;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CandidateChangeImage extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;
    ActivityCandidateChangeImageBinding binding;
    ActivityResultLauncher<String> launcher;
    Button ChangeImage,SelectImage;
    ImageView img;
    Uri filename;


    String CanID;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_change_image);
        img=(ImageView) findViewById(R.id.imageView3);
        //SelectImage=(Button) findViewById(R.id.button);
        ChangeImage=(Button) findViewById(R.id.button1);

        Intent i = getIntent();
        CanID =i.getStringExtra("Candidate_ID");
        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        binding = ActivityCandidateChangeImageBinding.inflate(getLayoutInflater());

        ChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadimage();
                //Toast.makeText(CandidateChangeImage.this,"Uploaded",Toast.LENGTH_LONG).show();

            }
        });
        Button pick = findViewById(R.id.button);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

               img.setImageURI(result);
               filename = result;


            }
        });
    }

    private void uploadimage() {
        progressDialog = new ProgressDialog(CandidateChangeImage.this);
        progressDialog.setTitle("Uploading File...........");
        progressDialog.show();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String filenames = CanID;


        StorageReference ref = storageReference
                .child(
                        "images/").child("Candidates/"
                                +filenames+".jpg");



        //storageReference = FirebaseStorage.getInstance().getReference();
        ref.putFile(filename)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        img.setImageURI(null);
                        //if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(CandidateChangeImage.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();





                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //if(progressDialog.isShowing())
                progressDialog.dismiss();

                Toast.makeText(CandidateChangeImage.this, "Failed to upload image", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(
                new OnProgressListener<UploadTask.TaskSnapshot>() {

                    // Progress Listener for loading
                    // percentage on the dialog box
                    @Override
                    public void onProgress(
                            UploadTask.TaskSnapshot taskSnapshot)
                    {
                        double progress
                                = (100.0
                                * taskSnapshot.getBytesTransferred()
                                / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage(
                                "Uploaded "
                                        + (int)progress + "%");
                    }
                });;

    }

}