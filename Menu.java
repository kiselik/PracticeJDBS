import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    // private List<MenuEntry> list;
    private boolean isExit;
    private Database dat;
    private int selection = 0;

    public Menu() throws ClassNotFoundException, SQLException {
        //list=new ArrayList<>();
        //isExit=false

    }

    public void run() throws ClassNotFoundException, SQLException {
        while (selection != 6) {
            System.out.println("[1]  Find Employee");
            System.out.println("[2]  Find Employees for job");
            System.out.println("[3]  Find Employees for departs");
            System.out.println("[4]  Add Employee");
            System.out.println("[5]  Delete Employee");
            System.out.println("[6]  Quit");
            System.out.print("Write number there: ");

            Scanner reader = new Scanner(System.in);
            if (!reader.hasNextInt()) {
                selection = -1;
            } else {
                selection = reader.nextInt();
            }
            switch (selection) {
                case 1:
                    System.out.println("------------------------------------------");
                    System.out.println("Please, write id Employee there");
                    if (reader.hasNextInt()) {
                        int id = reader.nextInt();
                        System.out.println("Are you sure? Y/N");
                        if (reader.next().equals("Y")) {
                            dat = new Database();
                            System.out.println(dat.searchEmployee(id));
                            dat.closeConn();
                        }
                    } else
                        System.out.println("Well, it isn't a number.Try again");
                    System.out.println("------------------------------------------");
                    break;
                case 2:
                    System.out.println("------------------------------------------");
                    System.out.println("Please, write job of Employee there");
                    if (reader.hasNextLine()) {
                        reader.nextLine();
                        String job = reader.nextLine();
                        System.out.println("Are you sure? Y/N");
                        if (reader.next().equals("Y")) {
                            dat = new Database();
                            System.out.println(dat.searchEmployeeJob(job));
                            dat.closeConn();
                        }
                    } else
                        System.out.println("Well, it isn't a string.Try again");
                    System.out.println("------------------------------------------");
                    break;
                case 3:
                    System.out.println("------------------------------------------");
                    System.out.println("Please, write name of Employees depart there");
                    if (reader.hasNextLine()) {
                        reader.nextLine();
                        String dept = reader.nextLine();
                        System.out.println("Are you sure? Y/N");
                        if (reader.next().equals("Y")) {
                            dat = new Database();
                            System.out.println(dat.searchEmployeeDept(dept));
                            dat.closeConn();
                        }
                    } else
                        System.out.println("Well, it isn't a string.Try again");
                    System.out.println("------------------------------------------");
                    break;
                case 4:
                    System.out.println("------------------------------------------");
                    System.out.println("Please, write information about Employee in format");
                    System.out.println("[id employee][name][position][number of manager][hiredate DD-MM-YYYY][salary][comm][number of department]");
                    System.out.println("example:  5 olga princess 7499 12-09-2013 4600 50 20");
                    reader.nextLine();
                    String tmp = reader.nextLine();
                    System.out.println("Are you sure? Y/N");
                    if (reader.next().equals("Y")) {
                        String[] inf = tmp.split(" ");
                        dat = new Database();
                        try {
                            dat.AddEmployee(Integer.parseInt(inf[0]), inf[1], inf[2], Integer.parseInt(inf[3]), inf[4], Integer.parseInt(inf[5]), inf[6], Integer.parseInt(inf[7]));
                        } catch (SQLException e) {
                            System.out.println("Oops, you have wrong option in centence");
                        }
                        dat.closeConn();
                    }
                    System.out.println("------------------------------------------");
                    break;
                case 5:
                    System.out.println("------------------------------------------");
                    System.out.println("Please, write id Employee there");
                    int id_emp;
                    if (reader.hasNextInt()) {
                        id_emp = reader.nextInt();
                        System.out.println("Are you sure? Y/N");
                        if (reader.next().equals("Y")) {
                            dat = new Database();
                            dat.DeleteEmployee(id_emp);
                            dat.closeConn();
                        }
                    } else
                        System.out.println("Well, it isn't a number.Try again");
                    System.out.println("------------------------------------------");
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Unfortunately, I don't understand your choice =(");
                    System.out.println(" ");

            }
        }


    }


}
