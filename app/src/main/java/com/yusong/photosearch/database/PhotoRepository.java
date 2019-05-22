package com.yusong.photosearch.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.yusong.photosearch.Models.Photo;
import com.yusong.photosearch.Models.SearchResult;
import com.yusong.photosearch.services.ApiService;
import com.yusong.photosearch.services.RetroClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {
    private static PhotoRepository ourInstance;

    public static final String TAG = "PhotoRepository";

    public LiveData<List<Photo>> mPhotos;
    private PhotoDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();
    private static Object lock = new Object();

    public static PhotoRepository getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (lock) {
                ourInstance = new PhotoRepository(context);
            }
        }
        return ourInstance;
    }

    private PhotoRepository(Context context) {
        mDb = PhotoDatabase.getInstance(context);
        mPhotos = getAllPhotos();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //mDb.photoDao().insertAll(SampleData.getNotes());
            }
        });
    }

    private LiveData<List<Photo>> getAllPhotos() {
        return mDb.photoDao().getAll();
    }

    public void deleteAllPhotos() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().deleteAll();
            }
        });
    }

    public Photo getPhotoById(int photoId) {
        return mDb.photoDao().getPhotoById(photoId);
    }

    public void insertPhoto(final Photo photo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().insertPhoto(photo);
            }
        });
    }

    public void deletePhoto (final Photo photo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().deletePhoto(photo);
            }
        });
    }

    public void search(String keyWords) {


        //let apiKey = "1af249b0331dff153977c0cd33dc1844"


//URLString = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=\(apiKey)&text=\(escapedTerm)&per_page=100&
// format=json&nojsoncallback=1"
//URLString = "https://api.flickr.com/services/rest/?method=flickr.photos.getinfo&api_key=\(apiKey)&photo_id=\(phontoID)&format=json&nojsoncallback=1"
//url =  URL(string: "https://farm\(farm).staticflickr.com/\(server)/\(photoID)_\(secret)_\(size).jpg")




        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Map<String, String> params = new HashMap<>();
        params.put("method", "flickr.photos.search");
        params.put("api_key", "1af249b0331dff153977c0cd33dc1844");
        params.put("text", keyWords);
        params.put("per_page", "100");
        params.put("format", "json");
        params.put("nojsoncallback", "1");

        Call<SearchResult> call = api.getSearchResult(params);

        /**
         * Enqueue Callback will be call when get response...
         */
        Log.i(TAG, call.request().url().toString());
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    String status = response.body().getStat();
                    if (status.equals("ok")) {
                        final List<Photo> photos = response.body().getPhotos().getPhoto();
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.photoDao().insertAll(photos);
                            }
                        });

                    } else {
                        Log.e(TAG, status);
                    }

                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
