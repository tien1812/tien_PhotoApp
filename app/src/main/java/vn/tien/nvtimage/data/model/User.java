package vn.tien.nvtimage.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("links")
    private Link mLink;
    @SerializedName("profile_image")
    private ProfileImage mImage;

    public User() {
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Link getLink() {
        return mLink;
    }

    public ProfileImage getImage() {
        return mImage;
    }
}
