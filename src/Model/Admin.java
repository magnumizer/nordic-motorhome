package Model;//Alisia-Nadia Sarb DAT16i

import java.time.LocalDate;

public class Admin extends Employee
{

    public Admin(String name, String cpr, LocalDate dateOfBirth, String address, String phoneNumber, String email, String username, String password)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email, username, password);
    }

}
