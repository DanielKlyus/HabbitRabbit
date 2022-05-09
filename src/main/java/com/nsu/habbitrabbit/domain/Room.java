package com.nsu.habbitrabbit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "rooms")

public class Room {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long creatorId;
    private Date createdAt;
    private Date updatedAt;
    private Date finishedAt;
    private Integer rabbitsForFailure;
    private Integer rabbitsForSuccess;

    public Room(){
    }

    public Room(
            final Long id,
            final String name,
            final Long creatorId,
            final Date createdAt,
            final Date updatedAt,
            final Date finishedAt,
            final Integer rabbitsForFailure,
            final Integer rabbitsForSuccess
    ){
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.finishedAt = finishedAt;
        this.rabbitsForFailure = rabbitsForFailure;
        this.rabbitsForSuccess = rabbitsForSuccess;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Long getCreatorId() { return creatorId; }

    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public Date getFinishedAt() { return finishedAt; }

    public void setFinishedAt(Date finishedAt) { this.finishedAt = finishedAt; }

    public Integer getRabbitsForFailure() { return rabbitsForFailure; }

    public void setRabbitsForFailure(Integer rabbitsForFailure) { this.rabbitsForFailure = rabbitsForFailure; }

    public Integer getRabbitsForSuccess() { return rabbitsForSuccess; }

    public void setRabbitsForSuccess(Integer rabbitsForSuccess) { this.rabbitsForSuccess = rabbitsForSuccess; }
}
