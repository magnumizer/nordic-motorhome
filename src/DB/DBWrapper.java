package DB;//Magnus Svendsen DAT16i

import Model.*;

import java.sql.*;
import java.util.ArrayList;

import static Model.Customer.allCustomers;
import static Model.Employee.allEmployees;
import static Model.Motorhome.allMotorhomes;


public class DBWrapper
{

//CUSTOMER

    public void addCustomer(Customer customer) {


        try {
            Connection conn = DBConn.getConn();

            String query = "INSERT INTO customer(customer_name, customer_cpr, customer_DOB, customer_email, customer_address, customer_tlf ) VALUES (?,?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpr());
            stmt.setDate(3, Date.valueOf(customer.getDateOfBirth()));
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getPhoneNumber());
            stmt.executeUpdate();

            allCustomers.add(customer);

        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    public ArrayList<Customer> getCustomer(){
        allCustomers = new ArrayList<>();

        try {
            String query = "SELECT * FROM `customer`";
            ResultSet rs = getDBcon(query);
            while (rs.next()){
               allCustomers.add(new Customer(rs.getString(1),rs.getString(2),
                       rs.getDate(3).toLocalDate(),rs.getString(4)
                       ,rs.getInt(6),rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return allCustomers;
    }

    public void updateCustomer(Customer customer){




    }

//EMPLOYEE
    public void addEmployee(Employee employee){

        try {
            Connection conn = DBConn.getConn();

            String query = "INSERT INTO staff(id, staff_name, staff_cpr, staff_DOB, staff_email, staff_address, staff_tlf, username,password) VALUES (NULL ,?,?,?,?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getCpr());
            stmt.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getAddress());
            stmt.setInt(6, employee.getPhoneNumber());
            stmt.setString(7,employee.getUsername());
            stmt.setString(8,employee.getPassword());

            stmt.executeUpdate();

            allEmployees.add(employee);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    public ArrayList<Employee> getEmployee(){

        try {
            allEmployees = new ArrayList<>();
            String query ="SELECT * FROM staff";
            ResultSet rs=getDBcon(query);
            while (rs.next()) {
                Employee employee = new Employee( rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5),
                        rs.getInt(7),rs.getString(6),rs.getString(8),rs.getString(9)) {
                };
                allEmployees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEmployees;


    }

    public void updateEmployee(Employee employee){

        try {
            Connection conn = DBConn.getConn();

            String query = "UPDATE staff SET staff_name=?, staff_cpr=?, staff_DOB=?, staff_email=?, staff_address=?, staff_tlf=?, username=?,password=? WHERE staff_cpr=?;";
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getCpr());
            stmt.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getAddress());
            stmt.setInt(6, employee.getPhoneNumber());
            stmt.setString(7,employee.getUsername());
            stmt.setString(8,employee.getPassword());
            stmt.setString(9,employee.getCpr());


            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }



    }
//RESERVATION
    public void addReservation(Reservation reservation) {

        try {
            Connection conn = DBConn.getConn();

            String query = "INSERT INTO reservation(customer_name, customer_cpr, customer_DOB, customer_email, customer_address, customer_tlf ) VALUES (?,?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);



        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void getReservation(){

    }

    public void updateReservation(){

    }


//RENTAL
    public void addRental(){



    }
//ACCESSORIES
    public void addAccessory(Accessory accessory){

        try {
            Connection conn = DBConn.getConn();

            String query = "INSERT INTO rent_accessory(accessory_id, name,price, quantity) VALUES (NULL ,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setString(1, accessory.getType());
            stmt.setInt(2, accessory.getQuantity());
            stmt.setDouble(3,accessory.getPrice());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }


    }
    public void updateAccessory(){
    }
    public ArrayList<Accessory> getAccessory(){
     return null;
    }

//MOTORHOME
    public void addMotorhome(Motorhome motorhome){

        try {
            Connection conn = DBConn.getConn();

            String query = "INSERT INTO motorhome(motorhome_id, motorhome_brand, motorhome_model, motorhome_size, motorhome_price) VALUES (NULL ,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setString(1, motorhome.getBrand());
            stmt.setString(2,motorhome.getModel());
            stmt.setString(3, motorhome.getSize());
            stmt.setDouble(4, motorhome.getPricePerDay());

            stmt.executeUpdate();
            allMotorhomes.add(motorhome);


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    public void updateMotorhome(Motorhome motorhome){

        try {
            Connection conn= DBConn.getConn();
            String query="UPDATE motorhome SET motorhome_brand=?, motorhome_model=?, motorhome_size=?, motorhome_price=?  WHERE motorhome_id =?;" ;
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setString(1, motorhome.getBrand());
            stmt.setString(2,motorhome.getModel());
            stmt.setString(3, motorhome.getSize());
            stmt.setDouble(4, motorhome.getPricePerDay());
            stmt.setInt(5,motorhome.getMotorhomeID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public ArrayList<Motorhome> getMotorhomes(){


        try {
            allMotorhomes = new ArrayList<>();
            String query ="SELECT * FROM motorhome";
            ResultSet rs=getDBcon(query);
            while (rs.next()) {
                Motorhome motorhome = new Motorhome(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5));
                allMotorhomes.add(motorhome);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allMotorhomes;
    }



    public ResultSet getDBcon (String query){
        ResultSet rs =null;
        try {
            Connection conn = DBConn.getConn();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}








