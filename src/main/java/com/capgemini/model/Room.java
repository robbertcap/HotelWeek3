package com.capgemini.model;

/**
 * Room class contains the details of a room
 */
public class Room {

    private long id;

    private ERoomType roomType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ERoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(ERoomType roomType) {
        this.roomType = roomType;
    }
}