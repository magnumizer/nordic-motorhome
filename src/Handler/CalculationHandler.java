package Handler;//Magnus Svendsen DAT16i

import Model.Accessory;
import Model.Reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public abstract class CalculationHandler
{
    public static float calculateBasePrice(LocalDate pickupDate, LocalDate dropoffDate, float pricePerDay, int currentSeason)
    {
        int days = calculateDaysBetweenDates(pickupDate, dropoffDate);

        float multiplier;

        switch (currentSeason)
        {
            case 1:
                multiplier = 1.3f;
                break;
            case 2:
                multiplier = 1.6f;
                break;
            default:
                multiplier = 1.0f;
                break;
        }

        return (days * (pricePerDay * multiplier));
    }

    public static int calculateDaysBetweenDates(LocalDate date1, LocalDate date2)
    {
        return (int)ChronoUnit.DAYS.between(date1, date2);
    }

    public static float calculateDropoffPrice(String dropoffAddress)
    {
        //how are we supposed to calculate this? just gonna add random value for now

        return 100 * Reservation.getTransferCost();
    }

    public static float calculateAccessorySum(HashMap<Accessory, Integer> accessories)
    {
        float price = 0;

        for (Accessory accessory : accessories.keySet())
        {
            price += accessory.getPrice() * accessories.get(accessory);
        }

        return price;
    }

    //Alisa-Nadia Sarb
    public static float calculateCancellationFee(Reservation reservation)
    {
        int days = CalculationHandler.calculateDaysBetweenDates(LocalDate.now(), reservation.getPickupDate());
        float price = reservation.getTotalPrice();
        float fine = 0;

        if (days >= 50)
        {
            fine = (20*price)/100;

            if (fine < 200f)
            {
                fine = 200f;
            }
        }
        else if (days >= 15 && days <= 49)
        {
            fine = (50*price)/100;
        }
        else if (days < 15 && days >= 1)
        {
            fine = (80*price)/100;
        }
        else if (days == 0)
        {
            fine = (95*price)/100;
        }

        return fine;

    }

    public static float clamp(float val, float min, float max)
    {
        return Math.max(min, Math.min(max, val));
    }

    public static int clamp(int val, int min, int max)
    {
        return Math.max(min, Math.min(max, val));
    }
}
