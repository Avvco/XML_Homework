package xml_homework.Midterm;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Part1 {
  static final String DB_URL = "jdbc:mysql://localhost/xmldb";
  static final String USER = "admin";
  static final String PASS = "admin";

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SQLException {
    createTable();
    readXML();
  }

  public static void createTable() {
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
          String sql = "CREATE TABLE score" +
                   "(student_id VARCHAR(5) not NULL, " +
                   " xml_class INT, " + 
                   " data_structure INT, " + 
                   " algorithm INT, " + 
                   " network INT, " + 
                   " PRIMARY KEY ( student_id ))"; 

         stmt.executeUpdate(sql);
         System.out.println("Created table in given database...");   	  
      } catch (SQLException e) {
         e.printStackTrace();
      }
      System.out.println("Table Created.");
  }

  public static void readXML() throws ParserConfigurationException, SAXException, IOException, SQLException {
    File inputFile = new File("midterm.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(inputFile);
    doc.getDocumentElement().normalize();

    // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
    
    NodeList nList = doc.getElementsByTagName("student");
    for(int temp = 0; temp < nList.getLength(); temp++) {
      Node node = nList.item(temp);

      if (node.getNodeType() != Node.ELEMENT_NODE) continue;

      Element element = (Element) node;
      
      insertDataToTable(element.getAttribute("student_id"),
                        Integer.parseInt(element.getElementsByTagName("xml_class").item(0).getTextContent()),
                        Integer.parseInt(element.getElementsByTagName("data_structure").item(0).getTextContent()), 
                        Integer.parseInt(element.getElementsByTagName("algorithm").item(0).getTextContent()), 
                        Integer.parseInt(element.getElementsByTagName("network").item(0).getTextContent()));
    }
  }

  public static void insertDataToTable(String student_id, Integer xml_class, Integer data_structure, Integer algorithm, Integer network) throws SQLException {
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {          
        String sql = "INSERT INTO score VALUES('%s', %s, %s, %s, %s)".formatted(student_id, xml_class, data_structure, algorithm, network);
        System.out.println(sql);
        stmt.executeUpdate(sql);
      }
  }
}
