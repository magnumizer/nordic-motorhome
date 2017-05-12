package Model;//Magnus Svendsen DAT16i

import java.sql.Date;

public class Admin extends Employee
{

    public Admin(String name, String cpr, Date dateOfBirth, String address, int phoneNumber, String email)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);
    }

}
