package vn.tien.nvtimage.data.source;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import io.reactivex.Observable;
import vn.tien.nvtimage.BuildConfig;
import vn.tien.nvtimage.constant.NetworkState;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.data.server.DataServer;
import vn.tien.nvtimage.data.server.NetworkServer;

public class PhotoRemoteData extends PageKeyedDataSource<Long, Photo>
        implements PhotoDataSource.remote, PhotoDataSource.search {
    private static PhotoRemoteData sPhotoRemoteData;
    private DataServer mDataServer;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;

    public PhotoRemoteData(DataServer dataServer) {
        mDataServer = dataServer;
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }


    public static PhotoRemoteData getInstance(Context context) {
        if (sPhotoRemoteData == null) {
            sPhotoRemoteData = new PhotoRemoteData(NetworkServer.getDataServer(context));
        }
        return sPhotoRemoteData;
    }

    @Override
    public Observable<List<Photo>> getPhoto(int page) {
        return mDataServer.getAllImages(BuildConfig.API_KEY, page, 30);
    }

    @Override
    public Observable<List<Collection>> getCollection(int page) {
        return mDataServer.getAllCollections(BuildConfig.API_KEY, page, 30);
    }

    @Override
    public Observable<List<Photo>> getPhotoInCollection(int id, int page) {
        return mDataServer.getPhotosInCollection(id, BuildConfig.API_KEY, page, 30);
    }

    @Override
    public Observable<List<Photo>> getPhotoOfUser(String username, int page) {
        return mDataServer.getPhotosOfUser(username, BuildConfig.API_KEY, page, 30);
    }

    @Override
    public Observable<List<Photo>> getLikesOfUser(String username, int page) {
        return mDataServer.getLikesOfUser(username, BuildConfig.API_KEY, page, 30);
    }

    @Override
    public Observable<List<Collection>> getCollectionsOfUser(String username, int page) {
        return mDataServer.getCollectionsOfUser(username,BuildConfig.API_KEY,page,30);
    }

    @Override
    public Observable<Photo> searchPhoto(String query) {
        return mDataServer.searchPhoto(BuildConfig.API_KEY, query, 30);
    }

    @Override
    public Observable<Collection> searchCollection(String query) {
        return mDataServer.searchCollections(BuildConfig.API_KEY, query, 30);
    }

    @Override
    public Observable<User> searchUser(String query) {
        return mDataServer.searchUsers(BuildConfig.API_KEY, query, 30);
    }

    //paging
    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Photo> callback) {
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Photo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Photo> callback) {

    }
}
