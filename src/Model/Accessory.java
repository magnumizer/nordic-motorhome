package Model;//Magnus Svendsen DAT16i

import java.util.HashMap;

public class Accessory
{
    public static HashMap<String, Accessory> allAccessories = new HashMap<>();

    String type;
    float price;
    int quantity;

    public Accessory(String type, float price, int quantity)
    {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return this.getType();
    }
}
