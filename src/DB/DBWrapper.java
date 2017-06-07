package DB;//Thi Binh Minh Cao DAT16i

import Handler.SearchHandler;
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

                Customer customer = new Customer(name, cpr, birthday, address, tlf, email);
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
                    "VALUES (?,?,?,?,?,?);";


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,_name);
            stmt.setString(2,_cpr);
            stmt.setDate(3, Date.valueOf(_birthday));
            stmt.setString(4,_address);
            stmt.setString(5,_tlf);
            stmt.setString(6,_email);
            stmt.executeUpdate();
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
                        BookKeeper bookkeeper = new BookKeeper(name, cpr, birthday, address, tlf, email, username, password);
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
        else if (employee instanceof BookKeeper)
        {
            _position = "Book Keeper";
        }
        String _username = employee.getUsername();
        String _password = employee.getPassword();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `employees` (`name`, `cpr`, `birthday`, `address`, `tlf`, `email`, `position`, `username`, `password`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?);";


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,_name);
            stmt.setString(2,_cpr);
            stmt.setDate(3, Date.valueOf(_birthday));
            stmt.setString(4,_address);
            stmt.setString(5,_tlf);
            stmt.setString(6,_email);
            stmt.setString(7,_position);
            stmt.setString(8,_username);
            stmt.setString(9,_password);

            stmt.executeUpdate();
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
                Customer customer = SearchHandler.getCustomer(rs.getString("customer"));
                Motorhome motorhome = SearchHandler.getMotorhome(rs.getString("motorhome"));
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
                reservation.setReservationID(id);
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

        String _accessories = accessoriesMap.get(Accessory.allAccessories.get("Bike Rack")) + ","
                            + accessoriesMap.get(Accessory.allAccessories.get("Bed Linen")) + ","
                            + accessoriesMap.get(Accessory.allAccessories.get("Child Seat")) + ","
                            + accessoriesMap.get(Accessory.allAccessories.get("Picnic Table")) + ","
                            + accessoriesMap.get(Accessory.allAccessories.get("Chair")) + "";

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `reservations` (`id`, `customer`, `motorhome`, `date`, `pickup`, `dropoff`, `address`, `accessories`, `season`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?);";


            PreparedStatement stmt=con.prepareStatement(sql);

            stmt.setString(1,_id);
            stmt.setString(2,_customerCPR);
            stmt.setString(3,_motorhomeID);
            stmt.setDate(4, Date.valueOf(_reservationDate));
            stmt.setDate(5, Date.valueOf(_pickupDate));
            stmt.setDate(6, Date.valueOf(_dropoffDate));
            stmt.setString(7,_address);
            stmt.setString(8,_accessories);
            stmt.setInt(9,_season);

            stmt.executeUpdate();
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //delete
    public void deleteReservation(Reservation reservation)
    {
        String reservationID = reservation.getReservationID();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "DELETE FROM reservations WHERE id = ?;";

            PreparedStatement prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1, reservationID);
            prepStmt.execute();

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
                String id = rs.getString("id");
                String model = rs.getString("model");
                String brand = rs.getString("brand");
                String size = rs.getString("size");
                float pricePerDay = rs.getFloat("price");
                String status = rs.getString("generalstatus");
                String cleanStatus = rs.getString("cleanstatus");
                String dateOfCheck = rs.getString("dateofcheck");

                Motorhome motorhome = new Motorhome(model, brand, size, pricePerDay);
                motorhome.setMotorhomeID(id);
                motorhome.setStatus(status);
                motorhome.setCleanStatus(cleanStatus);
                if (dateOfCheck != null && !dateOfCheck.equals(""))
                {
                    motorhome.setDateOfCheck(LocalDate.parse(dateOfCheck));
                }
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
        String _status = motorhome.getStatus();
        String _cleanStatus = motorhome.getCleanStatus();
        String _dateOfCheck = motorhome.getDateOfCheck();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `motorhomes` (`id`, `model`, `brand`, `size`, `price`, `generalstatus`, `cleanstatus`, `dateofcheck`) " +
                    "VALUES (?,?,?,?,?,?,?,?);";


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,_id);
            stmt.setString(2,_model);
            stmt.setString(3,_brand);
            stmt.setString(4,_size);
            stmt.setFloat(5,_pricePerDay);
            stmt.setString(6,_status);
            stmt.setString(7,_cleanStatus);
            stmt.setString(8, _dateOfCheck);

            stmt.executeUpdate();
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
                    "VALUES (?,?,?);";


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,_type);
            stmt.setFloat(2,_price);
            stmt.setInt(3,_quantity);

            stmt.executeUpdate();
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


//RENTALS
    //get
    public void getRentalData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM rentals;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String id = rs.getString("id");
                Reservation reservation = SearchHandler.getReservation(rs.getString("reservation"));
                Service service = SearchHandler.getService(rs.getString("service"));
                boolean paid = rs.getBoolean("paid");

                Rental rental = new Rental(reservation);
                rental.setRentalID(id);
                rental.setService(service);
                rental.setPaidByCustomer(paid);

                Rental.allRentals.add(rental);
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //set
    public void updateRental(Rental rental)
    {
        String _id = rental.getRentalID();
        String _reservationID = rental.getReservation().getReservationID();
        String _serviceID = null;
        if (rental.getService() != null)
        {
            _serviceID = rental.getService().getServiceID();
        }
        int _paid = rental.isPaidByCustomer() ? 1 : 0;

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `rentals` (`id`, `reservation`, `service`, `paid`) " +
                    "VALUES (?,?,?,?);";


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,_id);
            stmt.setString(2,_reservationID);
            stmt.setString(3,_serviceID);
            stmt.setInt(4,_paid);

            stmt.executeUpdate();
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //delete
    public void deleteRental(Rental rental )
    {
        String rentalID = rental.getRentalID();

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "DELETE FROM rentals WHERE id = ?;";

            PreparedStatement prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1, rentalID);
            prepStmt.execute();

            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


//SERVICES
    //get
    public void getServiceData()
    {
        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "SELECT * FROM services;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String id = rs.getString("id");
                String title = rs.getString("title");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                LocalDate date = rs.getDate("date").toLocalDate();

                Service service = new Service(title, price, description, date);
                service.setServiceID(id);
                Service.allServices.add(service);
            }

            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //set
    public void updateService(Service service)
    {
        String _id = service.getServiceID();
        String _title = service.getServiceTitle();
        float _price = service.getPrice();
        String _description = service.getDescription();
        Date _date = Date.valueOf(service.getServiceDate());

        Connection con = null;
        try
        {
            con = DBConn.getConn();

            String sql = "REPLACE INTO `services` (`id`, `title`, `price`, `description`, `date`) " +
                    "VALUES (?,?,?,?,?);";


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,_id);
            stmt.setString(2,_title);
            stmt.setFloat(3,_price);
            stmt.setString(4,_description);
            stmt.setDate(5,_date);

            stmt.executeUpdate();
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
