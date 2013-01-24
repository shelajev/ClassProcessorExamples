package ee.ut.jf2012.hw7.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bean")
public class Bean {

  @XmlAttribute(name = "id")
  public String id;

  @XmlAttribute(name = "class")
  public String className;
  
  @XmlAttribute(name = "action")
  public String methodName;
  
  @XmlElement(name = "field")
  public Field field;

}
