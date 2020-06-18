package vn.tien.nvtimage.data.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

public class Url {
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

    @BindingAdapter({"photo"})
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
