package com.example.hospital.ui;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.hospital.R;

public class login extends AppCompatActivity {
    private Button btn_login;
    private EditText Email, Passwoerd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = findViewById(R.id.et_email);
        Passwoerd = findViewById(R.id.et_password);
        if (introsavepre()) {
            Intent intent = new Intent(this, HomeActiviy.class);
            startActivity(intent);
            finish();
        }

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText().toString().isEmpty() && Passwoerd.getText().toString().isEmpty()) {
                    Toast.makeText(login.this, "Pleas check your fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(login.this, HomeActiviy.class);
                    createNotification();
                    savePrefrence();
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public boolean introsavepre() {


        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MyPref", MODE_PRIVATE);
        boolean isOpen = sharedPreferences.getBoolean("isIntroOpend", false);
        return isOpen;


    }

    private void savePrefrence() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isIntroOpend", true);
        editor.commit();
    }

    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_emai)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.addnew))
                .setContentTitle("Hospital")
                .setContentText("Thank you you have been logged in successfully")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
