package Interface;

import Beans.DistrictVO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Chen on 2017/6/6.
 */
public interface DistrictService {

    @GET("district")
    Observable<DistrictVO> getAllDistrict(@Query("keywords") String keywords,
                                          @Query("subdistrict") Integer subdistrict,
                                          @Query("key") String key,
                                          @Query("showbiz") Boolean showbiz,
                                          @Query("extensions") String extensions);
}
