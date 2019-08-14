package com.example.asihambeni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity{
    private EditText inputEmailAddress, inputPassword;
    private Button btnSignIn;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() !=null)
        {
            startActivity(new Intent(SignInActivity.this, MainMenuActivity.class));
        }

        setContentView(R.layout.activity_sign_in);

        inputEmailAddress = (EditText) findViewById(R.id.editEmail);
        inputPassword = (EditText) findViewById(R.id.editPassword);
        btnSignIn = (Button) findViewById(R.id.SignInButton);

        auth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v)
                                         {
                                             String email = inputEmailAddress.getText().toString();
                                             final String password = inputPassword.getText().toString();

                                             if (TextUtils.isEmpty(email)) {
                                                 Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }

                                             if (TextUtils.isEmpty(password)) {
                                                 Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }

                                             progressBar.setVisibility(View.VISIBLE);

                                             //authenticate user
                                             auth.signInWithEmailAndPassword(email, password)
                                                     .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                                         @Override
                                                         public void onComplete(@NonNull Task<AuthResult> task) {
                                                             // If sign in fails, display a message to the user. If sign in succeeds
                                                             // the auth state listener will be notified and logic to handle the
                                                             // signed in user can be handled in the listener.
                                                             progressBar.setVisibility(View.GONE);
                                                             if (!task.isSuccessful())
                                                             {
                                                                 // there was an error
                                                                 if (password.length() < 6)
                                                                 {
                                                                     inputPassword.setError(getString(R.string.err_password));
                                                                 } else {
                                                                     Toast.makeText(SignInActivity.this, getString(R.string.err_login), Toast.LENGTH_LONG).show();
                                                                 }
                                                             } else
                                                                 {
                                                                 Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
                                                                 startActivity(intent);
                                                                 finish();
                                                             }
                                                         }
                                                     });
                                         }
                                     }
        );

    }


    public void createAccount(View view)
    {
        Intent account = new Intent(this,SignUpActivity.class);
        startActivity(account);
    }

    public void forgotPassword(View view)
    {
        Intent forgotpass = new Intent(this, PasswordResetActivity.class);
        startActivity(forgotpass);
    }
}
