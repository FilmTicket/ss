package Beans;

import java.util.ArrayList;

/**
 * Created by Chen on 2017/6/7.
 */
public class MovieData {
    private Boolean hasNext;
    private ArrayList<MovieBean> movies;

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public ArrayList<MovieBean> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieBean> movies) {
        this.movies = movies;
    }
}
