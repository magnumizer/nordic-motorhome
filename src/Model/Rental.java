package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

public class Rental
{
    public static ArrayList<Rental> allRentals = new ArrayList<>();

    String rentalID;
    Reservation reservation;
    Service service;
    float totalPrice;
    boolean paidByCustomer;

    public Rental(Reservation reservation)
    {
        this.rentalID = generateID();
        this.reservation = reservation;
    }

    private String generateID()
    {
        //logic for generating unique id goes here
        return "null";
    }

    public String getRentalID()
    {
        return rentalID;
    }


    public Reservation getReservation()
    {
        return reservation;
    }

    public void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public String getServiceName()
    {
        if (this.getService() != null)
        {
            return this.getService().getServiceTitle();
        }
        else
        {
            return "";
        }
    }

    public float getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public boolean isPaidByCustomer()
    {
        return paidByCustomer;
    }

    public String getPaidByCustomer()
    {
        return isPaidByCustomer() ? "PAID" : "NOT PAID";
    }

    public void setPaidByCustomer(boolean paidByCustomer)
    {
        this.paidByCustomer = paidByCustomer;
    }

    public Customer getCustomer()
    {
        return this.getReservation().getCustomer();
    }

    public String getCustomerName()
    {
        return this.getCustomer().getName();
    }

    public String getMotorhomeName()
    {
        return this.getReservation().getMotorhome().getModel();
    }

    public LocalDate getPickupDate()
    {
        return this.getReservation().getPickupDate();
    }

    public LocalDate getDropoffDate()
    {
        return this.getReservation().getDropoffDate();
    }

    public int getDaysOfRental()
    {
        return CalculationHandler.calculateDaysBetweenDates(getPickupDate(), getDropoffDate());
    }

    public float getPrice()
    {
        return this.getReservation().getTotalPrice();
    }

    public float getServicePrice()
    {
        if (this.getService() != null)
        {
            return this.getService().getPrice();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public String toString()
    {
        return this.getRentalID();
    }
}
