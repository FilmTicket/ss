package easybuy.server.model;

public class SeatInfo {
	private Integer seatId;
	private Integer position;
	private Integer row;
	private Integer column;
	private Integer status;
	private Integer movieTimeId;
	
	public SeatInfo() {
		super();
	}

	public SeatInfo(Integer position, Integer row, Integer column, Integer status, Integer movieTimeId) {
		super();
		this.position = position;
		this.row = row;
		this.column = column;
		this.status = status;
		this.movieTimeId = movieTimeId;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMovieTimeId() {
		return movieTimeId;
	}

	public void setMovieTimeId(Integer movieTimeId) {
		this.movieTimeId = movieTimeId;
	}
}
