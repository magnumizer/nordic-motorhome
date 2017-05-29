package Handler;//Magnus Svendsen DAT16i

import Model.*;

import java.util.ArrayList;

public abstract class SearchHandler
{

    //region Lists
    public static ArrayList<Employee> findEmployee(String searchValue)
    {
        ArrayList<Employee> foundEmployees = new ArrayList<>();
        String searchString = searchValue.toLowerCase();

        for (Employee employee : Employee.allEmployees)
        {
            if (employee.getName().toLowerCase().contains(searchString))
            {
                foundEmployees.add(employee);
                continue;
            }
            if (employee.getCpr().toLowerCase().contains(searchString))
            {
                foundEmployees.add(employee);
                continue;
            }
            if (employee.getDateOfBirth().toString().contains(searchString))
            {
                foundEmployees.add(employee);
                continue;
            }
            if (employee.getAddress().toLowerCase().contains(searchString))
            {
                foundEmployees.add(employee);
                continue;
            }
            if (String.valueOf(employee.getPhoneNumber()).contains(searchString))
            {
                foundEmployees.add(employee);
                continue;
            }
            if (employee.getEmail().toLowerCase().contains(searchString))
            {
                foundEmployees.add(employee);
                continue;
            }
            if (employee.getUsername().toLowerCase().contains(searchString))
            {
                foundEmployees.add(employee);
                continue;
            }
        }

        return foundEmployees;

    }

    public static ArrayList<Customer> findCustomer(String searchValue)
    {
        ArrayList<Customer> foundCustomers = new ArrayList<>();
        String searchString = searchValue.toLowerCase();

        for (Customer customer : Customer.allCustomers)
        {
            if (customer.getName().toLowerCase().contains(searchString))
            {
                foundCustomers.add(customer);
                continue;
            }
            if (customer.getCpr().toLowerCase().contains(searchString))
            {
                foundCustomers.add(customer);
                continue;
            }
            if (customer.getDateOfBirth().toString().contains(searchString))
            {
                foundCustomers.add(customer);
                continue;
            }
            if (customer.getAddress().toLowerCase().contains(searchString))
            {
                foundCustomers.add(customer);
                continue;
            }
            if (String.valueOf(customer.getPhoneNumber()).contains(searchString))
            {
                foundCustomers.add(customer);
                continue;
            }
            if (customer.getEmail().toLowerCase().contains(searchString))
            {
                foundCustomers.add(customer);
                continue;
            }
        }

        return foundCustomers;

    }

    public static ArrayList<Reservation> findReservation(String searchValue)
    {
        ArrayList<Reservation> foundReservations = new ArrayList<>();
        String searchString = searchValue.toLowerCase();

        for (Reservation reservation : Reservation.allReservations)
        {
            if (reservation.getReservationID().toLowerCase().contains(searchString))
            {
                foundReservations.add(reservation);
                continue;
            }
            if (reservation.getMotorhome().toString().toLowerCase().contains(searchString))
            {
                foundReservations.add(reservation);
                continue;
            }
            if (reservation.getCustomer().toString().toLowerCase().contains(searchString))
            {
                foundReservations.add(reservation);
                continue;
            }
            if (reservation.getReservationDate().toString().contains(searchString))
            {
                foundReservations.add(reservation);
                continue;
            }
            if (reservation.getPickupDate().toString().contains(searchString))
            {
                foundReservations.add(reservation);
                continue;
            }
            if (reservation.getDropoffDate().toString().contains(searchString))
            {
                foundReservations.add(reservation);
                continue;
            }
            if (String.valueOf(reservation.getTotalPrice()).contains(searchString))
            {
                foundReservations.add(reservation);
                continue;
            }
        }

        return foundReservations;

    }

    public static ArrayList<Motorhome> findMotorhome(String searchValue)
    {
        ArrayList<Motorhome> foundMotorhomes = new ArrayList<>();
        String searchString = searchValue.toLowerCase();

        for (Motorhome motorhome : Motorhome.allMotorhomes)
        {
            if (String.valueOf(motorhome.getMotorhomeID()).contains(searchString))
            {
                foundMotorhomes.add(motorhome);
                continue;
            }
            if (motorhome.getModel().toLowerCase().contains(searchString))
            {
                foundMotorhomes.add(motorhome);
                continue;
            }
            if (motorhome.getBrand().toLowerCase().contains(searchString))
            {
                foundMotorhomes.add(motorhome);
                continue;
            }
            if (motorhome.getSize().toLowerCase().contains(searchString))
            {
                foundMotorhomes.add(motorhome);
                continue;
            }
            if (String.valueOf(motorhome.getPricePerDay()).contains(searchString))
            {
                foundMotorhomes.add(motorhome);
                continue;
            }
            if (motorhome.getStatus().toLowerCase().contains(searchString))
            {
                foundMotorhomes.add(motorhome);
                continue;
            }
            if (motorhome.getDateOfCheck().contains(searchString))
            {
                foundMotorhomes.add(motorhome);
                continue;
            }
        }

        return foundMotorhomes;

    }

    public static ArrayList<Accessory> findAccessory(String searchValue)
    {
        ArrayList<Accessory> foundAccessories = new ArrayList<>();
        String searchString = searchValue.toLowerCase();

        for (Accessory accessory : Accessory.allAccessories.values())
        {
            if (accessory.getType().toLowerCase().contains(searchString))
            {
                foundAccessories.add(accessory);
                continue;
            }
            if (String.valueOf(accessory.getPrice()).contains(searchString))
            {
                foundAccessories.add(accessory);
                continue;
            }
            if (String.valueOf(accessory.getQuantity()).contains(searchString))
            {
                foundAccessories.add(accessory);
                continue;
            }
        }

        return foundAccessories;

    }

    public static ArrayList<Rental> findRental(String searchValue)
    {
        ArrayList<Rental> foundRentals = new ArrayList<>();
        String searchString = searchValue.toLowerCase();

        for (Rental rental : Rental.allRentals)
        {
            if (rental.getRentalID().toLowerCase().contains(searchString))
            {
                foundRentals.add(rental);
                continue;
            }
            if (rental.getCustomerName().toLowerCase().contains(searchString))
            {
                foundRentals.add(rental);
                continue;
            }
            if (rental.getMotorhomeName().toLowerCase().contains(searchString))
            {
                foundRentals.add(rental);
                continue;
            }
            if (rental.getServiceName().toLowerCase().contains(searchString))
            {
                foundRentals.add(rental);
                continue;
            }
            if (rental.getServiceDate().contains(searchString))
            {
                foundRentals.add(rental);
                continue;
            }
        }

        return foundRentals;

    }
    //endregion


    //region Single
    public static Customer getCustomer(String cpr)
    {
        for (Customer customer : Customer.allCustomers)
        {
            if (customer.getCpr().equals(cpr))
            {
                return customer;
            }
        }
        return null;
    }

    public static Motorhome getMotorhome(String id)
    {
        for (Motorhome motorhome : Motorhome.allMotorhomes)
        {
            if (motorhome.getMotorhomeID().equals(id))
            {
                return motorhome;
            }
        }
        return null;
    }

    public static Reservation getReservation(String id)
    {
        for (Reservation reservation : Reservation.allReservations)
        {
            if (reservation.getReservationID().equals(id))
            {
                return reservation;
            }
        }
        return null;
    }

    public static Service getService(String id)
    {
        for (Service service : Service.allServices)
        {
            if (service.getServiceID().equals(id))
            {
                return service;
            }
        }
        return null;
    }
    //endregion

}
