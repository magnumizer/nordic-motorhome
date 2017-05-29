package DB;//Magnus Svendsen DAT16i

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
    private static final String URL = "jdbc:mysql://54.93.253.101/";
    private static final String DB_NAME = "nordicmotorhome";
    private static final String USER = "minh";
    private static final String PASS = "vivian4499";

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
