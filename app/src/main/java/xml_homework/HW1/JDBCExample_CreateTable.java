package xml_homework.HW1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample_CreateTable {
   static final String DB_URL = "jdbc:mysql://localhost/xmldb";
   static final String USER = "admin";
   static final String PASS = "admin";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
          String sql = "CREATE TABLE class" +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " nick VARCHAR(255), " + 
                   " mark VARCHAR(255), " + 
                   " PRIMARY KEY ( id ))"; 

         stmt.executeUpdate(sql);
         System.out.println("Created table in given database...");   	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}