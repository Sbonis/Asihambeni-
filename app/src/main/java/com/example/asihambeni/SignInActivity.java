package com.example.asihambeni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity{
    private EditText inputEmailAddress, inputPassword;
    private Button btnSignIn;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        progressDialog = new ProgressDialog(this);
        inputEmailAddress = (EditText) findViewById(R.id.editEmail);
        inputPassword = (EditText) findViewById(R.id.editPassword);
        btnSignIn = (Button) findViewById(R.id.SignInButton);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() !=null)
        {
            Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {

        }

        auth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

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

           if (!email.contains("@")) {
               Toast.makeText(getApplicationContext(), "Wrong email format", Toast.LENGTH_SHORT).show();
               return;
           }

           progressDialog.setMessage("Signing in ...");
           progressDialog.show();


                //authenticate user
              auth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressDialog.dismiss();

                       if (task.isSuccessful())
                       {
                           Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
                           startActivity(intent);
                           finish();

                       }
                       else
                           {
                               Toast.makeText(SignInActivity.this, "Incorrect email or password.",
                                       Toast.LENGTH_LONG).show();
                           }
                   }
               });
          }
        });

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
