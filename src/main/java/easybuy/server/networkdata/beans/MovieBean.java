package easybuy.server.networkdata.beans;

/**
 * Created by Chen on 2017/6/7.
 */
public class MovieBean {
    private Boolean late;
    private Integer cnms;
    private Integer sn;
    private String showDate;
    private Integer dur;
    private String img;//海报图片路径
    private Double sc;//评分
    private String ver;//电影类型 3D等
    private String nm;//电影名称
    private String scm;//电影描述
    private Boolean imax;//是否有Imax
    private Integer snum;
    private String src;
    private Integer pn;
    private Integer preSale;//预售票房
    private String vd;
    private String dir;//电影导演
    private String star;//电影主演
    private String cat;//电影类型 剧情 动作 惊悚等
    private Integer wish;
    private String time;//上映时间
    private Integer id;//电影id

    public Boolean getLate() {
        return late;
    }

    public void setLate(Boolean late) {
        this.late = late;
    }

    public Integer getCnms() {
        return cnms;
    }

    public void setCnms(Integer cnms) {
        this.cnms = cnms;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public Integer getDur() {
        return dur;
    }

    public void setDur(Integer dur) {
        this.dur = dur;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getSc() {
        return sc;
    }

    public void setSc(Double sc) {
        this.sc = sc;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm;
    }

    public Boolean getImax() {
        return imax;
    }

    public void setImax(Boolean imax) {
        this.imax = imax;
    }

    public Integer getSnum() {
        return snum;
    }

    public void setSnum(Integer snum) {
        this.snum = snum;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public Integer getPreSale() {
        return preSale;
    }

    public void setPreSale(Integer preSale) {
        this.preSale = preSale;
    }

    public String getVd() {
        return vd;
    }

    public void setVd(String vd) {
        this.vd = vd;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public Integer getWish() {
        return wish;
    }

    public void setWish(Integer wish) {
        this.wish = wish;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
