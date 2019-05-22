package com.yusong.photosearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yusong.photosearch.utilities.Constants.PHOTO_BIG_IMAGE_URL;
import static com.yusong.photosearch.utilities.Constants.PHOTO_DETAIL_URL;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    String mDetailUrl;
    String mImageUrl;

    // TODO
    // Bind the rest TextViews for detail fields.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mDetailUrl = extras.getString(PHOTO_DETAIL_URL);
            mImageUrl = extras.getString(PHOTO_BIG_IMAGE_URL);
            // and get whatever type user account id is
        }


        Glide.with(this)
                .load(mImageUrl)
                //.apply(new RequestOptions().override(100, 80))
                .into(mImageView);


        // TODO
        // Use the Retrofit to load the detail page with the mDetailUrl
        // and populate the related fields.
    }
}
