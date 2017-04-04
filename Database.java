import java.sql.*;

public class Database {
    private Connection conn;

    Database() throws ClassNotFoundException, SQLException {
        String driver = "oracle.jdbc.OracleDriver";
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        //DriverManager.getDriver(driver);
        //DriverManager.registerDriver(DriverManager.getDriver(driver));
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@sql.edu-netcracker.com:1251:XE",
                "unc17i_kiseleva", "pp6pj9VN");

        //conn.close( );
    }

    String searchEmployee(int id) throws ClassNotFoundException, SQLException {
        PreparedStatement stat = conn.prepareStatement("SELECT e.ename AS NAME,e.job,m.mg,e.hiredate,e.sal, d.dname AS DEPART,d.loc AS LOCATION " +
                                            "FROM dept d,emp e, " +
                                            "(SELECT e1.ename,e2.ename AS mg FROM emp e1,emp e2 WHERE e2.empno=e1.mgr) m " +
                                            " WHERE e.empno=? AND d.deptno=e.deptno AND m.ename=e.ename");
        stat.setInt(1, id);

        ResultSet res = stat.executeQuery();
        int size = res.getMetaData().getColumnCount();
        StringBuilder tmp = new StringBuilder();


        while (res.next()) {
            for (int i = 1; i <= size; i++) {
                tmp.append(res.getMetaData().getColumnName(i)+": ");
                tmp.append(res.getString(i));
                tmp.append(" ");
            }
        }
        if (tmp.toString().isEmpty())
            return  "Well, i haven't this id of Employee";
        else
        return tmp.toString();
    }

    void closeConn() throws ClassNotFoundException, SQLException {
        conn.close();
    }

}
