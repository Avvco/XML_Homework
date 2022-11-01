package xml_homework.Midterm;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class Part2 {
  static final String DB_URL = "jdbc:mysql://localhost/xmldb";
  static final String USER = "admin";
  static final String PASS = "admin";

  public static void main(String[] args) throws SQLException, ParserConfigurationException, TransformerException {
    ArrayList<Score> score = readFromDB();
    // System.out.println(score.toString());
    writeToXML(score);
  }

  public static ArrayList<Score> readFromDB() throws SQLException {
    ArrayList<Score> score = new ArrayList<>();
    String query = "SELECT * FROM score";
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
      PreparedStatement statement = conn.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("student_id") + " " + rs.getInt("xml_class") + " " + rs.getInt("data_structure")
            + " " + rs.getInt("algorithm") + " " + rs.getInt("network"));
        score.add(new Score(rs.getString("student_id"), rs.getInt("xml_class"), rs.getInt("data_structure"), rs.getInt("algorithm"), rs.getInt("network")));
      }
    }
    return score;
  }

  public static void writeToXML(ArrayList<Score> score) throws ParserConfigurationException, TransformerException {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.newDocument();

    Element rootElement = doc.createElement("score_data");
    doc.appendChild(rootElement);
    
    for(Score _score : score) {
      Element student = doc.createElement("student");
      rootElement.appendChild(student);
      
      Attr attr = doc.createAttribute("student_id");
      attr.setValue(String.valueOf(_score.getStudent_id()));
      student.setAttributeNode(attr);

      Element xml_class = doc.createElement("xml_class");
      xml_class.appendChild(doc.createTextNode(String.valueOf(_score.getXml_class())));
      student.appendChild(xml_class);

      Attr attr_xml_class = doc.createAttribute("gpa");
      attr_xml_class.setValue(gpaTable(_score.getXml_class()));
      xml_class.setAttributeNode(attr_xml_class);


      Element data_structure = doc.createElement("data_structure");
      data_structure.appendChild(doc.createTextNode(String.valueOf(_score.getData_structure())));
      student.appendChild(data_structure);

      Attr attr_data_structure = doc.createAttribute("gpa");
      attr_data_structure.setValue(gpaTable(_score.getData_structure()));
      data_structure.setAttributeNode(attr_data_structure);


      Element algorithm = doc.createElement("algorithm");
      algorithm.appendChild(doc.createTextNode(String.valueOf(_score.getAlgorithm())));
      student.appendChild(algorithm);

      Attr attr_algorithm = doc.createAttribute("gpa");
      attr_algorithm.setValue(gpaTable(_score.getAlgorithm()));
      algorithm.setAttributeNode(attr_algorithm);


      Element network = doc.createElement("network");
      network.appendChild(doc.createTextNode(String.valueOf(_score.getNetwork())));
      student.appendChild(network);

      Attr attr_network = doc.createAttribute("gpa");
      attr_network.setValue(gpaTable(_score.getNetwork()));
      network.setAttributeNode(attr_network);
    }

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    //StreamResult result = new StreamResult(new File("C:\\cars.xml"));
    StreamResult result = new StreamResult(new File("midtermOUT.xml"));
    transformer.transform(source, result);
    
    // Output to console for testing
    StreamResult consoleResult = new StreamResult(System.out);
    transformer.transform(source, consoleResult);
  }

  public static String gpaTable(Integer score) {
    if(between(score, 90, 100)) return "4.5";
    if(between(score, 85, 89)) return "4.0";
    if(between(score, 80, 84)) return "3.7";
    if(between(score, 77, 79)) return "3.3";
    if(between(score, 73, 76)) return "3.0";
    if(between(score, 70, 72)) return "2.7";
    if(between(score, 67, 69)) return "2.5";
    if(between(score, 63, 66)) return "2.3";
    if(between(score, 60, 62)) return "2.0";
    if(between(score, 50, 59)) return "1.0";
    return "0.0";
  }

  public static boolean between(Integer score, Integer lower, Integer upper) {
    return lower <= score && score <= upper;
  }

}
