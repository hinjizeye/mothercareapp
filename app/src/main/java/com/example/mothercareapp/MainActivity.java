package com.example.mothercareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

  private EditText inputEmail, inputPassword, inputName, inputAge, numberChildren, inputLocation;
  private Button btnSignIn, btnSignUp;

  // Write a message to the database
  FirebaseDatabase database;
  DatabaseReference myRef;


  private ProgressBar progressBar;
  private FirebaseAuth auth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Get Firebase auth instance
    auth = FirebaseAuth.getInstance();

    inputEmail = (EditText) findViewById(R.id.EmailAddress);
    inputPassword = (EditText) findViewById(R.id.Password);
    inputName = (EditText) findViewById(R.id.Name);
    inputAge = (EditText) findViewById(R.id.Age);
    numberChildren = (EditText) findViewById(R.id.numOfChildren);
    inputLocation = (EditText) findViewById(R.id.Location);

    btnSignIn = (Button)findViewById(R.id.SignIN);
    btnSignUp = (Button)findViewById(R.id.SignUp);

    btnSignIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
      }
    });

    btnSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String name = inputName.getText().toString();
        String age = inputAge.getText().toString();
        String children = numberChildren.getText().toString();
        String location = inputLocation.getText().toString();

        if (TextUtils.isEmpty(email)) {
          Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
          return;
        }

        if (TextUtils.isEmpty(password)) {
          Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
          return;
        }

        if (password.length() < 8) {
          Toast.makeText(getApplicationContext(), "Password too short, enter minimum 8 characters!", Toast.LENGTH_SHORT).show();
          return;
        }

        //creating user
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            Toast.makeText(MainActivity.this, "Your Account has been created:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
            if (!task.isSuccessful()) {
              Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                Toast.LENGTH_SHORT).show();
            } else {
              startActivity(new Intent(MainActivity.this, LoginActivity.class));
              finish();
            }

          }
        });

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        Helper helper = new Helper(name, age, children,location, email, password);
        myRef.child(name).setValue(helper);




      }
    });



  }

}
