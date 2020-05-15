package com.example.hospital.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.hospital.Entity.Status;
import com.example.hospital.R;
import com.example.hospital.viewModel.AddNewStatus;

public class addNewStutes extends AppCompatActivity {
    public static final String EXTRA_DATA_ID = "com.example.hospital.ui.extraID";
    public static final String EXTRA_DATA_FullName = "com.example.hospital.ui.Fullname";
    public static final String EXTRA_DATA_Status = "com.example.hospital.ui.Status";
    public static final String EXTRA_DATA_Date = "com.example.hospital.ui.Date";
    private EditText fullName, edStutas, date;
    private AddNewStatus addNewStatus;
    private Boolean EditeMode;
    private int mid;
    private Button confierm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_stutes);

        fullName = findViewById(R.id.et_fullName);
        edStutas = findViewById(R.id.et_Stutes);
        date = findViewById(R.id.et_Date);
        confierm = findViewById(R.id.btn_Confierm);
        addNewStatus = ViewModelProviders.of(this).get(AddNewStatus.class);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_DATA_ID)) {
            setTitle("Editing");
            EditeMode = true;
            mid = intent.getIntExtra(EXTRA_DATA_ID, -1);
            fullName.setText(intent.getStringExtra(EXTRA_DATA_FullName));
            edStutas.setText(intent.getStringExtra(EXTRA_DATA_Status));
            date.setText(intent.getStringExtra(EXTRA_DATA_Date));

        } else {
            setTitle("Add New Status");
            EditeMode = false;
        }

        confierm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = fullName.getText().toString().trim();
                String stuts = edStutas.getText().toString().trim();
                String daydate = date.getText().toString().trim();
                Status status = new Status(Name, stuts, daydate);
                if (Name.isEmpty() && stuts.isEmpty() && daydate.isEmpty()) {
                    Toast.makeText(addNewStutes.this, "Please check the fields", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (EditeMode) {
                        status.setId(mid);
                        addNewStatus.Update(status);
                    } else {
                        addNewStatus.insert(status);

                    }
                    finish();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Aboutus:
                startActivity(new Intent(this, Aboutus.class));
                break;
            case R.id.Signout:
                SharedPreferences preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
