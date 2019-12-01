package com.um.jobadssample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface AdDao {

    @Query("SELECT * FROM ads ORDER BY _ID ASC")
    LiveData<List<Ad>> getAllAds();

    @Insert(onConflict = IGNORE)
    void insert(Ad ad);

    @Query("DELETE FROM ads")
    void deleteAll();

    @Delete
    void deleteAd(Ad ad);

    @Query("SELECT * FROM ads WHERE _ID = :adId")
    LiveData<Ad> getAd(int adId);

    @Update
    void update(Ad ad);

}
