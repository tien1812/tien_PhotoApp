package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Link implements Parcelable {
    @SerializedName("html")
    private String mHtml;
    @SerializedName("photos")
    private String mPhotos;

    public Link() {
    }

    protected Link(Parcel in) {
        mHtml = in.readString();
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

    public String getHtml() {
        return mHtml;
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
        parcel.writeString(mHtml);
        parcel.writeString(mPhotos);
    }
}
