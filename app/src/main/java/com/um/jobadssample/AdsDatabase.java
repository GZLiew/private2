package com.um.jobadssample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Ad.class}, version = 1)
public abstract class AdsDatabase extends RoomDatabase {

    public abstract AdDao AdDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile AdsDatabase INSTANCE;

    // Singleton
    static AdsDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AdsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AdsDatabase.class, "ads_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static AdsDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AdDao mDao;

        PopulateDbAsync(AdsDatabase db) {
            mDao = db.AdDao();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

            }
            catch (Exception e) {
                Log.v("Database", e.getMessage());
            }

            return null;
        }
    }
}
