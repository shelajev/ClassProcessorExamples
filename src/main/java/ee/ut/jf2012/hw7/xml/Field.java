package ee.ut.jf2012.hw7.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "field")
public class Field {

  @XmlAttribute(name = "name")
  public String name;

  @XmlAttribute(name = "ref")
  public String ref;

}
