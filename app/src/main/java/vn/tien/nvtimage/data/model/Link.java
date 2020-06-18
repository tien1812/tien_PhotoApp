package vn.tien.nvtimage.data.model;

import com.google.gson.annotations.SerializedName;

public class Link {
    @SerializedName("self")
    private String mSelf;
    @SerializedName("photos")
    private String mPhotos;

    public Link() {
    }

    public String getSelf() {
        return mSelf;
    }

    public String getPhotos() {
        return mPhotos;
    }
}
