package com.capgemini.model.trips;

import com.capgemini.model.trips.Boat;

import java.util.ArrayList;

public class Fleet
{
    private ArrayList boats;

    public Fleet()
    {
        this.boats = new ArrayList();
    }

    public Boat getBoat(int boatNumber)
    {
        int numberBoats;
        Boat currentBoat, aBoat;
        numberBoats = this.boats.size();
        aBoat = null;
        for (int i=0; i<numberBoats; i=i+1)
        {
            currentBoat =(Boat)this.boats.get(i);
            if (currentBoat.hasNumber(boatNumber))
            {
                aBoat = currentBoat;
            }
        }
        return aBoat;
    }

    public void save(Boat aBoat)
    {
        this.boats.add (aBoat);
    }

    public Boat getAvailableBoat()
    {
        int numberBoats;
        Boat anAvailableBoat, aBoat;
        numberBoats = this.boats.size();
        anAvailableBoat = null;
        for (int i=0; i<numberBoats; i=i+1)
        {
            aBoat = (Boat)this.boats.get(i);
            if (aBoat.isAvailable())
            {
                anAvailableBoat = aBoat;
            }
        }
        return anAvailableBoat;
    }
}
