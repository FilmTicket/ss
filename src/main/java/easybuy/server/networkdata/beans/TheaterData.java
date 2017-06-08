package easybuy.server.networkdata.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Chen on 2017/6/8.
 */
public class TheaterData {
    @SerializedName("番禺区")
    private ArrayList<TheaterBean> panyu;

    @SerializedName("白云区")
    private ArrayList<TheaterBean> baiyun;

    @SerializedName("天河区")
    private ArrayList<TheaterBean> tianhe;

    @SerializedName("海珠区")
    private ArrayList<TheaterBean> haizhu;

    @SerializedName("越秀区")
    private ArrayList<TheaterBean> yuexiu;

    @SerializedName("荔湾区")
    private ArrayList<TheaterBean> liwan;

    @SerializedName("增城区")
    private ArrayList<TheaterBean> zengcheng;

    @SerializedName("花都区")
    private ArrayList<TheaterBean> huadu;

    @SerializedName("黄埔区")
    private ArrayList<TheaterBean> huangpu;

    @SerializedName("南沙区")
    private ArrayList<TheaterBean> nansha;

    @SerializedName("从化区")
    private ArrayList<TheaterBean> conghua;

    public ArrayList<TheaterBean> getPanyu() {
        return panyu;
    }

    public void setPanyu(ArrayList<TheaterBean> panyu) {
        this.panyu = panyu;
    }

    public ArrayList<TheaterBean> getBaiyun() {
        return baiyun;
    }

    public void setBaiyun(ArrayList<TheaterBean> baiyun) {
        this.baiyun = baiyun;
    }

    public ArrayList<TheaterBean> getTianhe() {
        return tianhe;
    }

    public void setTianhe(ArrayList<TheaterBean> tianhe) {
        this.tianhe = tianhe;
    }

    public ArrayList<TheaterBean> getHaizhu() {
        return haizhu;
    }

    public void setHaizhu(ArrayList<TheaterBean> haizhu) {
        this.haizhu = haizhu;
    }

    public ArrayList<TheaterBean> getYuexiu() {
        return yuexiu;
    }

    public void setYuexiu(ArrayList<TheaterBean> yuexiu) {
        this.yuexiu = yuexiu;
    }

    public ArrayList<TheaterBean> getLiwan() {
        return liwan;
    }

    public void setLiwan(ArrayList<TheaterBean> liwan) {
        this.liwan = liwan;
    }

    public ArrayList<TheaterBean> getZengcheng() {
        return zengcheng;
    }

    public void setZengcheng(ArrayList<TheaterBean> zengcheng) {
        this.zengcheng = zengcheng;
    }

    public ArrayList<TheaterBean> getHuadu() {
        return huadu;
    }

    public void setHuadu(ArrayList<TheaterBean> huadu) {
        this.huadu = huadu;
    }

    public ArrayList<TheaterBean> getHuangpu() {
        return huangpu;
    }

    public void setHuangpu(ArrayList<TheaterBean> huangpu) {
        this.huangpu = huangpu;
    }

    public ArrayList<TheaterBean> getNansha() {
        return nansha;
    }

    public void setNansha(ArrayList<TheaterBean> nansha) {
        this.nansha = nansha;
    }

    public ArrayList<TheaterBean> getConghua() {
        return conghua;
    }

    public void setConghua(ArrayList<TheaterBean> conghua) {
        this.conghua = conghua;
    }

    public ArrayList<TheaterBean> getAllTheaterBeans() {
        ArrayList<TheaterBean> all = new ArrayList<TheaterBean>();
        all.addAll(panyu);
        all.addAll(baiyun);
        all.addAll(tianhe);
        all.addAll(haizhu);
        all.addAll(yuexiu);
        all.addAll(liwan);
        all.addAll(zengcheng);
        all.addAll(huadu);
        all.addAll(huangpu);
        all.addAll(conghua);
        return all;
    }
}
