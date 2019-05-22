package com.yusong.photosearch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yusong.photosearch.R;
import com.yusong.photosearch.Models.Photo;
import com.yusong.photosearch.ui.PhotoAdapter;
import com.yusong.photosearch.viewmodel.SearchResultViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

//    @OnClick(R.id.fab)
//    void fabClickHandler() {
//        Intent intent = new Intent(this, EditorActivity.class);
//        startActivity(intent);
//    }

    private List<Photo> photos = new ArrayList<>();
    private PhotoAdapter mAdapter;
    private SearchResultViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();

    }

    private void initViewModel() {

        final Observer<List<Photo>> photosObserver =
                new Observer<List<Photo>>() {
                    @Override
                    public void onChanged(@Nullable List<Photo> photoList) {
                        photos.clear();
                        photos.addAll(photoList);

                        if (photos.size() > 0) {
                            if (mAdapter == null) {
                                mAdapter = new PhotoAdapter(photos,
                                        MainActivity.this);
                                mRecyclerView.setAdapter(mAdapter);
                            } else {
                                mAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                };

        mViewModel = ViewModelProviders.of(this)
                .get(SearchResultViewModel.class);
        mViewModel.mPhotos.observe(this, photosObserver);
        this.search("trees");
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(divider);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {
            deleteAllPhotos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllPhotos() {
        mViewModel.deleteAllPhotos();
    }

    private void search(String keyWords) {
        mViewModel.search(keyWords);
    }
}
