package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
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

    protected User(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mLink = in.readParcelable(Link.class.getClassLoader());
        mImage = in.readParcelable(ProfileImage.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mName);
        parcel.writeParcelable(mLink,i);
        parcel.writeParcelable(mImage,i);
    }

}
