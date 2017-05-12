package Model;//Magnus Svendsen DAT16i

import java.sql.Date;

public class Customer extends Person
{

    private String customerID;

    public Customer(String name, int cpr, Date dateOfBirth, String address, int phoneNumber, String email)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);
        this.customerID = generateID();
    }

    private String generateID()
    {
        //logic for generating unique id goes here
        return "";
    }

    public String getCustomerID()
    {
        return customerID;
    }

}
