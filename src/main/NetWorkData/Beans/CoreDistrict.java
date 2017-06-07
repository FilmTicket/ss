package Beans;

import java.util.ArrayList;

/**
 * Created by Chen on 2017/6/7.
 */
public class CoreDistrict extends BaseDistrict {
    private ArrayList<CoreDistrict> districts;

    public ArrayList<CoreDistrict> getCoreDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<CoreDistrict> districts) {
        this.districts = districts;
    }
}
