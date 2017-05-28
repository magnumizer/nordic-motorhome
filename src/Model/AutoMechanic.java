package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;

public class AutoMechanic extends Employee
{

    public AutoMechanic(String name, String cpr, LocalDate dateOfBirth, String address, String phoneNumber, String email, String username, String password)
    {
        super( name, cpr, dateOfBirth, address, phoneNumber, email, username, password);
    }

}
