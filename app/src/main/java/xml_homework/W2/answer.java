package xml_homework.W2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class answer {
  static final String DB_URL = "jdbc:mysql://localhost/xmldb";
   static final String USER = "admin";
   static final String PASS = "admin";
   static final String QUERY = "SELECT id, first, last, nick, mark FROM class";

   public static void main(String[] args) throws ParserConfigurationException, TransformerException {

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.newDocument();
      
      // root element
      Element rootElement = doc.createElement("class");
      doc.appendChild(rootElement);
      
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);
      ) {		      
         while(rs.next()){
            //Display values
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", Mark: " + rs.getInt("mark"));
            System.out.print(", First: " + rs.getString("first"));
            System.out.println(", Last: " + rs.getString("last"));
            // student element
         Element student = doc.createElement("student");
         rootElement.appendChild(student);

         // setting attribute to element
         Attr attr = doc.createAttribute("rollno");
         attr.setValue(String.valueOf(rs.getInt("id")));
         student.setAttributeNode(attr);

         // firstname element
         Element firstname = doc.createElement("firstname");
         firstname.appendChild(doc.createTextNode(rs.getString("first")));
         student.appendChild(firstname);
         
         // lastname element
         Element lastname = doc.createElement("lastname");
         lastname.appendChild(doc.createTextNode(rs.getString("last")));
         student.appendChild(lastname);
         
         // nickname element
         Element nickname = doc.createElement("nickname");
         nickname.appendChild(doc.createTextNode(rs.getString("nick")));
         student.appendChild(nickname);
         
         // marks element
         Element marks = doc.createElement("marks");
         marks.appendChild(doc.createTextNode(String.valueOf(rs.getInt("mark"))));
         student.appendChild(marks);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      //StreamResult result = new StreamResult(new File("C:\\cars.xml"));
      StreamResult result = new StreamResult(new File("test.xml"));
      transformer.transform(source, result);
      
      // Output to console for testing
      StreamResult consoleResult = new StreamResult(System.out);
      transformer.transform(source, consoleResult);
   }
}
