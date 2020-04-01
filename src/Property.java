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
    private String flatType;
    private float sqfeet;
    private int remainingLease;
    private float price;

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

    public String getFlatType() {
        return this.flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public float getSqfeet() {
        return this.sqfeet;
    }

    public void setSqfeet(float sqfeet) {
        this.sqfeet = sqfeet;
    }

    public int getRemainingLease() {
        return this.remainingLease;
    }

    public void setRemainingLease(int remainingLease) {
        this.remainingLease = remainingLease;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public String toString() {
    	ArrayList<String> flat = new ArrayList<String>();
    	flat.add(Integer.toString(year));
        flat.add(Integer.toString(month));
        flat.add(town);
    	flat.add(flatType);
    	flat.add(String.valueOf(sqfeet));
    	flat.add(Integer.toString(remainingLease));
    	flat.add(String.valueOf(price));
    	
    	return flat.toString();
    }

    /**
     * Constructor for property class.
     */

    public Property(int year, int month, String town, String flatType, float sqfeet, int remainingLease, 
        float price) {
        this.year = year;
        this.month = month;
        this.town = town;
        this.flatType = flatType;
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
        String flatType = rooms[0];
        float sqfeet = Float.parseFloat(col[6]);
        String[] yearsLeft = col[9].split(" ");
        int remainingLease = Integer.parseInt(yearsLeft[0]);
        float price = Float.parseFloat(col[10]);
        Property currFlat = new Property(year, month, town, flatType, sqfeet, remainingLease, price);

        return currFlat;
    }

}
