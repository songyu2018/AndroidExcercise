package com.yusong.photosearch;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.yusong.photosearch.database.PhotoDao;
import com.yusong.photosearch.database.PhotoDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "Junit";
    private PhotoDatabase mDb;
    private PhotoDao mDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,
                PhotoDatabase.class).build();
        mDao = mDb.photoDao();
        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndRetrieveNotes() {
//        mDao.insertAll(SampleData.getNotes());
//        int count = mDao.getCount();
//        Log.i(TAG, "createAndRetrieveNotes: count=" + count);
//        assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareStrings() {
//        mDao.insertAll(SampleData.getNotes());
//        NoteEntity original = SampleData.getNotes().get(0);
//        NoteEntity fromDb = mDao.getNoteById(1);
//        assertEquals(original.getText(), fromDb.getText());
//        assertEquals(1, fromDb.getId());
    }
}
