package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable {
    @SerializedName("id")
    private String mId;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("name")
    private String mName;
    @SerializedName("links")
    private Link mLink;
    @SerializedName("profile_image")
    private ProfileImage mImage;
    @SerializedName("bio")
    private String mBio;
    @SerializedName("portfolio_url")
    private String mPortfolio;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("results")
    private List<User> mUsers;

    public User() {
    }

    protected User(Parcel in) {
        mId = in.readString();
        mUsername = in.readString();
        mName = in.readString();
        mLink = in.readParcelable(Link.class.getClassLoader());
        mImage = in.readParcelable(ProfileImage.class.getClassLoader());
        mBio = in.readString();
        mPortfolio = in.readString();
        mLocation = in.readString();
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

    public String getUsername() {
        return mUsername;
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

    public String getBio() {
        return mBio;
    }

    public String getPortfolio() {
        return mPortfolio;
    }

    public String getLocation() {
        return mLocation;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mUsername);
        parcel.writeString(mName);
        parcel.writeParcelable(mLink, i);
        parcel.writeParcelable(mImage, i);
        parcel.writeString(mBio);
        parcel.writeString(mPortfolio);
        parcel.writeString(mLocation);
    }

    @Override
    public String toString() {
        return "By" + mName;
    }
}
