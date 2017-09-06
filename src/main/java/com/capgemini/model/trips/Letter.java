package com.capgemini.model.trips;


public class Letter
{
    private Tripbox aTripbox;
    private Fleet aFleet;

    public Letter()
    {
        this.aTripbox = new Tripbox();
        this.aFleet = new Fleet();

        for (int i=1; i<11; i=i+1)
        {
            Boat aBoat = new Boat(i);
            aFleet.save(aBoat);
        }
    }

    public int startLakeTrip()
    {
        Boat aBoat;
        int boatNumber;
        Trip aTrip;
        aBoat = this.aFleet.getAvailableBoat();
        if (aBoat == null)
        {
            boatNumber =-1;
        }
        else
        {
            aTrip = aBoat.makeLakeTrip();
            this.aTripbox.save(aTrip);
            boatNumber = aBoat.getNumber();
        }
        return boatNumber;
    }

    public int startRiverTrip()
    {
        Boat aBoat;
        int boatNumber;
        Trip aTrip;
        aBoat = this.aFleet.getAvailableBoat();
        if (aBoat == null)
        {
            boatNumber =-1;
        }
        else
        {
            aTrip = aBoat.makeRiverTrip();
            this.aTripbox.save(aTrip);
            boatNumber = aBoat.getNumber();
        }
        return boatNumber;
    }

    public String endTrip(int number)
    {
        Boat aBoat;
        CGTime aDuration;
        boolean required;
        String aString, aDurationString;
        aBoat = this.aFleet.getBoat(number);
        aDuration = aBoat.endTrip();
        aDurationString = aDuration.toString();
        required = aBoat.isInspectionRequired();
        if (required)
        {
            aBoat.resetInspection();
            aString = "This boat should be inspected. ";
        }
        else
        {
            aString = "This boat should not be inspected. ";
        }
        aString = aDurationString + aString;
        return aString;
    }

    public int getNumberOfEndedTrips()
    {
        int numberEndedTrips;
        numberEndedTrips = this.aTripbox.getNumberOfEndedTrips();
        return numberEndedTrips;
    }

    public CGTime getAverageDuration()
    {
        CGTime averageDuration;
        averageDuration = aTripbox.getAverageDuration();
        return averageDuration;
    }

}
