package xml_homework.W1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample_Insert {
   static final String DB_URL = "jdbc:mysql://localhost/xmldb";
   static final String USER = "admin";
   static final String PASS = "admin";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
         // Execute a query
         System.out.println("Inserting records into the table...");          
         String sql = "INSERT INTO class VALUES (100, 'Gena', 'Wu', 'Gena', 95)";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO class VALUES (101, 'Arny', 'Wu', 'Arny', 90)";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO class VALUES (102, 'Kiki', 'Yeh', 'Kiki', 90)";
         stmt.executeUpdate(sql);
         sql = "INSERT INTO class VALUES (103, 'Tracy', 'Chang', 'Tracy', 93)";
         stmt.executeUpdate(sql);
         System.out.println("Inserted records into the table...");
         
         /*String  sql_insert = "INSERT INTO class(id,first,last,nick,mark) VALUES (?,?,?,?,?)";
         PreparedStatement preparedStatement = conn.prepareStatement(sql_insert);
         preparedStatement.setInt(1, 104);
         preparedStatement.setString(2, "Cody");
         preparedStatement.setString(3, "Wang");
         preparedStatement.setString(4, "Cody");
         preparedStatement.setInt(5, 80);
         System.out.println(preparedStatement);
         preparedStatement.executeUpdate();    */     
         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}