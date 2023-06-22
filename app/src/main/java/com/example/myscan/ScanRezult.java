package com.example.myscan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class ScanRezult extends AppCompatActivity {

    private ImageView deviceImageView;
    private TextView deviceNameTextView;
    private TextView deviceSNTextView;
    private TextView devicePNTextView;
    private TextView deviceDataTextView;

    private StorageReference storageRef;
    private StorageTask<UploadTask.TaskSnapshot> uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_rezult);

        // Получаем ссылки на элементы интерфейса из макета
        deviceImageView = findViewById(R.id.device_image);
        deviceNameTextView = findViewById(R.id.device_name);
        deviceSNTextView = findViewById(R.id.device_sn);
        devicePNTextView = findViewById(R.id.device_pn);
        deviceDataTextView = findViewById(R.id.device_data);

        // Получаем данные из интента, переданные из предыдущей активности
        Intent intent = getIntent();
        String deviceName = intent.getStringExtra("deviceName");
        String deviceSN = intent.getStringExtra("deviceSN");
        String devicePN = intent.getStringExtra("devicePN");
        String deviceData = intent.getStringExtra("deviceData");
        String imageUri = intent.getStringExtra("imageUri");

        // Устанавливаем значения в текстовые поля
        deviceNameTextView.setText(deviceName);
        deviceSNTextView.setText(deviceSN);
        devicePNTextView.setText(devicePN);
        deviceDataTextView.setText(deviceData);

        // Получаем ссылку на Firebase Storage
        storageRef = FirebaseStorage.getInstance().getReference();

        // Загружаем и отображаем изображение, если ссылка на изображение доступна
        if (imageUri != null) {
            loadAndDisplayImage(imageUri);
        }
    }

    private void loadAndDisplayImage(String imageUri) {
        // Создаем ссылку на файл в Firebase Storage
        StorageReference imageRef = storageRef.child(imageUri);

        // Загружаем изображение и отображаем его с помощью библиотеки Glide
        Glide.with(this)
                .load(imageRef)
                .into(deviceImageView);
    }

    // ...
}