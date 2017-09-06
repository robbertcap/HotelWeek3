package com.capgemini.controller;

import com.capgemini.exception.NotFoundException;
import com.capgemini.model.Room;
import com.capgemini.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room/")
public class RoomController {


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Room> getAll() {

        return RoomRepository.rooms;
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Room get(@PathVariable long id) {

        for (Room room : RoomRepository.rooms) {
            if (room.getId() == id) {
                return room;
            }
        }


        throw new NotFoundException();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void create(@RequestBody Room room) {
        RoomRepository.rooms.add(room);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Room updatedRoom) {

        for (Room room : RoomRepository.rooms) {
            if (room.getId() == id) {
                room.setRoomType(updatedRoom.getRoomType());
                return;
            }
        }


        throw new NotFoundException();
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        for (Room room : RoomRepository.rooms) {
            if (room.getId() == id) {
                RoomRepository.rooms.remove(room);
                return;
            }
        }


        throw new NotFoundException();
    }
}
