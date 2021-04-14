package com.example.mothercareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class ListOfUsers extends AppCompatActivity {

  private TextView list;


  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);
        list = findViewById(R.id.textViewId);
        DatabaseReference databaseRef=FirebaseDatabase.getInstance().getReference("users");

        databaseRef.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
            String data = snapshot.child("name").getValue(String.class);
            list.setText(data);
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
        });

  }

}
