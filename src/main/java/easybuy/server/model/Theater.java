package easybuy.server.model;

public class Theater {
    private Integer theaterId;
    private String theaterName;
    private String theaterAddr;
    private String theaterDis;
    private String theaterLowest;
    private String tag;
    
	public Theater() {
		super();
	}

	public Theater(String theaterName, String theaterAddr, String theaterDis, String theaterLowest, String tag) {
		super();
		this.theaterName = theaterName;
		this.theaterAddr = theaterAddr;
		this.theaterDis = theaterDis;
		this.theaterLowest = theaterLowest;
		this.tag = tag;
	}

	public Integer getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterAddr() {
		return theaterAddr;
	}

	public void setTheaterAddr(String theaterAddr) {
		this.theaterAddr = theaterAddr;
	}

	public String getTheaterDis() {
		return theaterDis;
	}

	public void setTheaterDis(String theaterDis) {
		this.theaterDis = theaterDis;
	}

	public String getTheaterLowest() {
		return theaterLowest;
	}

	public void setTheaterLowest(String theaterLowest) {
		this.theaterLowest = theaterLowest;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
