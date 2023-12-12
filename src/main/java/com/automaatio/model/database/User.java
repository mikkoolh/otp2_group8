package com.automaatio.model.database;

import jakarta.persistence.*;

import java.util.Locale;

/**
 * The User class represents a User entity that is stored in the database.
 *
 * @author Mikko Hänninen, Matleena Kankaanpää
 * @version 1.0
 * @since 15.09.2023
 */

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "selected_picture")
    private int selectedPicture;

    @Column(name = "age")
    private int age;

    @Column(name = "userType")
    private int userType;

    @Column(name = "max_price")
    private double maxPrice;

    @Column(name = "locale")
    private String locale;

    /**
     * Parameterless constructor
     */
    public User() {}

    /**
     * Parameterized constructor
     *
     * @param username      The username of the user
     * @param firstName     The first name of the user
     * @param lastName      The last name of the user
     * @param phoneNumber   The phone number of the user
     * @param email         The email address of the user
     * @param password      The hashed password of the user
     * @param age           The age of the user
     * @param userType      The user type
     */
    public User(String username, String firstName, String lastName, String phoneNumber, String email, String password, int age, int userType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.age = age;
        this.userType = userType;
        this.maxPrice = 30;
        locale = "en_US";
    }

    /**
     * Returns the username of the user
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     * @param username A username to be given to the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the first name of the user
     * @return The first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user
     * @param firstName A first name to be given to the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user
     * @return The last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user
     * @param lastName The last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the phone number of the user
     * @return The phone number of the user
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user
     * @param phoneNumber A phone number to be set for the user
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email address of the user
     * @return The email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user
     * @param email An email address to be set for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the hashed password of the user
     * @return The hashed password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     * @param password A hashed password to be set for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the age of the user
     * @return The age of the user
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user
     * @param age The age to be set for the user
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the maximum electricity price for the user
     * @param price The maximum electricity price to be set for the user
     */
    public void setMaxPrice(double price){
        maxPrice = price;
    }

    /**
     * Returns the maximum electricity price set by the user
     * @return The maximum electricity price set by the user
     */
    public double getMaxPrice(){
        return maxPrice;
    }

    /**
     * Returns an integer which identifies the user's selected profile picture
     * @return An integer which identifies the user's selected profile picture
     */
    public int getSelectedPicture() {
        return selectedPicture;
    }

    /**
     * Sets the user's profile picture identified by an integer
     * @param selectedPicture An integer which identifies the user's profile picture
     */
    public void setSelectedPicture(int selectedPicture) {
        this.selectedPicture = selectedPicture;
    }

    /**
     * Sets the user's selected locale
     * @param locale The locale to be set for the user
     */
    public void setLocale(String locale){
        this.locale = locale;
    }

    /**
     * Returns the user's selected locale
     * @return The user's selected locale
     */
    public Locale getLocale(){
        return new Locale(locale.split("_")[0], locale.split("_")[1]);
    }

    /**
     * Returns the username of the user
     * @return The username of the user
     */
    @Override
    public String toString() {
        return username;
    }
}
