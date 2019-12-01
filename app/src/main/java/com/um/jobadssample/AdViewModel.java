package com.um.jobadssample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class AdViewModel extends AndroidViewModel {

    private AdRepository mRepository;

    private LiveData<List<Ad>> mAllAds;

    public AdViewModel(Application application) {
        super(application);
        mRepository = new AdRepository(application);
        mAllAds = mRepository.getAllAds();
    }

    LiveData<List<Ad>> getAllAds() {
        return mAllAds;
    }

    LiveData<Ad> getAd(int id) {
        return mRepository.getAd(id);
    }

    void insert(Ad ad) {
        mRepository.insert(ad);
    }

    void updatePet(Ad ad) {
        mRepository.updateAd(ad);
    }

    void deleteSelected(Ad ad) {
        mRepository.deleteSelected(ad);
    }

    void deleteAll() {
        mRepository.deletaAll();
    }
}
