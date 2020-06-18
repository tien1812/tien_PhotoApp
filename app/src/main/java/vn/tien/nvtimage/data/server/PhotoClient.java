package vn.tien.nvtimage.data.server;

import android.content.Context;

import androidx.core.content.ContextCompat;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoClient {
    private static Retrofit sRetrofit;
    private static final String BASE_URL = "https://api.unsplash.com/";

    public static Retrofit getInstance(Context context){
        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
