package com.nsu.habbitrabbit.controller.dto;

import java.util.ArrayList;
import java.util.Date;

public class CreateRoomInput {
    private String name;
    private String description;
    private Long creatorId;
    private Date finishedAt;
    private Integer rabbitsForFailure;
    private Integer rabbitsForSuccess;
    ArrayList<String> emails;

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

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

    public CreateRoomInput(final String name, final String description, final Long creatorId, final Date finishedAt, final Integer rabbitsForFailure, final Integer rabbitsForSuccess) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.finishedAt = finishedAt;
        this.rabbitsForFailure = rabbitsForFailure;
        this.rabbitsForSuccess = rabbitsForSuccess;
    }

    CreateRoomInput() {}
}
