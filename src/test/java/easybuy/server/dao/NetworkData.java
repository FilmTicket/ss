package easybuy.server.dao;

import Beans.CoreDistrict;
import Beans.DistrictVO;
import Beans.MovieBean;
import Beans.MovieVO;
import Retrofit.RetrofitFactory;
import rx.functions.Action1;

import java.util.ArrayList;

/**
 * Created by Chen on 2017/6/7.
 */
public class NetworkData {
    public static void main(String[] args) {
        final String keywords = "中国";
        final Integer subdistrict = 3;
        final String API_KEY = "a0910adf7dba9c176a51e8bab4765956";
        final Boolean showbiz = false;
        final String extensions = "base";
        final String type = "all";
        final Integer offset = 0;
        final Integer limit = 2000;

        RetrofitFactory.getInstance().getDistrictService()
                .getAllDistrict(keywords,
                        subdistrict,
                        API_KEY,
                        showbiz,
                        extensions)
                .subscribe(new Action1<DistrictVO>() {
                    @Override
                    public void call(DistrictVO districtVO) {
                        ArrayList<CoreDistrict> provinces = districtVO.getDistricts().get(0).getCoreDistricts();
                        System.out.println(provinces.size());
                        int count = 0;
                        for (CoreDistrict province : provinces) {
                            ArrayList<CoreDistrict> cities = province.getCoreDistricts();
                            for(CoreDistrict city : cities) {
                                ArrayList<CoreDistrict> districts = city.getCoreDistricts();
                                for (CoreDistrict district : districts) {
                                    System.out.println(province.getName()+ " "+ city.getName()+ " "+ district.getName());
                                    count++;
                                }
                            }
                        }
                        System.out.println("count:"+count);
                    }
                });

        RetrofitFactory.getInstance().getMovieService()
                .getAllMovies(type, offset, limit)
                .subscribe(new Action1<MovieVO>() {
                    @Override
                    public void call(MovieVO movieVO) {
                        int count = 0;
                        ArrayList<MovieBean> movieBeans = movieVO.getData().getMovies();
                        for (MovieBean movieBean : movieBeans) {
                            System.out.println(movieBean.getNm()+" "+movieBean.getCat()+" "+movieBean.getStar()+" "+movieBean.getImg());
                            count++;
                        }
                        System.out.println(count);
                    }
                });

    }
}
