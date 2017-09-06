package com.capgemini.controller;

import com.capgemini.exception.NotFoundException;
import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.GuestRepository;
import com.capgemini.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/")
public class BookingController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Booking> getAll() {

        return BookingRepository.bookings;
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Booking get(@PathVariable long id) {

        for (Booking booking : BookingRepository.bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }


        throw new NotFoundException();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void create(@RequestBody Booking booking) {

        Guest guest = null;

        for (Guest g : GuestRepository.guests) {
            if (g.getId() == booking.getGuest().getId()) {
                guest = g;
                break;
            }
        }

        if (guest == null) throw new NotFoundException();

        Room room = null;

        for (Room r : RoomRepository.rooms) {
            if (r.getId() == booking.getRoom().getId()) {
                room = r;
                break;
            }
        }

        if (room == null) throw new NotFoundException();

        booking.setGuest(guest);
        booking.setRoom(room);

        BookingRepository.bookings.add(booking);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Booking updatedBooking) {

        for (Booking booking : BookingRepository.bookings) {
            if (booking.getId() == id) {
                booking.setStart(updatedBooking.getStart());
                booking.setEnd(updatedBooking.getEnd());
                booking.setComments(updatedBooking.getComments());
                return;
            }
        }


        throw new NotFoundException();

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        for (Booking booking : BookingRepository.bookings) {
            if (booking.getId() == id) {
                BookingRepository.bookings.remove(booking);
                return;
            }
        }


        throw new NotFoundException();
    }

}
