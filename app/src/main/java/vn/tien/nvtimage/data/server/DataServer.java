package vn.tien.nvtimage.data.server;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.tien.nvtimage.data.model.Photo;

public interface DataServer {
    @GET("photos?")
    Observable<List<Photo>> getAllImage(@Query("client_id") String key, @Query("page") int page, @Query("per_page") int per);

    @GET("photos?")
    Observable<List<Photo>> searchImage(@Query("client_id") String key, @Query("query") String query);
}
