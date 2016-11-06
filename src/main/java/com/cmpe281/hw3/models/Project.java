package com.cmpe281.hw3.models;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.*;

/**
 * Harish Kumar K V
 */
@Entity
@Table(name = "project")
@XmlRootElement(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "budget", nullable = false)
    private Double budget;

    public Project()    {}

    public Project(Integer id, String name, Double budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public Integer getId()  {
        return id;
    }

    public void setId(Integer id)   {
        this.id = id;
    }

    public String getName()   {
        return name;
    }

    public void setName(String name)   {
        this.name = name;
    }

    public Double getBudget () {
        return budget;
    }

    public void setBudget (Double budget) {
        this.budget = budget;
    }
}
