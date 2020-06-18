package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Link implements Parcelable {
    @SerializedName("self")
    private String mSelf;
    @SerializedName("photos")
    private String mPhotos;

    public Link() {
    }

    protected Link(Parcel in) {
        mSelf = in.readString();
        mPhotos = in.readString();
    }

    public static final Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };

    public String getSelf() {
        return mSelf;
    }

    public String getPhotos() {
        return mPhotos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mSelf);
        parcel.writeString(mPhotos);
    }
}
