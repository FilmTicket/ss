package easybuy.server.networkdata.beans;

import java.util.ArrayList;

/**
 * Created by Chen on 2017/6/6.
 */
public class DistrictVO {
    private String status;
    private String info;
    private String infocode;
    private String count;
    private ArrayList<RootDistrict> districts = new ArrayList<RootDistrict>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<RootDistrict> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<RootDistrict> districts) {
        this.districts = districts;
    }
}
