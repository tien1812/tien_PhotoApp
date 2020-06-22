package vn.tien.nvtimage.data.server;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.model.User;

public interface DataServer {
    @GET("photos?")
    Observable<List<Photo>>
    getAllImages(@Query("client_id") String key,
                 @Query("page") int page, @Query("per_page") int per);

    @GET("collections?")
    Observable<List<Collection>>
    getAllCollections(@Query("client_id") String key,
                      @Query("page") int page,
                      @Query("per_page") int per);

    @GET("collections/{id}/photos?")
    Observable<List<Photo>>
    getPhotosInCollection(@Path("id") int id,
                          @Query("client_id") String key,
                          @Query("page") int page,
                          @Query("per_page") int per);

    @GET("users/{username}/photos?")
    Observable<List<Photo>> getPhotosOfUser(@Path("username") String username,
                                            @Query("client_id") String key,
                                            @Query("page") int page,
                                            @Query("per_page") int per);


    @GET("users/{username}/likes?")
    Observable<List<Photo>> getLikesOfUser(@Path("username") String username,
                                            @Query("client_id") String key,
                                            @Query("page") int page,
                                            @Query("per_page") int per);

    @GET("search/photos?")
    Observable<Photo>
    searchPhoto(@Query("client_id") String key, @Query("query") String query,
                @Query("per_page") int per);

    @GET("search/collections?")
    Observable<Collection>
    searchCollections(@Query("client_id") String key, @Query("query") String query,
                @Query("per_page") int per);

    @GET("search/users?")
    Observable<User>
    searchUsers(@Query("client_id") String key, @Query("query") String query,
                      @Query("per_page") int per);
}
