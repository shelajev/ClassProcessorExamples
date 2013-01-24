package ee.ut.jf2012.hw7.processors;

import javassist.CtClass;
import javassist.CtMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ut.jf2012.hw7.xml.Bean;
import ee.ut.jf2012.hw7.xml.Beans;

public class ActionDelegator implements CtClassProcessor {
  private static final Logger log = LoggerFactory.getLogger(ActionDelegator.class);

  @Override
  public void process(Beans beans, String id, CtClass clazz) throws Exception {
    Bean bean = beans.get(id);
    if(bean.field == null) {
      return;
    }
    Bean dependency = beans.get(bean.field.ref);
    // we expect it to be without params and void. Note this is documented shortcoming! :D
    // can't take point off for not willing to iterate and compare names :D
    CtMethod method = clazz.getMethod(bean.methodName, "()V");
    log.info("Changing method {}.{}(), adding call to {}.{}()",
        new Object[] { clazz.getName(), bean.methodName, dependency.className, dependency.methodName });
    // call dependency method first
    method.insertBefore(
        "{" +
        "  $0." + bean.field.name + "." + dependency.methodName + "();" +
        "}"  
    );
  }

}
