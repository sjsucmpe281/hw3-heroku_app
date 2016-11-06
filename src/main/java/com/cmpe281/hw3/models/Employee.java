package com.cmpe281.hw3.models;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.*;

/**
 * Harish Kumar K V
 */
@Entity
@Table(name = "employee")
@XmlRootElement(name="employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    public Employee ()  {}

    public Employee (Integer id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }
}
