package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

public class Motorhome
{
    public static ArrayList<Motorhome> allMotorhomes = new ArrayList<>();

    String motorhomeID;
    String model;
    String brand;
    String size;
    String status = "Available";
    float pricePerDay;
    String cleanStatus = "Clean";
    String dateOfCheck;

    public Motorhome(String model, String brand, String size, float pricePerDay)
    {
        this.model = model;
        this.brand = brand;
        this.size = size;
        this.pricePerDay = pricePerDay;
        this.motorhomeID = Motorhome.allMotorhomes.size() + "";
    }

    public String getMotorhomeID()
    {
        return motorhomeID;
    }

    public void setMotorhomeID(String motorhomeID)
    {
        this.motorhomeID = motorhomeID;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCleanStatus()
    {
        return cleanStatus;
    }

    public void setCleanStatus(String cleanStatus)
    {
        this.cleanStatus = cleanStatus;
    }

    public String getDateOfCheck()
    {
        if (dateOfCheck == null)
        {
            return "";
        }
        else
        {
            return dateOfCheck;
        }
    }

    public void setDateOfCheck(LocalDate localDate)
    {
        this.dateOfCheck = localDate.toString();
    }

    public float getPricePerDay()
    {
        return pricePerDay;
    }

    public void setPricePerDay(float pricePerDay)
    {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString()
    {
        return this.getBrand() + " " + this.getModel();
    }
}
