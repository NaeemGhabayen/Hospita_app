package com.example.hospital.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "statusTable")
public class Status {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String FullName;
    private String storys;
    @ColumnInfo(name = "Type")
    private String Date;

    public Status() {
    }

    public Status(String fullName, String storys, String date) {
        FullName = fullName;
        this.storys = storys;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getStorys() {
        return storys;
    }

    public void setStorys(String storys) {
        this.storys = storys;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
