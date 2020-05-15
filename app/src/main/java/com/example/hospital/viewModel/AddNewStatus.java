package com.example.hospital.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.hospital.Entity.Status;

public class AddNewStatus extends AndroidViewModel {
    private Reprsitory mReprsitory;

    public AddNewStatus(@NonNull Application application) {
        super(application);
        mReprsitory = new Reprsitory(application);

    }

    public void insert(Status status) {
        mReprsitory.Insert(status);
    }

    public void Delet(Status status) {
        mReprsitory.Delete(status);
    }

    public void Update(Status status) {
        mReprsitory.Update(status);
    }
}
