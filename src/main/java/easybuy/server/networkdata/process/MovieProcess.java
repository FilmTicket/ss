package easybuy.server.networkdata.process;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import easybuy.server.model.Movie;
import easybuy.server.model.PopularMovie;
import easybuy.server.networkdata.beans.MovieBean;
import easybuy.server.networkdata.beans.MovieVO;
import easybuy.server.networkdata.retrofit.RetrofitFactory;
import easybuy.server.service.MovieService;
import rx.functions.Action1;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class MovieProcess {
	
	@Autowired
	private MovieService movieService;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieProcess.class);
	
	@Test
	public void addMovieProcess() {
        final String type = "all";
        final Integer offset = 0;
        final Integer limit = 2000;
        
        String message = null;
        
        final List<Movie> movies = new ArrayList<Movie>();
        final List<PopularMovie> popularMovies = new ArrayList<PopularMovie>();

        RetrofitFactory.getInstance().getMovieService()
                .getAllMovies(type, offset, limit)
                .subscribe(new Action1<MovieVO>() {
                    @Override
                    public void call(MovieVO movieVO) {
                        ArrayList<MovieBean> movieBeans = movieVO.getData().getMovies();
                        for (MovieBean movieBean : movieBeans) {
                        	Movie movie = new Movie(movieBean.getNm(), movieBean.getScm(), movieBean.getImg());
                        	PopularMovie popularMovie = new PopularMovie(movieBean.getNm(), movieBean.getScm(), movieBean.getImg());
                        	
                        	movies.add(movie);
                        	if (popularMovies.size() < 10) {
                        		popularMovies.add(popularMovie);
                        	}
                        }
                    }
                });
        
        message = movieService.addMovies(movies);
        if (message != null) {
        	logger.info(message);
        }
        
        message = movieService.addPopularMovies(popularMovies);
        if (message != null) {
        	logger.info(message);
        }
    }
	
    //@Test
	public void deleteMovieProcess() {
		String message = null;
		
		message = movieService.deleteAllMovies();
		if (message != null) {
        	logger.info(message);
        }
		
		message = movieService.deleteAllPopularMovies();
		if (message != null) {
        	logger.info(message);
        }
	}
}
