import java.sql.*;
import java.util.LinkedList;

public class Database {
    private Connection conn;

    Database() throws ClassNotFoundException, SQLException {
        String driver = "oracle.jdbc.OracleDriver";
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@sql.edu-netcracker.com:1251:XE",
                "unc17i_kiseleva", "pp6pj9VN");
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
                tmp.append(res.getMetaData().getColumnName(i) + ": ");
                tmp.append(res.getString(i));
                tmp.append(" ");
            }
        }
        stat.close();
        if (tmp.toString().isEmpty())
            return "Well, i haven't this id of Employee";
        else
            return tmp.toString();
    }
    String searchEmployeeJob(String job) throws ClassNotFoundException, SQLException {
        PreparedStatement stat = conn.prepareStatement("SELECT e.ename AS NAME,m.mg,e.hiredate,e.sal, d.dname AS DEPART,d.loc AS LOCATION " +
                "FROM dept d,emp e, " +
                "(SELECT e1.ename,e2.ename AS mg FROM emp e1,emp e2 WHERE e2.empno=e1.mgr) m " +
                " WHERE e.job=? AND d.deptno=e.deptno AND m.ename=e.ename");
        stat.setString(1, job);

        ResultSet res = stat.executeQuery();
        int size = res.getMetaData().getColumnCount();
        StringBuilder tmp = new StringBuilder();


        while (res.next()) {
            for (int i = 1; i <= size; i++) {
                tmp.append(res.getMetaData().getColumnName(i) + ": ");
                tmp.append(res.getString(i));
                tmp.append(" ");
            }
        }
        stat.close();
        if (tmp.toString().isEmpty())
            return "Well, i haven't this id of Employee";
        else
            return tmp.toString();
    }

    String searchEmployeeDept(String dept) throws ClassNotFoundException, SQLException {
        PreparedStatement stat = conn.prepareStatement("SELECT e.ename AS NAME,e.job,m.mg,e.hiredate,e.sal AS DEPART,d.loc AS LOCATION " +
                "FROM dept d,emp e, " +
                "(SELECT e1.ename,e2.ename AS mg FROM emp e1,emp e2 WHERE e2.empno=e1.mgr) m " +
                " WHERE d.dname=? AND d.deptno=e.deptno AND m.ename=e.ename");
        stat.setString(1, dept);

        ResultSet res = stat.executeQuery();
        int size = res.getMetaData().getColumnCount();
        StringBuilder tmp = new StringBuilder();
        
        while (res.next()) {
            for (int i = 1; i <= size; i++) {
                tmp.append(res.getMetaData().getColumnName(i) + ": ");
                tmp.append(res.getString(i));
                tmp.append(" ");
            }
        }
        stat.close();
        if (tmp.toString().isEmpty())
            return "Well, i haven't this id of Employee";
        else
            return tmp.toString();
    }

    void AddEmployee(Integer empno, String ename, String job, Integer mgr, String hiredate, Integer salary, String comm, Integer deptno) throws ClassNotFoundException, SQLException {
        PreparedStatement stats = conn.prepareStatement("INSERT INTO emp VALUES (?,?,?,?,TO_DATE(?, 'DD-MM-YYYY'),?,?,?)");
        stats.setString(1, empno.toString());
        stats.setString(2, ename);
        stats.setString(3, job);
        stats.setString(4, mgr.toString());
        stats.setString(5, hiredate);
        stats.setString(6, salary.toString());
        stats.setString(7, comm);
        stats.setString(8, deptno.toString());
        int res = stats.executeUpdate();
        if (res != 0) {
            System.out.println("1 row inserted");
            conn.commit();
        } else
            System.out.println("Well, i haven't this id of Employee");
        stats.close();

    }

    void DeleteEmployee(int id) throws ClassNotFoundException, SQLException {
        PreparedStatement stat = conn.prepareStatement("DELETE FROM emp e WHERE e.empno=?");
        stat.setInt(1, id);

        int res = stat.executeUpdate();
        if (res != 0) {
            System.out.println("1 row deleted");
            conn.commit();
        } else
            System.out.println("Well, i haven't this id of Employee");
        stat.close();
    }


    void closeConn() throws ClassNotFoundException, SQLException {
        conn.close();
    }

}
