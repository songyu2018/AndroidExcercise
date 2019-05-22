package com.yusong.photosearch.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yusong.photosearch.DetailsActivity;
import com.yusong.photosearch.R;
import com.yusong.photosearch.Models.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yusong.photosearch.utilities.Constants.PHOTO_BIG_IMAGE_URL;
import static com.yusong.photosearch.utilities.Constants.PHOTO_DETAIL_URL;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private final List<Photo> mPhotos;
    private final Context mContext;

    public PhotoAdapter(List<Photo> mPhotos, Context mContext) {
        this.mPhotos = mPhotos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Photo photo = mPhotos.get(position);
        holder.mTextView.setText(photo.getId());
        Glide.with(mContext)
                .load(photo.getPhotoUrl("s"))
                .apply(new RequestOptions().override(100, 80))
                .into(holder.mThumbmail);

        holder.mThumbmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailView(photo);
            }
        });

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailView(photo);
            }
        });
    }

    private void loadDetailView(Photo photo) {
        Intent intent = new Intent(mContext, DetailsActivity.class);
        intent.putExtra(PHOTO_DETAIL_URL, photo.getDetailInfoUrl());
        intent.putExtra(PHOTO_BIG_IMAGE_URL, photo.getPhotoUrl("b"));
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.note_text)
        TextView mTextView;
        @BindView(R.id.thumbnail)
        ImageView mThumbmail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
