package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collection extends BaseObservable implements Parcelable {
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
    @SerializedName("cover_photo")
    private Photo mPhoto;
    @SerializedName("results")
    private List<Collection> mCollections;

    public Collection() {
    }

    protected Collection(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mTotalPhoto = in.readInt();
        mLink = in.readParcelable(Link.class.getClassLoader());
        mUser = in.readParcelable(User.class.getClassLoader());
        mPhoto = in.readParcelable(Photo.class.getClassLoader());
    }

    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel in) {
            return new Collection(in);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
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

    public List<Collection> getCollections() {
        return mCollections;
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

    @Override
    public String toString() {
        return mTotalPhoto + " Photos";
    }

}
