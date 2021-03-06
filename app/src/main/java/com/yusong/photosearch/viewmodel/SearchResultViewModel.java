package com.yusong.photosearch.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yusong.photosearch.Models.Photo;
import com.yusong.photosearch.database.PhotoRepository;

import java.util.List;

public class SearchResultViewModel extends AndroidViewModel {

    private LiveData<List<Photo>> mPhotos;
    private PhotoRepository mRepository;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);

        mRepository = PhotoRepository.getInstance(application.getApplicationContext());
        mPhotos = mRepository.getPhotos();
    }

    public LiveData<List<Photo>> getPhotos() {
        return this.mPhotos;
    }

    public void deleteAllPhotos() {
        mRepository.deleteAllPhotos();
    }

    public void search(String keyWords) {
        mRepository.search(keyWords);
    }
}
