package com.um.jobadssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AdRepository {

    private AdDao mAdDao;
    private LiveData<List<Ad>> mAllAds;
    private LiveData<Ad> adData;


    AdRepository(Application application) {
        AdsDatabase db = AdsDatabase.getDatabase(application);
        mAdDao = db.AdDao();
        mAllAds = mAdDao.getAllAds();
    }

    LiveData<List<Ad>> getAllAds() {
        return mAllAds;
    }

    void insert(Ad ad) {
        new insertAsyncTask(mAdDao).execute(ad);
    }

    LiveData<Ad> getAd(int id) {
        adData = mAdDao.getAd(id);
        return adData;
    }

    void deletaAll() {
        new deleteAsyncTask(mAdDao).execute();
    }

    void updateAd(Ad ad) {
        new updateAsyncTask(mAdDao).execute(ad);
    }

    void deleteSelected(Ad ad) {
        new deleteSelectedAsyncTask(mAdDao).execute(ad);
    }

    private static class deleteSelectedAsyncTask extends AsyncTask<Ad, Void, Void> {
        private AdDao mAsyncTaskDao;

        deleteSelectedAsyncTask(AdDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Ad... ads) {
            mAsyncTaskDao.deleteAd(ads[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Ad, Void, Void> {

        private AdDao mAsyncTaskDao;

        updateAsyncTask(AdDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Ad... ads) {
            mAsyncTaskDao.update(ads[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Ad, Void, Void> {

        private AdDao mAsyncTaskDao;

        insertAsyncTask(AdDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Ad... ads) {
            mAsyncTaskDao.insert(ads[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private AdDao mAsyncTaskDao;

        deleteAsyncTask(AdDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
