package xml_homework.Midterm;

public class Score {
  private String student_id;
  private int xml_class;
  private int data_structure;
  private int algorithm;
  private int network;
  
  public Score(String student_id, int xml_class, int data_structure, int algorithm, int network) {
    this.student_id = student_id;
    this.xml_class = xml_class;
    this.data_structure = data_structure;
    this.algorithm = algorithm;
    this.network = network;
  }
  public String getStudent_id() {
    return student_id;
  }
  public void setStudent_id(String student_id) {
    this.student_id = student_id;
  }
  public int getXml_class() {
    return xml_class;
  }
  public void setXml_class(int xml_class) {
    this.xml_class = xml_class;
  }
  public int getData_structure() {
    return data_structure;
  }
  public void setData_structure(int data_structure) {
    this.data_structure = data_structure;
  }
  public int getAlgorithm() {
    return algorithm;
  }
  public void setAlgorithm(int algorithm) {
    this.algorithm = algorithm;
  }
  public int getNetwork() {
    return network;
  }
  public void setNetwork(int network) {
    this.network = network;
  }
  @Override
  public String toString() {
    return "Score [student_id=" + student_id + ", xml_class=" + xml_class + ", data_structure=" + data_structure
        + ", algorithm=" + algorithm + ", network=" + network + "]";
  }
  
}
