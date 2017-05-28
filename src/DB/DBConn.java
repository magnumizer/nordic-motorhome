package DB;//Magnus Svendsen DAT16i

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
    private static final String URL = "jdbc:mysql://mydatabase.caz3kevaubmz.us-west-2.rds.amazonaws.com:3306/";
    private static final String DB_NAME = "nordicmotorhome";
    private static final String USER = "magnus";
    private static final String PASS = "euw64cpt";

    public static Connection getConn()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    DBConn.URL + DBConn.DB_NAME,
                    DBConn.USER,
                    DBConn.PASS);
            return conn;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
