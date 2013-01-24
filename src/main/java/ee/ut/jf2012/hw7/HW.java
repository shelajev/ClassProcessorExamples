package ee.ut.jf2012.hw7;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;

import ee.ut.jf2012.hw7.xml.Bean;
import ee.ut.jf2012.hw7.xml.Beans;

public class HW {

  public void run(String xmlName, String beanId) throws Exception {
    JAXBContext context = JAXBContext.newInstance(Beans.class);
    //Rein taught me to close streams :D
    try (InputStream is = new FileInputStream(xmlName)) {
      Beans beans = (Beans) context.createUnmarshaller().unmarshal(is);
      ClassUtil.prepareClasses(beans);
      ClassUtil.run(getStringToRun(beans, beanId));
    }
  }

  private String getStringToRun(Beans beans, String beanId) {
    Bean bean = beans.get(beanId);
    StringBuilder sb = new StringBuilder();
    sb.append("new ");
    sb.append(bean.className);
    sb.append("().");
    sb.append(bean.methodName);
    sb.append("();");
    return sb.toString();
  }
}
