package com.yusong.photosearch.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.yusong.photosearch.Models.Photo;

@Database(entities = {Photo.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class PhotoDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "PhotoDatabase.db";
    private static volatile PhotoDatabase instance;
    private static final Object LOCK = new Object();

    public abstract PhotoDao photoDao();

    public static PhotoDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            PhotoDatabase.class, DATABASE_NAME).build();
                }
            }
        }

        return instance;
    }
}
