package easybuy.server.model;

public class Ticket {
    private Integer ticketId;
    private Integer userId;
    private String movieName;
    private String theaterName;
    private String dateTime;
    private String hallName;
    private String seats;
    
	public Ticket() {
		super();
	}

	public Ticket(Integer userId, String movieName, String theaterName, String dateTime, String hallName,
			String seats) {
		super();
		this.userId = userId;
		this.movieName = movieName;
		this.theaterName = theaterName;
		this.dateTime = dateTime;
		this.hallName = hallName;
		this.seats = seats;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}
}
