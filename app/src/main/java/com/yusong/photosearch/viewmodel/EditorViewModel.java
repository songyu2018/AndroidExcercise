package com.yusong.photosearch.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.yusong.photosearch.Models.Photo;
import com.yusong.photosearch.database.PhotoRepository;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<Photo> mLiveNote =
            new MutableLiveData<>();
    private PhotoRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = PhotoRepository.getInstance(getApplication());
    }

    public void loadData(final int photoId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Photo photo = mRepository.getPhotoById(photoId);
                mLiveNote.postValue(photo);
            }
        });
    }

}
