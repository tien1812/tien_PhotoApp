package vn.tien.nvtimage.data.repository;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.source.PhotoDataSource;
import vn.tien.nvtimage.data.source.PhotoRemoteData;

public class PhotoRepository implements PhotoDataSource.remote ,PhotoDataSource.search {
    private PhotoRemoteData mPhotoRemoteData;
    private static PhotoRepository sPhotoRepository;

    public PhotoRepository(PhotoRemoteData photoRemoteData) {
        mPhotoRemoteData = photoRemoteData;
    }

    public static PhotoRepository getInstance(Context context){
         if (sPhotoRepository == null){
             sPhotoRepository = new PhotoRepository(PhotoRemoteData.getInstance(context));
         }
         return sPhotoRepository;
    }

    @Override
    public Observable<List<Photo>> getPhoto(int page) {
        return mPhotoRemoteData.getPhoto(page);
    }

    @Override
    public Observable<List<Photo>> searchPhoto(String query) {
        return mPhotoRemoteData.searchPhoto(query);
    }
}
