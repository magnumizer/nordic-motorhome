package Model;//Magnus Svendsen DAT16i

import Handler.CalculationHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Reservation
{
    public static ArrayList<Reservation> allReservations = new ArrayList<>();

    public static float getTransferCost()
    {
        return transferCost;
    }

    private String reservationID;
    private Customer customer;
    private Motorhome motorhome;
    private LocalDate reservationDate;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;
    private String dropoffAddress = "Nordic Motorhome Office";
    private HashMap<Accessory, Integer> accessories = new HashMap<>();

    private int currentSeason;
    private static final float transferCost = 0.70f;

    public Reservation(Customer customer, Motorhome motorhome, LocalDate reservationDate, LocalDate pickupDate, LocalDate dropoffDate, String dropoffAddress, int currentSeason)
    {
        this.customer = customer;
        this.motorhome = motorhome;
        this.reservationDate = reservationDate;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.dropoffAddress = dropoffAddress;
        this.currentSeason = currentSeason;
    }


    public String generateID()
    {
        //logic for generating unique id goes here
        int val = 0;

        for (Reservation reservation : Reservation.allReservations)
        {
            if (val <= Integer.parseInt(reservation.getReservationID()))
            {
                val++;
            }
            else
            {
                break;
            }
        }

        return val + "";
    }

    public String getReservationID()
    {
        return reservationID;
    }

    public void setReservationID(String reservationID)
    {
        this.reservationID = reservationID;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Motorhome getMotorhome()
    {
        return motorhome;
    }

    public void setMotorhome(Motorhome motorhome)
    {
        this.motorhome = motorhome;
    }

    public LocalDate getReservationDate()
    {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate)
    {
        this.reservationDate = reservationDate;
    }

    public LocalDate getPickupDate()
    {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate)
    {
        this.pickupDate = pickupDate;
    }

    public LocalDate getDropoffDate()
    {
        return dropoffDate;
    }

    public void setDropoffDate(LocalDate dropoffDate)
    {
        this.dropoffDate = dropoffDate;
    }

    public String getDropoffAddress()
    {
        return dropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress)
    {
        this.dropoffAddress = dropoffAddress;
    }

    public HashMap<Accessory, Integer> getAccessories()
    {
        return accessories;
    }

    public void setAccessories(HashMap<Accessory, Integer> accessories)
    {
        this.accessories = accessories;
    }

    public int getCurrentSeason()
    {
        return this.currentSeason;
    }

    public float getTotalPrice()
    {
        return CalculationHandler.calculateBasePrice(getPickupDate(), getDropoffDate(), getMotorhome().getPricePerDay(), getCurrentSeason())
                + CalculationHandler.calculateAccessorySum(getAccessories())
                + ((this.getDropoffAddress().equals("Nordic Motorhome Office")) ? 0 : CalculationHandler.calculateDropoffPrice(getDropoffAddress()));
    }
}
