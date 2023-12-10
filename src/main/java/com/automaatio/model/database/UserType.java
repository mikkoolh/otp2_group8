package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * The UserType class represents a UserType entity that is stored in the database.
 *
 * @author Nikita Nossenko
 * @version 1.0
 */

@Entity
@Table(name = "userType")
public class UserType {
    /**
     * The unique identifier for the userType.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTypeID")
    private int userTypeID;

    /**
     * The description of user type.
     */
    @Column(name = "description")
    private String description;

    /**
     * Default constructor for creating a new userType instance.
     */
    public UserType() {}

    /**
     * Constructs a new UserType with the specified description.
     *
     * @param description The description of the user type.
     */
    public UserType(String description) {
        this.description = description;
    }

    /**
     * Returns the user type description
     * @return The user type description
     */
    @Override
    public String toString() {
        return description;
    }
}