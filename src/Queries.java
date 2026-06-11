import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Queries {

    public void NewReservation(Connection connection,Scanner sc,Statement stmt){
        
        System.out.println("Enter Room number: ");
        int roomNo=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Guest name: ");
        String name=sc.nextLine();
        System.out.println("Enter Contact No: ");
        String contact=sc.next();
        
        try {
            int rowAffected = stmt.executeUpdate(
            "INSERT INTO reservation(guest_name, room_number, contact_number) " +
            "VALUES('" + name + "', " + roomNo + ", '" + contact +  "')"
            );

            if(rowAffected>0)
            System.out.println("Reservation successful!!");
            else
            System.out.println("Reservation Failed!!!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void ViewReservation(Connection connection, Statement stmt){
        System.out.println();
        try {
            ResultSet rs=stmt.executeQuery("SELECT * FROM reservation");
            System.out.println("Reservation Details: ");
            System.out.println("----------------------+----------------------+----------------------+----------------------+----------------------+");
            System.out.println("|    Reservation ID   |      Guest Name      |     Room Number      |     Contact Number   |    Reservation Date  |");
            System.out.println("------------------------------------------------------------------------------------------------------------------+");

            while(rs.next()){
                int id=rs.getInt("reservation_id");
                String name=rs.getString("guest_name");
                String contact=rs.getString("contact_number");
                int roomNo=rs.getInt("room_number");
                String date=rs.getTimestamp("reservation_date").toString();

                System.out.printf("|%-16d  |%-19s  |%-19d  |%-19s  |%-19s  |%n",
                  id, name, roomNo, contact, date);
                System.out.println("-------------------------------------------------------------------------------------------------------------+");

            }
        } catch (SQLException e) {
           
          System.out.println(e.getMessage());
        }
        
    System.out.println();
    }



    public void GetRoomNo(Connection connection,Scanner sc,Statement stmt){
        sc.nextLine(); 
        System.out.println("Enter Guest name: ");
        String name = sc.nextLine();

        System.out.println("Enter reservation id:");
        int id = sc.nextInt();
        
        try {
           ResultSet rs = stmt.executeQuery("SELECT room_number FROM reservation WHERE reservation_id=" + id +
            " AND guest_name='" + name + "'");
            if (rs.next()) {
                int roomNo = rs.getInt("room_number");
                System.out.println(
                "The guest " + name +
                " with reservation id " + id +
                " stays in room no: " + roomNo
                 );
                } 
                else {
                     System.out.println("No reservation found.");
                    }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }


    public void DeleteReservation(Connection connection,Scanner sc,Statement stmt){
        System.out.println("Enter reservation id to  delete:");
        int id=sc.nextInt();
        try {
            System.out.println("Do you really want to delete reservation(Y/N):");
            String confirm=sc.next();
            if(confirm.equalsIgnoreCase("Y")){
                int rowsAffected=stmt.executeUpdate("DELETE FROM reservation WHERE reservation_id="+id);
                if(rowsAffected>0)
                System.out.println("Reservation deleted successfully!!");
                else
                System.out.println("Reservation deletion Failed!!");
            }
                    
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }


    public void UpdateReservation(Connection connection,Scanner sc,Statement stmt){
        System.out.println("Enter reservation id to update:");
        int id=sc.nextInt();
         System.out.println("Enter Room number to update: ");
        int roomNo=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Guest name to update: ");
        String name=sc.nextLine();
        System.out.println("Enter Contact No to update: ");
        String contact=sc.next();
        System.out.println("Enter reservation date:(dd-mm-yyyy) to update");
        String reservation_date=sc.next();
        
        try {
            int rowAffected = stmt.executeUpdate(
            "UPDATE reservation " +
            "SET guest_name='" + name + "', " +
            "contact_number='" + contact + "', " +
            "room_number=" + roomNo + " " +
            "WHERE reservation_id=" + id
            );

            if(rowAffected>0)
            System.out.println("Reservation updated  successful!!");
            else
            System.out.println("Reservation updation Failed!!!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }    
    }