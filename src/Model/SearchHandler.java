package Model;//Magnus Svendsen DAT16i

import java.util.ArrayList;

public abstract class SearchHandler
{
    public static ArrayList<Rental> searchRentals(String searchValue)
    {
        ArrayList<Rental> foundRentals = new ArrayList<>();

        for (Rental rental : Rental.allRentals)
        {
            if (rental.getRentalID().equals(searchValue))
            {
                foundRentals.add(rental);
                break; //breaking here because id is unique
            }
            if (rental.getCustomerName().equals(searchValue))
            {
                foundRentals.add(rental);
                continue;
            }
            if (rental.getMotorhomeName().equals(searchValue))
            {
                foundRentals.add(rental);
                continue;
            }
            if (rental.getServiceName().equals(searchValue))
            {
                foundRentals.add(rental);
                continue;
            }
        }

        return foundRentals;

    }
}
