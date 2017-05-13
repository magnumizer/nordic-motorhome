package Model;//Magnus Svendsen DAT16i

import java.time.LocalDate;

public abstract class Person
{
    private String name;
    private String cpr;
    private LocalDate dateOfBirth;
    private String address;
    private int phoneNumber;
    private String email;

    public Person(String name, String cpr, LocalDate dateOfBirth, String address, int phoneNumber, String email)
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

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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
