/**
 * @author Jiamin Lua 
 * Property class that contains all the attributes of the property.
 * This class also includes getter and setter methods, and the constructor that 
 * creates a new property listing
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class Property {
    private int year;
    private int month;
    private String town;
    private String flatType;
    private double sqfeet;
    private int remainingLease;
    private double price;

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

    public double getSqfeet() {
        return this.sqfeet;
    }

    public void setSqfeet(double sqfeet) {
        this.sqfeet = sqfeet;
    }

    public int getRemainingLease() {
        return this.remainingLease;
    }

    public void setRemainingLease(int remainingLease) {
        this.remainingLease = remainingLease;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

    /**
     * The toSring method prints out each Property instance in an arrayList
     */

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

    public Property(int year, int month, String town, String flatType, double sqfeet, int remainingLease, double price) {
        this.year = year;
        this.month = month;
        this.town = town.toUpperCase();
        this.flatType = flatType.toUpperCase();
        this.sqfeet = sqfeet;
        this.remainingLease = remainingLease;
        this.price = price;
    }

    /**
     * toFlat method splits each row of data from a .csv file and
     * creates a new property object by creating the instance variables
     * within each Property instance
     */

    public static Property toFlat(String data) {
        String[] col = data.split(",");
        String[] saleDate = col[0].split("-");
        int year = Integer.parseInt(saleDate[0]);
        int month = Integer.parseInt(saleDate[1]);
        String town = col[1];
        String[] rooms = col[2].split(" ");
        String flatType = rooms[0];
        double sqfeet = Double.parseDouble(col[6]);
        String[] yearsLeft = col[9].split(" ");
        int remainingLease = Integer.parseInt(yearsLeft[0]);
        double price = Double.parseDouble(col[10]);
        Property currFlat = new Property(year, month, town, flatType, sqfeet, remainingLease, price);

        return currFlat;
    }

    
}
