package Model;//Magnus Svendsen DAT16i

import java.sql.Date;

public abstract class Person
{
    private String name;
    private int cpr;
    private Date dateOfBirth;
    private String address;
    private int phoneNumber;
    private String email;

    public Person(String name, int cpr, Date dateOfBirth, String address, int phoneNumber, String email)
    {
        this.name = name;
        this.cpr = cpr;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpr() {
        return cpr;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString()
    {
        return "Name: " + this.name +
                "\nCpr: " + this.cpr +
                "\nDate of birth: " + this.dateOfBirth +
                "\nAddress :" + this.address +
                "\nPhone number: " + this.phoneNumber +
                "\nEmail :" + this.email;
    }

}
