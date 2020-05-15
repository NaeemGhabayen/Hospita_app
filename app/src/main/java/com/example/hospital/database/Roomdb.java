package com.example.hospital.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hospital.Entity.Status;


@Database(entities = Status.class, version = 1)
public abstract class Roomdb extends RoomDatabase {
    private static Roomdb instance;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Pupuler(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    //Singelton
    public static synchronized Roomdb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Roomdb.class, "Stuats_Db")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    public abstract DawStatus dawStatus();

    private static class Pupuler extends AsyncTask<Void, Void, Void> {
        private DawStatus dawStatus;

        Pupuler(Roomdb roomdb) {
            dawStatus = roomdb.dawStatus();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dawStatus.Insert(new com.example.hospital.Entity.Status("Ali mohammad", "Accedent car", "12/12/2021"));
            dawStatus.Insert(new com.example.hospital.Entity.Status("basel jo", "Accedent car", "15/5/2018"));
            dawStatus.Insert(new com.example.hospital.Entity.Status("Naeem Ghabayen", "Accedent car", "21/2/2014"));
            return null;
        }
    }
}
