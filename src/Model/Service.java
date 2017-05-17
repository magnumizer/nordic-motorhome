package Model;//Magnus Svendsen DAT16i

public class Service
{
    String serviceTitle;
    float price;
    String description;

    public Service(String serviceTitle, float price, String description)
    {
        this.serviceTitle = serviceTitle;
        this.price = price;
        this.description = description;
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

    @Override
    public String toString()
    {
        return this.serviceTitle;
    }
}
