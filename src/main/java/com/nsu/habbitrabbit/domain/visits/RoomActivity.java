package com.nsu.habbitrabbit.domain.visits;

import java.util.Date;

public class RoomActivity {
    private Long roomId;
    private int count;
    private Date lastVisitAt;
    private boolean isAdmin;

    public RoomActivity() {
    }

    public RoomActivity(final Long roomId, final int count, final Date lastVisitAt, final boolean isAdmin) {
        this.roomId = roomId;
        this.count = count;
        this.lastVisitAt = lastVisitAt;
        this.isAdmin = isAdmin;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getLastVisitAt() {
        return lastVisitAt;
    }

    public void setLastVisitAt(Date lastVisitAt) {
        this.lastVisitAt = lastVisitAt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
