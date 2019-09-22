package com.haodong.pracmodule.chonggou.test1;

/**
 * created by linghaoDo on 2019-09-18
 * <p>
 * description:
 */
public class Movie {
    public static final int CHILERENS=2;
    public static final int REGULAR=0;
    public static final int NEW_RELEASE=1;
    private String _title;
    private int _priceCode;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
        this._priceCode = _priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int _priceCode) {
        this._priceCode = _priceCode;
    }

    public String get_title() {
        return _title;
    }
}
