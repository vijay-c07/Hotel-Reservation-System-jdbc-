import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/hotel_db";
    private static final String username = "postgres";
    private static final String password = "Password";

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Welcome to Hotel Reservation System!!");
        int ip;
        Scanner sc = new Scanner(System.in);
        Queries con = new Queries();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();

            do {
                System.out.println("===================Hotel Reservation System===================");
                System.out.println("1.New Reservation");
                System.out.println("2.View Reservation");
                System.out.println("3.Get room number");
                System.out.println("4.Delete Reservation");
                System.out.println("5.Update Reservation");
                System.out.println("6.Exit");

                System.out.println("\nSelect option:(eg..1,2...6)");
                ip = sc.nextInt();

                switch (ip) {
                    case 1:
                        con.NewReservation(connection, sc, stmt);
                        break;
                    case 2:
                        con.ViewReservation(connection, stmt);
                        break;
                    case 3:
                        con.GetRoomNo(connection, sc, stmt);
                        break;
                    case 4:
                        con.DeleteReservation(connection, sc, stmt);
                        break;
                    case 5:
                        con.UpdateReservation(connection, sc, stmt);
                        break;
                    case 6:
                        connection.close();
                        exit();
                        break;
                    default:
                        System.out.println("Invalid Input,Please try again.");

                }
            } while (ip != 6);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 7;
        while (i != 0) {
            System.out.print(".");
            Thread.sleep(350);
            i--;
        }
        System.out.println();
        return;
    }
}
