package JdbcConnectivity;

import java.sql.*;
import java.util.Scanner;

public class Task1
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

                        System.out.println(" Enter Id ");
                        int id=sc.nextInt();

                        System.out.println(" Enter Name ");
                        String name=sc.next();

                        System.out.println(" Enter Age ");
                        int age=sc.nextInt();

                        System.out.println(" Enter Course ");
                        String course=sc.next();

                    String query= "insert into Student (id, name, age, course) values(?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(query);

                    ps.setInt(1,id);
                    ps.setString(2, name);
                    ps.setInt(3, age);
                    ps.setString(4, course);

                    if (ps.executeUpdate() > 0) {
                        System.out.println(" Insert Successful ");
                    } else {
                        System.out.println(" Failed");
                    }
                    break;
                    case 2:
                    {
                        System.out.println(" ");
//                        String str= "update Student set name=?,age=?,course=? where id=?";
//                        PreparedStatement pst = conn.prepareStatement(str);
//                        pst.setString(1, "Harshal");
//                        pst.setInt(2, 10);
//                        pst.setString(3, " BMC ");
//                        pst.setInt(4, 1);
//
//                        int UpdatedRows = pst.executeUpdate();
//                        if (UpdatedRows > 0) {
//                            System.out.println(" Updated Successfully ");
//                        } else {
//                            System.out.println(" Update Failed ");
//                    }
                        break;
                    }
                    case 3:

                        System.out.println(" Enter Id ");
                        int id1=sc.nextInt();

                        String sql1= "delete from Student where id=?";
                        PreparedStatement pst1 = conn.prepareStatement(sql1);
                        pst1.setInt(1, id1);

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