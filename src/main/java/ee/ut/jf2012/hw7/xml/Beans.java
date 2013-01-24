package ee.ut.jf2012.hw7.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "beans")
public class Beans {

    @XmlElement(name = "bean")
    public List<Bean> beans;

    public Bean get(String beanId) {
      for(Bean bean: beans) {
        if(bean.id.equals(beanId)) {
          return bean;
        }
      }
      throw new IllegalArgumentException("Bean " + beanId + " is not defined!");
    }
    

}