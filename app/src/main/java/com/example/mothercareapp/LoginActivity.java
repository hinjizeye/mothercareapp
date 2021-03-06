package com.example.mothercareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

  private EditText inputEmail, inputPassword;
  private FirebaseAuth auth;
  private Button btnLogin;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.loginEmailAddress);
        inputPassword= findViewById(R.id.loginPassword);
        btnLogin= findViewById(R.id.login);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String email = inputEmail.getText().toString();
            final String password = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
              Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
              return;
            }

            if (TextUtils.isEmpty(password)) {
              Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
              return;
            }

            auth.signInWithEmailAndPassword(email, password)
              .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (!task.isSuccessful()) {
                    // there was an error
                    Toast.makeText(LoginActivity.this, "Login failed." + task.getException(),
                      Toast.LENGTH_SHORT).show();
                  } else {
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                  }
                }
              });


          }
        });




    }

}
