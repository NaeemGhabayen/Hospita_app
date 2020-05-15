package com.example.hospital.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.Entity.Status;
import com.example.hospital.R;
import com.example.hospital.adapter.StatusAdapter;
import com.example.hospital.viewModel.StatusViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActiviy extends AppCompatActivity {
    private RecyclerView rv_Hospital;
    private StatusAdapter statusAdapter;
    private StatusViewModel statusViewModel;
    private FloatingActionButton fAB_add;
    private login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activiy);
        fAB_add = findViewById(R.id.fAB_add);
        rv_Hospital = findViewById(R.id.rv_Hospital);
        rv_Hospital.setLayoutManager(new LinearLayoutManager(this));
        rv_Hospital.setHasFixedSize(true);
        statusAdapter = new StatusAdapter();
        rv_Hospital.setAdapter(statusAdapter);
        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);
        statusViewModel.AllStatu().observe(this, new Observer<List<Status>>() {
            @Override
            public void onChanged(List<Status> statuses) {
                statusAdapter.setlist(statuses);
            }
        });
        statusAdapter.OnItemCliclkLisener(new StatusAdapter.OnItemCliclkLisener() {
            @Override
            public void onItemClick(Status status) {

                Intent i = new Intent(HomeActiviy.this, addNewStutes.class);
                i.putExtra(addNewStutes.EXTRA_DATA_ID, status.getId());
                i.putExtra(addNewStutes.EXTRA_DATA_FullName, status.getFullName());
                i.putExtra(addNewStutes.EXTRA_DATA_Status, status.getStorys());
                i.putExtra(addNewStutes.EXTRA_DATA_Date, status.getDate());
                startActivity(i);
                Toast.makeText(HomeActiviy.this, "Can you Edit", Toast.LENGTH_SHORT).show();


            }
        });
        fAB_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActiviy.this, addNewStutes.class);
                Toast.makeText(HomeActiviy.this, "Can you Add", Toast.LENGTH_SHORT).show();

                startActivityForResult(i, 1);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int postion = viewHolder.getAdapterPosition();
                statusViewModel.Delet(statusAdapter.getAllWord(postion));
            }
        }).attachToRecyclerView(rv_Hospital);
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
