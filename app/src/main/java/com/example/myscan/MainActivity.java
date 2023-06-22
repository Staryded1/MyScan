package com.example.myscan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button joinButton, loginButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinButton = (Button) findViewById(R.id.register_btn);
        loginButton = (Button)  findViewById(R.id.login_btn);

        FirebaseApp.initializeApp(this);

        // Получение ссылки на раздел "devices" и создание его в базе данных
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference devicesRef = database.getReference("devices");

        devicesRef.setValue(null)
                .addOnSuccessListener(aVoid -> {
                    // Успешное создание раздела "devices" в базе данных
                    Log.d("ScanRezult", "Раздел 'devices' успешно создан в базе данных");
                })
                .addOnFailureListener(e -> {
                    // Ошибка при создании раздела "devices" в базе данных
                    Log.e("ScanRezult", "Ошибка при создании раздела 'devices': " + e.getMessage());
                });



        joinButton.setOnClickListener(v -> {
            Intent RegisterIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(RegisterIntent);
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}