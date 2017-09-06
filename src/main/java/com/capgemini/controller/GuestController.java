package com.capgemini.controller;

import com.capgemini.exception.NotFoundException;
import com.capgemini.model.Guest;
import com.capgemini.repository.GuestRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guest/")
public class GuestController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Guest> getAll() {

        return GuestRepository.guests;
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Guest get(@PathVariable long id) {

        for (Guest guest : GuestRepository.guests) {
            if (guest.getId() == id) {
                return guest;
            }
        }


        throw new NotFoundException();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void create(@RequestBody Guest guest) {
        GuestRepository.guests.add(guest);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Guest updatedGuest) {

        for (Guest guest : GuestRepository.guests) {
            if (guest.getId() == id) {

                guest.setFirstName(updatedGuest.getFirstName());
                guest.setLastName(updatedGuest.getLastName());
                guest.setAddress(updatedGuest.getAddress());
                guest.setPostalCode(updatedGuest.getPostalCode());
                guest.setCity(updatedGuest.getCity());
                guest.setEmail(updatedGuest.getEmail());
                guest.setPhoneNumber(updatedGuest.getPhoneNumber());
                guest.setDateOfBirth(updatedGuest.getDateOfBirth());
                return;
            }
        }

        throw new NotFoundException();

    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        for (Guest guest : GuestRepository.guests) {
            if (guest.getId() == id) {

                GuestRepository.guests.remove(guest);
                return;
            }
        }

        throw new NotFoundException();
    }

}
