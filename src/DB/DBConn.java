package DB;//Magnus Svendsen DAT16i

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
    private static final String URL = "";
    private static final String DB_NAME = "";
    private static final String USER = "";
    private static final String PASS = "";

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
