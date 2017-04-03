import java.sql.*;
import oracle.jdbc.driver.OracleDriver;

public class Database {
    public static void main (String args[])  throws ClassNotFoundException, SQLException {
        String driver = "oracle.jdbc.OracleDriver";
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        //DriverManager.getDriver(driver);
        //DriverManager.registerDriver(DriverManager.getDriver(driver));
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection(   "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:XE",
                "unc17i_kiseleva","pp6pj9VN");
        conn.close( );
    }

}
