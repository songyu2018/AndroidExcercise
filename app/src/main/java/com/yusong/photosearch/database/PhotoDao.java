package com.yusong.photosearch.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yusong.photosearch.Models.Photo;

import java.util.List;

@Dao
public interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhoto(Photo photo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Photo> photos);

    @Delete
    void deletePhoto(Photo photo);

    @Query("SELECT * FROM photos WHERE id = :id")
    Photo getPhotoById(int id);

    @Query("SELECT * FROM photos ORDER BY id DESC")
    LiveData<List<Photo>> getAll();

    @Query("DELETE FROM photos")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM photos")
    int getCount();

}
