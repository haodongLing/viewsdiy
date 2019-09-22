package com.haodong.pracmodule.chonggou.test1;

/**
 * created by linghaoDo on 2019-09-18
 * <p>
 * description:
 */
public class Rental {
    private Movie movie;
    private int dayRented;

    public Rental(Movie movie, int dayRented) {
        this.movie = movie;
        this.dayRented = dayRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDayRented() {
        return dayRented;
    }
}
