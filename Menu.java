import java.util.Scanner;

public class Menu {
    // private List<MenuEntry> list;
    private boolean isExit;
    private int selection = 0;

    public Menu() {
        //list=new ArrayList<>();
        //isExit=false
    }

    public void run() {
        while (selection != 4) {
            System.out.println("[1]  Find Employee");
            System.out.println("[2]  Add Employee");
            System.out.println("[3]  Delete Employee");
            System.out.println("[4]  Quit");
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
                    int id;
                    if(reader.hasNextInt()){
                        id=reader.nextInt();
                    }
                    System.out.println("------------------------------------------");
                    break;
                case 2:
                    System.out.println("------------------------------------------");
                    System.out.println("2");
                    System.out.println("------------------------------------------");
                    break;
                case 3:
                    System.out.println("------------------------------------------");
                    System.out.println("3");
                    System.out.println("------------------------------------------");
                    break;
                case 4:
                    System.out.println("4");
                    break;
                default:
                    System.out.println("Unfortunately, I don't understand your choice =(");
                    System.out.println(" ");

            }
        }


    }


}
