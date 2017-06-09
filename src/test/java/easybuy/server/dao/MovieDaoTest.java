package easybuy.server.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import easybuy.server.model.Movie;
import easybuy.server.model.MovieTime;
import easybuy.server.model.PopularMovie;
import easybuy.server.model.SeatInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")

public class MovieDaoTest {

	@Autowired
	private MovieDao moviedao;
	
	//@Test
	public void addMovieTest() {
		String movieName = "一路向西";
		String movieDes = "奥斯卡获奖电影"; 
		String postUrl = "http://p1.meituan.net/165.220/movie/4185bf22758d232e15c5bf52f89a7553867763.png";
		
		String message = moviedao.addMovie(movieName, movieDes, postUrl);
		System.out.println("\naddMovie in test:");
	
		if (message != null) {
			System.out.println("Movie has existed\n");
		} else {
			System.out.println("Movie add success\n");
		}
	}
	
	//@Test
	public void addPopularMovieTest() {
		String movieName = "加勒比海盗5";
		String movieDes = "死无对证 喜剧,动作,奇幻 约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹"; 
		String postUrl = "http://p0.meituan.net/165.220/movie/ee5e691b425292f455c3eac5c628cfc7904509.png";
		
		String message = moviedao.addPopularMovie(movieName, movieDes, postUrl);
		System.out.println("\naddMovie in test:");
	
		if (message != null) {
			System.out.println("Movie has existed\n");
		} else {
			System.out.println("Movie add success\n");
		}
	}
	
	//@Test
	public void getPoppular() {
		List<PopularMovie> populars =  moviedao.getPopular();
		System.out.println("\ngetPoppular in test:");
		
		if (populars == null||populars.isEmpty()) {
			System.out.println("getPoppular fails\n");
		} else {
			for (int i = 0; i < populars.size(); ++i) {
				System.out.println(populars.get(i).getMovieName()+"\n");
			}
		}		
	}
	
	//@Test
	public void searchTheatersTest() {
		String keyword = "神奇女侠";
			
		List<Movie> movies1 = moviedao.searchMovie(keyword);
		System.out.println("\nsearchMovie test1:");
		
		if (movies1.isEmpty()) {
			System.out.println("Movie does not existed\n");
		} else {
			for (int i = 0; i < movies1.size(); i++) {
				System.out.println(movies1.get(i).getMovieId() + "find\n");
			}
		}
			
			List<Movie> movies2 = moviedao.searchMovie("haha");
			System.out.println("\nsearchMovie test2:");
			if (movies2.isEmpty()) {
				System.out.println("Movie does not existed\n");
			} else {
				for (int i = 0; i < movies2.size(); i++) {
					System.out.println(movies2.get(i).getMovieId() + "find\n");
				}
			}
		}
	
	//@Test
	public void addMovieTimeTest() {
		String date = "2017/6/9";
		String startTime = "12:00";
		String endTime = "15:00";
		Integer movieId = 5;
		String movieType = "动作";
		Integer theaterId = 2;
		String price = "100";
		String hallName = "3号厅";
		
		String message = moviedao.addMovieTime(date, startTime, endTime, movieId, movieType, theaterId, price, hallName);
		System.out.println("\naddMovie in test:");
	
		if (message != null) {
			System.out.println("MovieTime has existed\n");
		} else {
			System.out.println("MovieTime add success\n");
		}
		
	}
	
	@Test
	public void getMovieTimeTest() {
		String movieid = "4";
		String theaterid = "1";
		String date = "2017/6/9";
		
		List<MovieTime> movietimes = moviedao.getMovieTime(theaterid, movieid, date);
		System.out.println("\ngetMovieTime in test:");
		
		if (movietimes == null||movietimes.isEmpty()) {
			System.out.println("MovieTime does not exist\n");
		} else {
			for (int i = 0 ;i < movietimes.size(); ++i) {
				System.out.println(movietimes.get(i).getMovieTimeId()+"find\n");
			}
		}
		
	}
	
	//@Test
	public void getMoviesByTheaterIdTest() {
	   String id = "2";
	   
	   List<Movie> movies = moviedao.getMoviesByTheaterId(id);
	   System.out.println("\ngetMovieTime in test:");
	   
	   if (movies == null||movies.isEmpty()) {
			System.out.println("Movies does not exist\n");
		} else {
			for (int i = 0 ;i < movies.size(); ++i) {
				System.out.println(movies.get(i).getMovieId()+"find\n");
			}
		}
	}
	
	@Test
	public void getSeatInfoByMovieTimeIdTest() {
	   String id = "5";
	   
	   List<SeatInfo> seats = moviedao.getSeatInfoByMovieTimeId(id);
	   System.out.println("\ngetSeatInfoByMovieTimeIdTest in test:");
	   
	   if (seats == null||seats.isEmpty()) {
			System.out.println("seats does not exist\n");
		} else {
			for (int i = 0 ;i < seats.size(); ++i) {
				System.out.println(seats.get(i).getSeatId()+"find\n");
			}
		}
	}
}
