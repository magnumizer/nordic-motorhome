package Model;//Magnus Svendsen DAT16i

import java.sql.Date;

public class SalesAssistant extends Employee
{

    public SalesAssistant(String name, int cpr, Date dateOfBirth, String address, int phoneNumber, String email)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);
    }

}
