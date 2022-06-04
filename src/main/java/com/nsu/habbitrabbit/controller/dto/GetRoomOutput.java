package com.nsu.habbitrabbit.controller.dto;

import java.util.Date;

public class GetRoomOutput {
    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private Date createdAt;
    private Date updatedAt;
    private Date finishedAt;
    private Integer rabbitsForFailure;
    private Integer rabbitsForSuccess;
    public Boolean isFinished;
    public Boolean isClicked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getClicked() {
        return isClicked;
    }

    public void setClicked(Boolean clicked) {
        isClicked = clicked;
    }

    public GetRoomOutput(Long id, String name, String description, Long creatorId, Date createdAt, Date updatedAt, Date finishedAt, Integer rabbitsForFailure, Integer rabbitsForSuccess, Boolean isFinished, Boolean isClicked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.finishedAt = finishedAt;
        this.rabbitsForFailure = rabbitsForFailure;
        this.rabbitsForSuccess = rabbitsForSuccess;
        this.isFinished = isFinished;
        this.isClicked = isClicked;
    }



    public GetRoomOutput() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getRabbitsForFailure() {
        return rabbitsForFailure;
    }

    public void setRabbitsForFailure(Integer rabbitsForFailure) {
        this.rabbitsForFailure = rabbitsForFailure;
    }

    public Integer getRabbitsForSuccess() {
        return rabbitsForSuccess;
    }

    public void setRabbitsForSuccess(Integer rabbitsForSuccess) {
        this.rabbitsForSuccess = rabbitsForSuccess;
    }
}
