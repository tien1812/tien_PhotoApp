package vn.tien.nvtimage.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import vn.tien.nvtimage.utils.StringUltil;

public class Photo extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private String mId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("color")
    private String mColor;
    @SerializedName("alt_description")
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

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getId() {
        return mId;
    }

    public String getCreatedAt() {
        return StringUltil.formartDate(mCreatedAt);
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

    @Override
    public int describeContents() {
        return 0;
    }

    protected Photo(Parcel in) {
        mId = in.readString();
        mCreatedAt = in.readString();
        mColor = in.readString();
        mDescription = in.readString();
        mLikes = in.readInt();
        mUser = in.readParcelable(User.class.getClassLoader());
        mDownloads = in.readInt();
        mUrl = in.readParcelable(Url.class.getClassLoader());
        mLink = in.readParcelable(Link.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mCreatedAt);
        parcel.writeString(mColor);
        parcel.writeString(mDescription);
        parcel.writeInt(mLikes);
        parcel.writeParcelable(mUser,i);
        parcel.writeInt(mDownloads);
        parcel.writeParcelable(mUrl,i);
        parcel.writeParcelable(mLink,i);
    }

    @BindingAdapter({"photo"})
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter({"avatar"})
    public static void loadAvatar(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).apply(RequestOptions.circleCropTransform()).
                into(imageView);
    }
}
