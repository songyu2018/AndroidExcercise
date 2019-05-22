package com.yusong.photosearch.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    /**
     * No args constructor for use in serialization
     *
     */
    public SearchResult() {
    }

    /**
     *
     * @param photos
     * @param stat
     */
    public SearchResult(Photos photos, String stat) {
        super();
        this.photos = photos;
        this.stat = stat;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}

