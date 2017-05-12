package Model;//Magnus Svendsen DAT16i

import java.sql.Date;
import java.util.ArrayList;

public abstract class Employee extends Person
{
    public static ArrayList<Employee> allEmployees = new ArrayList<>();

    private String username;
    private String password;


    public Employee(String name, int cpr, Date dateOfBirth, String address, int phoneNumber, String email)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);
    }


    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


}
