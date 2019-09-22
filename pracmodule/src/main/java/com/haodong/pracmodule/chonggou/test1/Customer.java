package com.haodong.pracmodule.chonggou.test1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * created by linghaoDo on 2019-09-18
 * <p>
 * description:
 */
public class Customer {
    private String mName;
    private Vector mRentals = new Vector();

    public Customer(String name) {
        this.mName = name;
    }

    public void addRenTal(Rental arg) {
        mRentals.addElement(arg);

    }

    public String getName() {
        return mName;
    }

    public String statement() {
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = mRentals.elements();
        String result = "Rental Record for" + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDayRented() > 2)
                        thisAmount += (each.getDayRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDayRented() * 3;
                    break;
                case Movie.CHILERENS:
                    thisAmount += 1.5;
                    if (each.getDayRented() > 3)
                        thisAmount += (each.getDayRented() - 3) * 1.5;
                    break;
            }
        }
        return result;
    }

}
