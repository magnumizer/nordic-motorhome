package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Person
{
    public static ArrayList<Customer> allCustomers = new ArrayList<>();

    private String customerID;

    public Customer(String name, String cpr, LocalDate dateOfBirth, String address, int phoneNumber, String email)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);
        this.customerID = generateID();
    }

    private String generateID()
    {
        //logic for generating unique id goes here
        int val = Customer.allCustomers.size();
        return val + "";
    }

    public String getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(String customerID)
    {
        this.customerID = customerID;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }
}
