package easybuy.server.model;

public class Movie {
    private Integer movieId;
    private String movieName;
    private String movieDes;
    private String postUrl;
    
	public Movie() {
		super();
	}

	public Movie(String movieName, String movieDes, String postUrl) {
		super();
		this.movieName = movieName;
		this.movieDes = movieDes;
		this.postUrl = postUrl;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDes() {
		return movieDes;
	}

	public void setMovieDes(String movieDes) {
		this.movieDes = movieDes;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
}
