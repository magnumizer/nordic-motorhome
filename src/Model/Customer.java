package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Person
{
    public static ArrayList<Customer> allCustomers = new ArrayList<>();

    public static Customer getCustomer(String cpr)
    {
        for (Customer customer : Customer.allCustomers)
        {
            if (customer.getCpr().equals(cpr))
            {
                return customer;
            }
        }
        return null;
    }

    public Customer(String name, String cpr, LocalDate dateOfBirth, String address, String phoneNumber, String email)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);

    }

    @Override
    public String toString()
    {
        return this.getName();
    }
}
