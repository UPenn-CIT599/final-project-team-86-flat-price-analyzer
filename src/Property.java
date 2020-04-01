/**
 * @author Jiamin Lua 
 * Property class that contains all the attributes of the property.
 * This class also includes getter and setter methods, and the constructor that 
 * creates a new property listing
 */

import java.util.*;

public class Property {
    private int year;
    private int month;
    private String town;
    private int numRooms;
    private int sqfeet;
    private int remainingLease;
    private int price;

    /**
     * Getters and setters for Flight class
     */


    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getNumRooms() {
        return this.numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public int getSqfeet() {
        return this.sqfeet;
    }

    public void setSqfeet(int sqfeet) {
        this.sqfeet = sqfeet;
    }

    public int getRemainingLease() {
        return this.remainingLease;
    }

    public void setRemainingLease(int remainingLease) {
        this.remainingLease = remainingLease;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Constructor for property class.
     */

    public Property(int year, int month, String town, int numRooms, int sqfeet, int remainingLease, 
        int price) {
        this.year = year;
        this.month = month;
        this.town = town;
        this.numRooms = numRooms;
        this.sqfeet = sqfeet;
        this.remainingLease = remainingLease;
        this.price = price;
    }

    /**
     * toFlat method splits each row of data from a .csv file and
     * creates a new property object. 
     */

    public static Property toFlat(String data) {
        String[] col = data.split(",");
        String[] saleDate = col[0].split("-");
        int year = Integer.parseInt(saleDate[0]);
        int month = Integer.parseInt(saleDate[1]);
        String town = col[1];
        String[] rooms = col[2].split(" ");
        int numRooms = Integer.parseInt(rooms[0]);
        int sqfeet = Integer.parseInt(col[6]);
        int remainingLease = Integer.parseInt(col[9]);
        int price = Integer.parseInt(col[10]);
        Property currFlat = new Property(year, month, town, numRooms, sqfeet, remainingLease, price);

        return currFlat;
    }

}
