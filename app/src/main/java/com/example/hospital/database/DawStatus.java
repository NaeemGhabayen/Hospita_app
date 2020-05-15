package com.example.hospital.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hospital.Entity.Status;

import java.util.List;

@Dao
public interface DawStatus {
    @Insert()
    void Insert(Status word);

    @Delete()
    void Delete(Status word);

    @Update()
    void Update(Status word);

    @Query("DELETE FROM statusTable")
    void DeletAll();

    @Query("Select * From statusTable")
    LiveData<List<Status>> getAllStatus();
}
