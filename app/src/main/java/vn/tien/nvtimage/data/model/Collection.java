package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Collections implements Parcelable {
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("total_photos")
    private int mTotalPhoto;
    @SerializedName("links")
    private Link mLink;
    @SerializedName("user")
    private User mUser;
    @SerializedName("preview_photos")
    private Photo mPhoto;

    public Collections() {
    }

    protected Collections(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mTotalPhoto = in.readInt();
        mLink = in.readParcelable(Link.class.getClassLoader());
        mUser = in.readParcelable(User.class.getClassLoader());
        mPhoto = in.readParcelable(Photo.class.getClassLoader());
    }

    public static final Creator<Collections> CREATOR = new Creator<Collections>() {
        @Override
        public Collections createFromParcel(Parcel in) {
            return new Collections(in);
        }

        @Override
        public Collections[] newArray(int size) {
            return new Collections[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getTotalPhoto() {
        return mTotalPhoto;
    }

    public Link getLink() {
        return mLink;
    }

    public User getUser() {
        return mUser;
    }

    public Photo getPhoto() {
        return mPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeInt(mTotalPhoto);
        parcel.writeParcelable(mLink, i);
        parcel.writeParcelable(mUser, i);
        parcel.writeParcelable(mPhoto, i);
    }
}
