package vn.tien.nvtimage.data.repository;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.data.source.PhotoDataSource;
import vn.tien.nvtimage.data.source.PhotoRemoteData;

public class PhotoRepository implements PhotoDataSource.remote, PhotoDataSource.search {
    private PhotoRemoteData mPhotoRemoteData;
    private static PhotoRepository sPhotoRepository;

    public PhotoRepository(PhotoRemoteData photoRemoteData) {
        mPhotoRemoteData = photoRemoteData;
    }

    public static PhotoRepository getInstance(Context context) {
        if (sPhotoRepository == null) {
            sPhotoRepository = new PhotoRepository(PhotoRemoteData.getInstance(context));
        }
        return sPhotoRepository;
    }

    @Override
    public Observable<List<Photo>> getPhoto(int page) {
        return mPhotoRemoteData.getPhoto(page);
    }

    @Override
    public Observable<List<Collection>> getCollection(int page) {
        return mPhotoRemoteData.getCollection(page);
    }

    @Override
    public Observable<List<Photo>> getPhotoInCollection(int id, int page) {
        return mPhotoRemoteData.getPhotoInCollection(id,page);
    }

    @Override
    public Observable<List<Photo>> getPhotoOfUser(String username, int page) {
        return mPhotoRemoteData.getPhotoOfUser(username,page);
    }

    @Override
    public Observable<List<Photo>> getLikesOfUser(String username, int page) {
        return mPhotoRemoteData.getLikesOfUser(username,page);
    }

    @Override
    public Observable<Photo> searchPhoto(String query) {
        return mPhotoRemoteData.searchPhoto(query);
    }

    @Override
    public Observable<Collection> searchCollection(String query) {
        return mPhotoRemoteData.searchCollection(query);
    }

    @Override
    public Observable<User> searchUser(String query) {
        return mPhotoRemoteData.searchUser(query);
    }
}
