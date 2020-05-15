package com.example.hospital.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hospital.Entity.Status;

import java.util.List;

public class StatusViewModel extends AndroidViewModel {
    private Reprsitory mReprsitory;
    private LiveData<List<Status>> mgetAllStatus;

    public StatusViewModel(@NonNull Application application) {
        super(application);
        mReprsitory = new Reprsitory(application);
        mgetAllStatus = mReprsitory.getGetAllStatus();
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

    public void DeletAll() {
        mReprsitory.DeleteAllWord();
    }

    public LiveData<List<Status>> AllStatu() {
        return mgetAllStatus;
    }
}
