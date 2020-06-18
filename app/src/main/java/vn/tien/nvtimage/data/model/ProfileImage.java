package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProfileImage implements Parcelable {
    @SerializedName("small")
    private String mSmall;
    @SerializedName("medium")
    private String mMedium;
    @SerializedName("large")
    private String mLarge;

    protected ProfileImage(Parcel in) {
        mSmall = in.readString();
        mMedium = in.readString();
        mLarge = in.readString();
    }

    public static final Creator<ProfileImage> CREATOR = new Creator<ProfileImage>() {
        @Override
        public ProfileImage createFromParcel(Parcel in) {
            return new ProfileImage(in);
        }

        @Override
        public ProfileImage[] newArray(int size) {
            return new ProfileImage[size];
        }
    };

    public String getSmall() {
        return mSmall;
    }

    public String getMedium() {
        return mMedium;
    }

    public String getLarge() {
        return mLarge;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mSmall);
        parcel.writeString(mMedium);
        parcel.writeString(mLarge);
    }
}
