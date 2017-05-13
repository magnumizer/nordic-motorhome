package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public abstract class CalculationHandler
{
    public static float calculateBasePrice(LocalDate dropoffDate, LocalDate pickupDate, float pricePerDay, int currentSeason)
    {
        Period daysbetween = Period.between(pickupDate, dropoffDate);
        int days = daysbetween.getDays();
        days = CalculationHandler.clamp(days, 1, 365);

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

    public static float calculateDropoffPrice(String dropoffAddress)
    {
        //how are we supposed to calculate this? just gonna add random value for now

        return dropoffAddress.length() * 100 * Reservation.getTransferCost();
    }

    public static float calculateItemsPrice(HashMap<String, Float> priceMap)
    {
        float price = 0;
        for (Float value : priceMap.values())
        {
            price += value;
        }
        return price;
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
