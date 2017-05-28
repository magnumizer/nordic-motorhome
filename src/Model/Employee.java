package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Employee extends Person
{
    public static ArrayList<Employee> allEmployees = new ArrayList<>();

    public static boolean checkIfUserExists(String username)
    {
        for (Employee employee : Employee.allEmployees)
        {
            if (employee.getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }

    private String username;
    private String password;


    public Employee(String name, String cpr, LocalDate dateOfBirth, String address, String phoneNumber, String email, String username, String password)
    {
        super(name, cpr, dateOfBirth, address, phoneNumber, email);
        this.username = username;
        this.password = password;
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
