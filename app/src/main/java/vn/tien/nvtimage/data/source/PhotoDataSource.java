package vn.tien.nvtimage.data.source;

import java.util.List;

import io.reactivex.Observable;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.model.User;

public interface PhotoDataSource {
    interface remote {
        Observable<List<Photo>> getPhoto(int page);

        Observable<List<Collection>> getCollection(int page);

        Observable<List<Photo>> getPhotoInCollection(int id, int page);

        Observable<List<Photo>> getPhotoOfUser(String username, int page);

        Observable<List<Photo>> getLikesOfUser(String username, int page);

        Observable<List<Collection>> getCollectionsOfUser(String username, int page);

    }

    interface search {
        Observable<Photo> searchPhoto(String query);

        Observable<Collection> searchCollection(String query);

        Observable<User> searchUser(String query);
    }
}
