package com.nsu.habbitrabbit.controller.dto;

import java.util.Date;

public class GetRoomOutput {
    private String name;
    private String description;
    private Long creatorId;
    private Date createdAt;
    private Date updatedAt;
    private Date finishedAt;
    private Integer rabbitsForFailure;
    private Integer rabbitsForSuccess;

    private Boolean isFinished;

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    public GetRoomOutput(String name, String description, Long creatorId, Date createdAt, Date updatedAt, Date finishedAt, Integer rabbitsForFailure, Integer rabbitsForSuccess, Boolean isFinished) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.finishedAt = finishedAt;
        this.rabbitsForFailure = rabbitsForFailure;
        this.rabbitsForSuccess = rabbitsForSuccess;
        this.isFinished = isFinished;
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
