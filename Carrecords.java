import java.sql.*;
import java.util.Scanner;

public class Carrecords {

    public static void main(String[] args) {
        String dbURL ="jdbc:mysql://localhost:3306/vehicles";
        String username = "root";
        String password ="Root@123";
        Connection conn;
        int ch=0;
        int op=0;
        Scanner sc=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn =
                    DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Connected");
            }
            do {
                switch(op) {

                    case 1:
                        do {
                            System.out.println("Enter Car number: ");
                            int number=sc.nextInt();
                            System.out.println("Enter Car Name: ");
                            String name=sc.next();
                            System.out.println("Enter Car Colour: ");
                            String Colour=sc.next();
                            System.out.println("Enter Car Price: ");
                            int price=sc.nextInt();
                            String sql = "INSERT INTO v1 (number, name, Colour, price) VALUES (?, ?, ?, ?)";
                            PreparedStatement statement =
                                    conn.prepareStatement(sql);
                            statement.setInt(1, number);
                            statement.setString(2, name);
                            statement.setString(3, Colour);
                            statement.setInt(4, price);
                            int rowsInserted =
                                    statement.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("A new car was registered successfully!");
                            }
                            System.out.println("Do you want to continue?");
                            ch=sc.nextInt();
                        }while(ch==1);
                        break;
                    case 2:
                        System.out.println("Enter records for updation.");
                        System.out.println("Enter car number records are to be updated");
                        int unumber=sc.nextInt();
                        System.out.println("Enter Updated Car Name");
                        String uname=sc.next();
                        System.out.println("Enter Updated Car Colour");
                        String uColour=sc.next();
                        System.out.println("Enter Car price to be updated");
                        int uprice=sc.nextInt();
                        String sql2 = "UPDATE v1 SET name=?, Colour=?, price=? WHERE number=?";
                        PreparedStatement statement1 =
                                conn.prepareStatement(sql2);
                        statement1.setString(1, uname);
                        statement1.setString(2, uColour);
                        statement1.setInt(3, uprice);
                        statement1.setInt(4, unumber);
                        int rowsUpdated =
                                statement1.executeUpdate();
                        if (rowsUpdated > 0) {
                        System.out.println("An Employee was updated successfully!");
                    }
                    break;
                    case 3:
                        System.out.println("Enter records for Deletion");
                        System.out.println("Enter Car number whose records are to be deleted");
                        int dnumber=sc.nextInt();
                        String sql3 = "DELETE FROM v1 WHERE number=?";
                        PreparedStatement statement3 =
                                conn.prepareStatement(sql3);
                        statement3.setInt(1,dnumber);
                        int rowsDeleted =
                                statement3.executeUpdate();
                        if (rowsDeleted > 0) {
                        System.out.println("A car record was deleted successfully!");
                    }
                    break;
                    case 4:
                        String sql1 = "SELECT * FROM v1";

                        Statement stmt = conn.createStatement();
                        ResultSet result =
                                stmt.executeQuery(sql1);
                        int count = 0;
                        while (result.next()){
                            int enumber = result.getInt(1);
                            String ename = result.getString(2);
                            String eColour = result.getString(3);
                            int eprice = result.getInt(4);
                            String output = "Car #%d: -%d - %s - %s - %d";
                            System.out.println(String.format(output, ++count,enumber, ename,eColour, eprice));
                        }
                        break;
                }
                System.out.println("Enter your choice:1.Add Car record 2.Update Car record 3.Delete Car record 4. Display");
                op=sc.nextInt();
            }while(op!=0);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
