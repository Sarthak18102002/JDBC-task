package JdbcConnectivity;

import java.sql.*;
import java.util.Scanner;

public class Task
{
    private static String URL = "jdbc:mysql://localhost:3306/weoto";
    private static String user = "root";
    private static final String password = "Sarthak@123";

    public static void main(String[] args)
    {
        try
        {
            Scanner sc = new Scanner(System.in);

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(" Driver Loaded...");

            Connection conn = DriverManager.getConnection(URL, user, password);
            System.out.println(" Connection successfully!");


            while (true)
            {
                System.out.println(" 1. Insert Data ");
                System.out.println(" 2. Update Data ");
                System.out.println(" 3. Delete Data ");
                System.out.println(" 4. View Data ");
                System.out.println(" 5. Exit ");


                System.out.print(" Enter Choice : ");
                int ch = sc.nextInt();

                switch(ch)
                {
                    case 1:
                    String query= "insert into Student (id, name, age, course) values(?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(query);

                    ps.setInt(1, 2);
                    ps.setString(2, "Kunal");
                    ps.setInt(3, 23);
                    ps.setString(4, "Mca");

                    if (ps.executeUpdate() > 0) {
                        System.out.println(" Insert Successful ");
                    } else {
                        System.out.println(" Failed");
                    }
                    break;
                    case 2:
                    {
                        String str= "update Student set name=?,age=?,course=? where id=?";
                        PreparedStatement pst = conn.prepareStatement(str);
                        pst.setString(1, "Harshal");
                        pst.setInt(2, 10);
                        pst.setString(3, " BMC ");
                        pst.setInt(4, 1);

                        int UpdatedRows = pst.executeUpdate();
                        if (UpdatedRows > 0) {
                            System.out.println(" Updated Successfully ");
                        } else {
                            System.out.println(" Update Failed ");
                    }
                        break;
                    }
                    case 3:

                        String sql1= "delete from Student where id=?";
                        PreparedStatement pst1 = conn.prepareStatement(sql1);
                        pst1.setInt(1, 1);

                        int delete= pst1.executeUpdate();
                        if(delete>0)
                            System.out.println(" Deleted Successfully ");
                        else
                        {
                            System.out.println(" Delete Failed ");
                        }
                        break;

                    case 4:
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from Student");
                        while (rs.next())
                        {
                            System.out.println(rs.getInt("id") + " \n" + rs.getString("name") + " \n" + rs.getInt("age") + " \n" + rs.getString("course")+" \n");
                        }
                        break;

                    case 5:
                        System.out.println(" Exited ");
                        return;

                    default:
                        System.out.println(" Enter valid Choice");
                }
            }

        } catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}