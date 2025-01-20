package com.example.stayheheapps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private EditText emailTextView, passwordTextView;
    private Button btn;
    private ProgressBar progressBar;
    private FrameLayout progressOverlay;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        btn = findViewById(R.id.signInButton);
        progressOverlay = findViewById(R.id.progressOverlay);
        progressBar = findViewById(R.id.progressBar);


        //set onclick button signIn
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });
    }

   private void loginUserAccount(){
       progressOverlay.setVisibility(View.VISIBLE);

        String email,password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

       if (TextUtils.isEmpty(email)) {
           Toast.makeText(getApplicationContext(), "Masukkan Email", Toast.LENGTH_LONG).show();
           return;
       }
       if (TextUtils.isEmpty(password)){
           Toast.makeText(getApplicationContext(),"Password harus diisi",Toast.LENGTH_LONG).show();
           return;
       }
       //login
       mAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(
                       new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(
                                   @NonNull Task<AuthResult> task)
                           {
                               if (task.isSuccessful()) {
                                   Toast.makeText(getApplicationContext(),
                                                   "Login successful!!",
                                                   Toast.LENGTH_LONG)
                                           .show();

                                   // hide the progress bar
                                   progressBar.setVisibility(View.GONE);

                                   // if sign-in is successful
                                   // intent to home activity
                                   Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                   startActivity(intent);
                               }

                               else {

                                   // sign-in failed
                                   Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG)
                                           .show();

                                   // hide the progress bar
                                   progressBar.setVisibility(View.GONE);
                               }
                           }
                       });
   }
    //create acc
    public void registration(View viev){
        startActivity(new Intent(this, RegistrationActivity.class));
    }

}