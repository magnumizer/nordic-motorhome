package DB;//Magnus Svendsen DAT16i

import Model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class DBWrapper
{

//CUSTOMER
    //get
    public void getCustomerData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM customers;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String name = rs.getString("name");
                String cpr = rs.getString("cpr");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                String email = rs.getString("email");
                String address = rs.getString("address");
                String tlf = rs.getString("tlf");

                Customer customer = new Customer(name, cpr, birthday, email, address, tlf);
                Customer.allCustomers.add(customer);
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //set
    public void updateCustomer(Customer customer)
    {
        String _name = customer.getName();
        String _cpr = customer.getCpr();
        LocalDate _birthday = customer.getDateOfBirth();
        String _address = customer.getAddress();
        String _tlf = customer.getPhoneNumber();
        String _email = customer.getEmail();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `customers` (`name`, `cpr`, `birthday`, `address`, `tlf`, `email`) " +
                    "VALUES ('" + _name + "', '" + _cpr + "', '" + _birthday + "', '" + _address + "', '" + _tlf + "', '" + _email + "');";


            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



//EMPLOYEE
    //get
    public void getEmployeeData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM employees;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String name = rs.getString("name");
                String cpr = rs.getString("cpr");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                String address = rs.getString("address");
                String tlf = rs.getString("tlf");
                String email = rs.getString("email");
                String position = rs.getString("position");
                String username = rs.getString("username");
                String password = rs.getString("password");

                switch (position)
                {
                    case "Admin":
                        Admin admin = new Admin(name, cpr, birthday, address, tlf, email, username, password);
                        Employee.allEmployees.add(admin);
                        break;
                    case "Sales Assistant":
                        SalesAssistant salesAssistant = new SalesAssistant(name, cpr, birthday, address, tlf, email, username, password);
                        Employee.allEmployees.add(salesAssistant);
                        break;
                    case "Auto Mechanic":
                        AutoMechanic autoMechanic = new AutoMechanic(name, cpr, birthday, address, tlf, email, username, password);
                        Employee.allEmployees.add(autoMechanic);
                        break;
                    case "Cleaning Staff":
                        CleaningStaff cleaningStaff = new CleaningStaff(name, cpr, birthday, address, tlf, email, username, password);
                        Employee.allEmployees.add(cleaningStaff);
                        break;
                    case "Book Keeper":
                        Admin bookkeeper = new Admin(name, cpr, birthday, address, tlf, email, username, password);
                        Employee.allEmployees.add(bookkeeper);
                        break;
                    default:
                        System.out.println("Error in parsing position from Staff table.");
                        break;
                }
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //set
    public void updateEmployee(Employee employee)
    {
        String _name = employee.getName();
        String _cpr = employee.getCpr();
        LocalDate _birthday = employee.getDateOfBirth();
        String _address = employee.getAddress();
        String _tlf = employee.getPhoneNumber();
        String _email = employee.getEmail();
        String _position = "";

        if (employee instanceof Admin)
        {
            _position = "Admin";
        }
        else if (employee instanceof SalesAssistant)
        {
            _position = "Sales Assistant";
        }
        else if (employee instanceof AutoMechanic)
        {
            _position = "Auto Mechanic";
        }
        else if (employee instanceof CleaningStaff)
        {
            _position = "Cleaning Staff";
        }
//        else if (employee instanceof BookKeeper)
//        {
//            _position = "Book Keeper";
//        }
        String _username = employee.getUsername();
        String _password = employee.getPassword();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `employees` (`name`, `cpr`, `birthday`, `address`, `tlf`, `email`, `position`, `username`, `password`) " +
                    "VALUES ('" + _name + "', '" + _cpr + "', '" + _birthday + "', '" + _address + "', '" + _tlf + "', '" + _email + "', '" + _position + "', '" + _username + "', '" + _password + "');";


            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


//RESERVATION
    //get
    public void getReservationData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM reservations;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String id = rs.getString("id");
                Customer customer = Customer.getCustomer(rs.getString("customer"));
                Motorhome motorhome = Motorhome.getMotorhome(rs.getString("motorhome"));
                LocalDate reservationDate = rs.getDate("date").toLocalDate();
                LocalDate pickupDate = rs.getDate("pickup").toLocalDate();
                LocalDate dropoffDate = rs.getDate("dropoff").toLocalDate();
                String address = rs.getString("address");
                String accessories = rs.getString("accessories");
                int season = rs.getInt("season");

                HashMap<Accessory, Integer> accessoriesMap = new HashMap<>();
                String[] split = accessories.split(",");
                accessoriesMap.put(Accessory.allAccessories.get("Bike Rack"), Integer.parseInt(split[0]));
                accessoriesMap.put(Accessory.allAccessories.get("Bed Linen"), Integer.parseInt(split[1]));
                accessoriesMap.put(Accessory.allAccessories.get("Child Seat"), Integer.parseInt(split[2]));
                accessoriesMap.put(Accessory.allAccessories.get("Picnic Table"), Integer.parseInt(split[3]));
                accessoriesMap.put(Accessory.allAccessories.get("Chair"), Integer.parseInt(split[4]));


                Reservation reservation = new Reservation(customer, motorhome, reservationDate, pickupDate, dropoffDate, address, season);
                reservation.setAccessories(accessoriesMap);
                Reservation.allReservations.add(reservation);

            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //set
    public void updateReservation(Reservation reservation)
    {
        String _id = reservation.getReservationID();
        String _customerCPR = reservation.getCustomer().getCpr();
        String _motorhomeID = reservation.getMotorhome().getMotorhomeID();
        LocalDate _reservationDate = reservation.getReservationDate();
        LocalDate _pickupDate = reservation.getPickupDate();
        LocalDate _dropoffDate = reservation.getDropoffDate();
        String _address = reservation.getDropoffAddress();
        int _season = reservation.getCurrentSeason();

        HashMap<Accessory, Integer> accessoriesMap = reservation.getAccessories();
        String _accessories = accessoriesMap.get("Bike Rack") + "," + accessoriesMap.get("Bed Linen") + "," + accessoriesMap.get("Child Seat") + "," + accessoriesMap.get("Picnic Table") + "," + accessoriesMap.get("Chair") + "";

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `reservations` (`id`, `customer`, `motorhome`, `date`, `pickup`, `dropoff`, `address`, `accessories`, `season`) " +
                    "VALUES ('" + _id + "', '" + _customerCPR + "', '" + _motorhomeID + "', '" + _reservationDate + "', '" + _pickupDate + "', '" + _dropoffDate + "', '" + _address + "', '" + _accessories + "', '" + _season + "');";


            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



//MOTORHOME
    //get
    public void getMotorhomeData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM motorhomes;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String model = rs.getString("model");
                String brand = rs.getString("brand");
                String size = rs.getString("size");
                float pricePerDay = rs.getFloat("price");

                Motorhome motorhome = new Motorhome(model, brand, size, pricePerDay);
                Motorhome.allMotorhomes.add(motorhome);
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //set
    public void updateMotorhome(Motorhome motorhome)
    {
        String _id = motorhome.getMotorhomeID();
        String _model = motorhome.getModel();
        String _brand = motorhome.getBrand();
        String _size = motorhome.getSize();
        float _pricePerDay = motorhome.getPricePerDay();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `motorhomes` (`id`, `model`, `brand`, `size`, `price`) " +
                    "VALUES ('" + _id + "', '" + _model + "', '" + _brand + "', '" + _size + "', '" + _pricePerDay + "');";


            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


//ACCESSORIES
    //get
    public void getAccessoryData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM accessories;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String type = rs.getString("type");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");

                Accessory accessory = new Accessory(type, price, quantity);
                Accessory.allAccessories.put(type, accessory);
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //set
    public void updateAccessory(Accessory accessory)
    {
        String _type = accessory.getType();
        float _price = accessory.getPrice();
        int _quantity = accessory.getQuantity();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `accessories` (`type`, `price`, `quantity`) " +
                    "VALUES ('" + _type + "', '" + _price + "', '" + _quantity + "');";


            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}








