package easybuy.server.networkdata.beans;

/**
 * Created by Chen on 2017/6/8.
 */
public class TheaterVO {
    private Integer status;
    private TheaterData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(TheaterData data) {
        this.data = data;
    }

    public TheaterData getData() {
        return data;
    }
}
