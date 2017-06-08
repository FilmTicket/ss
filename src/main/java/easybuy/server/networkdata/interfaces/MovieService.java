package easybuy.server.networkdata.interfaces;

import easybuy.server.networkdata.beans.MovieData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Chen on 2017/6/7.
 */
public interface MovieService {
	
    @GET("list.json")
    Observable<MovieData> getAllMovies(@Query("type") String type,
                                     @Query("offset") Integer offset,
                                     @Query("limit") Integer limit);
}
