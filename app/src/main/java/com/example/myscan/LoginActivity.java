package com.example.myscan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myscan.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private Button LoginButton;
    private EditText InputLogin;
    private EditText InputPassword;
    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        mAuth = FirebaseAuth.getInstance();
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(InputLogin.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Введите логин!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(InputPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Введите пароль!", Toast.LENGTH_SHORT).show();
                } else {
                    loadingBar.setTitle("Авторизация");
                    loadingBar.setMessage("Выполняется вход...");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();
                    loginUser(InputLogin.getText().toString(), InputPassword.getText().toString());
                }
            }

        });


    }

    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this, "User not null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User null", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginUser(String login, String password) {


        mAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    DatabaseReference roleRef;
                    String userUid = mAuth.getCurrentUser().getUid();

                    roleRef = FirebaseDatabase.getInstance().getReference("User").child(userUid);
                    roleRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String roleName = snapshot.child("role").getValue(String.class);


                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, "Вы успешно вошли!", Toast.LENGTH_SHORT).show();
                                Intent succesLogin = new Intent(LoginActivity.this,KnopkaScan.class);
                                startActivity(succesLogin);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Неправильный логин или пароль!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        LoginButton = findViewById(R.id.login_btn);
        InputLogin = findViewById(R.id.login_mail_input);
        InputPassword = findViewById(R.id.login_password_input);
        loadingBar = new ProgressDialog(this);

        mDataBase = FirebaseDatabase.getInstance().getReference();
        Paper.init(this);

    }
}