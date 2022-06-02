package com.nsu.habbitrabbit.controller.dto;

import java.util.Date;

public class CreateRoomOutput {
    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private Date createdAt;
    private Date updatedAt;
    private Date finishedAt;
    private Integer rabbitsForFailure;
    private Integer rabbitsForSuccess;
    private String error;

    public CreateRoomOutput(
            final Long id,
            final String name,
            final String description,
            final Long creatorId,
            final Date createdAt,
            final Date updatedAt,
            final Date finishedAt,
            final Integer rabbitsForFailure,
            final Integer rabbitsForSuccess,
            final String error
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.finishedAt = finishedAt;
        this.rabbitsForFailure = rabbitsForFailure;
        this.rabbitsForSuccess = rabbitsForSuccess;
        this.error = error;
    }

    public CreateRoomOutput(
            final Long id,
            final String name,
            final String description,
            final Long creatorId,
            final Date createdAt,
            final Date updatedAt,
            final Date finishedAt,
            final Integer rabbitsForFailure,
            final Integer rabbitsForSuccess
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.finishedAt = finishedAt;
        this.rabbitsForFailure = rabbitsForFailure;
        this.rabbitsForSuccess = rabbitsForSuccess;
    }

    public CreateRoomOutput(String error) {
        this.error = error;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
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

    public void setCreatorId(final Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(final Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getRabbitsForFailure() {
        return rabbitsForFailure;
    }

    public void setRabbitsForFailure(final Integer rabbitsForFailure) {
        this.rabbitsForFailure = rabbitsForFailure;
    }

    public Integer getRabbitsForSuccess() {
        return rabbitsForSuccess;
    }

    public void setRabbitsForSuccess(final Integer rabbitsForSuccess) {
        this.rabbitsForSuccess = rabbitsForSuccess;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
