package com.nsu.habbitrabbit.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean isActive;
    private Date updatedAt;
    private Date createdAt;
    private Integer countOfRabbits;

    public Player() {
    }

    public Player(final Long id, final String name, final String email, final String password, final boolean isActive, final Date updatedAt, final Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.countOfRabbits = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setActive(final boolean isActive) {
        this.isActive = isActive;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getCountOfRabbits() {
        return countOfRabbits;
    }

    public void setCountOfRabbits(Integer countOfRabbits) {
        this.countOfRabbits = countOfRabbits;
    }
}
