package vn.tien.nvtimage.data.source;

import java.util.List;

import io.reactivex.Observable;
import vn.tien.nvtimage.data.model.Photo;

public interface PhotoDataSource {
    interface remote {
        Observable<List<Photo>> getPhoto(int page );
    }

    interface search {
        Observable<List<Photo>> searchPhoto(String query);
    }
}
