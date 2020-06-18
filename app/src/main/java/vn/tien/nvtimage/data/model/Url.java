package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Url implements Parcelable {
    @SerializedName("raw")
    private String mRaw;
    @SerializedName("full")
    private String mFull;
    @SerializedName("regular")
    private String mRegular;
    @SerializedName("small")
    private String mSmall;
    @SerializedName("thumb")
    private String mThumb;

    public Url() {
    }

    protected Url(Parcel in) {
        mRaw = in.readString();
        mFull = in.readString();
        mRegular = in.readString();
        mSmall = in.readString();
        mThumb = in.readString();
    }

    public static final Creator<Url> CREATOR = new Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel in) {
            return new Url(in);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };

    public String getRaw() {
        return mRaw;
    }

    public String getFull() {
        return mFull;
    }

    public String getRegular() {
        return mRegular;
    }

    public String getSmall() {
        return mSmall;
    }

    public String getThumb() {
        return mThumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mRaw);
        parcel.writeString(mFull);
        parcel.writeString(mRegular);
        parcel.writeString(mSmall);
        parcel.writeString(mThumb);
    }

}
