package Beans;

import java.util.ArrayList;

/**
 * Created by Chen on 2017/6/7.
 */
public class RootDistrict extends BaseDistrict {
    private ArrayList<String> citycode;
    private ArrayList<CoreDistrict> districts;

    public ArrayList<String> getCitycode() {
        return citycode;
    }

    public void setCitycode(ArrayList<String> citycode) {
        this.citycode = citycode;
    }

    public ArrayList<CoreDistrict> getCoreDistricts() {
        return districts;
    }

    public void setCoreDistricts(ArrayList<CoreDistrict> districts) {
        this.districts = districts;
    }
}
