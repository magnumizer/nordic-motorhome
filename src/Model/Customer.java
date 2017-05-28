package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Person
{
    public static ArrayList<Customer> allCustomers = new ArrayList<>();


    private int customerID;

    public Customer(String name, String cpr, LocalDate dateOfBirth, String address, int phoneNumber, String email)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);

    }

    @Override
    public String toString()
    {
        return this.getName();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
