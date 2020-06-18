package vn.tien.nvtimage.data.source;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import vn.tien.nvtimage.BuildConfig;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.server.DataServer;
import vn.tien.nvtimage.data.server.NetworkServer;

public class PhotoRemoteData implements PhotoDataSource.remote, PhotoDataSource.search {
    private static PhotoRemoteData sPhotoRemoteData;
    private DataServer mDataServer;

    public PhotoRemoteData(DataServer dataServer) {
        mDataServer = dataServer;
    }

    public static PhotoRemoteData getInstance(Context context){
        if (sPhotoRemoteData == null){
            sPhotoRemoteData = new PhotoRemoteData(NetworkServer.getDataServer(context));
        }
        return sPhotoRemoteData;
    }

    @Override
    public Observable<List<Photo>> getPhoto(int page) {
        return mDataServer.getAllImage(BuildConfig.API_KEY,page,30);
    }

    @Override
    public Observable<List<Photo>> searchPhoto(String query) {
        return mDataServer.searchImage(BuildConfig.API_KEY,query);
    }
}
