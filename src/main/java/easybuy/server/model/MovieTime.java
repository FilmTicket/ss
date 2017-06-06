package easybuy.server.model;

public class MovieTime {
	private Integer movieTimeId;
	private String date;
	private String startTime;
	private String endTime;
	private Integer movieId;
	private String movieType;
	private Integer theaterId;
	private String price;
	private String hallName;
	
	public MovieTime() {
		super();
	}

	public MovieTime(String date, String startTime, String endTime, Integer movieId, String movieType,
			Integer theaterId, String price, String hallName) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.movieId = movieId;
		this.movieType = movieType;
		this.theaterId = theaterId;
		this.price = price;
		this.hallName = hallName;
	}

	public Integer getMovieTimeId() {
		return movieTimeId;
	}

	public void setMovieTimeId(Integer movieTimeId) {
		this.movieTimeId = movieTimeId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public Integer getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
}
