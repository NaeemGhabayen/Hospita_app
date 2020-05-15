package com.example.hospital.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hospital.Entity.Status;
import com.example.hospital.database.DawStatus;
import com.example.hospital.database.Roomdb;

import java.util.List;

public class Reprsitory {
    private DawStatus dawStatus;
    private LiveData<List<Status>> getAllStatus;

    public Reprsitory(Application application) {
        Roomdb roomdb = Roomdb.getInstance(application);
        dawStatus = roomdb.dawStatus();
        getAllStatus = dawStatus.getAllStatus();
    }

    public void Insert(Status status) {
        new InsertAsyencTask(dawStatus).execute(status);
    }

    public void Delete(Status status) {

        new DeletAsyencTask(dawStatus).execute(status);
    }

    public void Update(Status status) {
        new UpdateAsyencTask(dawStatus).execute(status);
    }

    public LiveData<List<Status>> getGetAllStatus() {
        return getAllStatus;
    }

    public void DeleteAllWord() {
        new DeletAllAsyencTask(dawStatus).execute();
    }


    private static class InsertAsyencTask extends AsyncTask<Status, Void, Void> {
        private DawStatus mDawStatus;

        public InsertAsyencTask(DawStatus mDawStatus) {
            this.mDawStatus = mDawStatus;
        }

        @Override
        protected Void doInBackground(com.example.hospital.Entity.Status... statuses) {

            mDawStatus.Insert(statuses[0]);
            return null;
        }
    }

    private static class DeletAsyencTask extends AsyncTask<Status, Void, Void> {
        private DawStatus mDawStatus;

        public DeletAsyencTask(DawStatus mDawStatus) {
            this.mDawStatus = mDawStatus;
        }

        @Override
        protected Void doInBackground(com.example.hospital.Entity.Status... statuses) {
            mDawStatus.Delete(statuses[0]);
            return null;
        }
    }

    private static class UpdateAsyencTask extends AsyncTask<Status, Void, Void> {
        private DawStatus mDawStatus;

        public UpdateAsyencTask(DawStatus mDawStatus) {
            this.mDawStatus = mDawStatus;
        }

        @Override
        protected Void doInBackground(com.example.hospital.Entity.Status... statuses) {

            mDawStatus.Update(statuses[0]);
            return null;
        }
    }

    private static class DeletAllAsyencTask extends AsyncTask<Void, Void, Void> {
        private DawStatus mDawStatus;

        public DeletAllAsyencTask(DawStatus mDawStatus) {
            this.mDawStatus = mDawStatus;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDawStatus.DeletAll();
            return null;
        }
    }


}