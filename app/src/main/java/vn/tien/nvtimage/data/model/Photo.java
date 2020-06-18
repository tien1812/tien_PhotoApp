package vn.tien.nvtimage.data.model;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id")
    private String mId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("color")
    private String mColor;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("urls")
    private Url mUrl;
    @SerializedName("links")
    private Link mLink;
    @SerializedName("likes")
    private int mLikes;
    @SerializedName("user")
    private User mUser;
    @SerializedName("downloads")
    private int mDownloads;

    public Photo() {
    }

    public String getId() {
        return mId;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getColor() {
        return mColor;
    }

    public String getDescription() {
        return mDescription;
    }

    public Url getUrl() {
        return mUrl;
    }

    public Link getLink() {
        return mLink;
    }

    public int getLikes() {
        return mLikes;
    }

    public User getUser() {
        return mUser;
    }

    public int getDownloads() {
        return mDownloads;
    }
}
