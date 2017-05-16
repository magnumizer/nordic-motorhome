package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;

public class Admin extends Employee
{

    public Admin(String name, String cpr, LocalDate dateOfBirth, String address, int phoneNumber, String email, String username, String password)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email, username, password);
    }

}
