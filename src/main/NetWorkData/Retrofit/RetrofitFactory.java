package Retrofit;

import Interface.DistrictService;
import Interface.MovieService;
import com.google.gson.Gson;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by Chen on 2017/6/6.
 */
public class RetrofitFactory {
    private Retrofit DistrictRetrofit = null;
    private Retrofit MovieRetrofit = null;
    private static RetrofitFactory intance = null;
    private DistrictService districtService = null;
    private MovieService movieService = null;

    public static RetrofitFactory getInstance() {
        if (intance == null) {
            intance = new RetrofitFactory();
        }
        return intance;
    }

    private RetrofitFactory() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                System.out.println(chain.request().url());
                String data = chain.proceed(chain.request()).body().string();
                System.out.println(data);
                return chain.proceed(chain.request());
            }
        }).build();

        Gson gson = new Gson();
        DistrictRetrofit = new Retrofit.Builder()
                .baseUrl("http://restapi.amap.com/v3/config/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        districtService = DistrictRetrofit.create(DistrictService.class);

        MovieRetrofit = new Retrofit.Builder()
                .baseUrl("http://m.maoyan.com/movie/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        movieService = MovieRetrofit.create(MovieService.class);
    }

    public DistrictService getDistrictService() {
        return districtService;
    }

    public MovieService getMovieService() {
        return movieService;
    }
}
