package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

public class Service
{
    public static ArrayList<Service> allServices = new ArrayList<>();

    String serviceID;
    String serviceTitle;
    float price;
    String description;
    LocalDate serviceDate;

    public Service(String serviceTitle, float price, String description, LocalDate serviceDate)
    {
        this.serviceID = allServices.size() + "";
        this.serviceTitle = serviceTitle;
        this.price = price;
        this.description = description;
        this.serviceDate = serviceDate;
    }

    public String getServiceID()
    {
        return serviceID;
    }

    public void setServiceID(String serviceID)
    {
        this.serviceID = serviceID;
    }

    public String getServiceTitle()
    {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle)
    {
        this.serviceTitle = serviceTitle;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getServiceDate()
    {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate)
    {
        this.serviceDate = serviceDate;
    }

    @Override
    public String toString()
    {
        return this.serviceTitle;
    }
}
