package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

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
    private ArrayList<Accessory> accessories = new ArrayList<>();
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
        this.reservationID = generateID();

        //creating rental here for now.. not sure where to instantiate it
        Rental rental = new Rental(this);
        Rental.allRentals.add(rental);
    }


    private String generateID()
    {
        //logic for generating unique id goes here
        return "";
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

    public ArrayList<Accessory> getAccessories()
    {
        return this.accessories;
    }

    public void addAccessory(Accessory accessory)
    {
        this.accessories.add(accessory);
    }

    public void setAccessories(ArrayList<Accessory> accessories)
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
