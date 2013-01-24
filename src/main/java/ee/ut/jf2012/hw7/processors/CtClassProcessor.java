package ee.ut.jf2012.hw7.processors;

import javassist.CtClass;
import ee.ut.jf2012.hw7.xml.Beans;

public interface CtClassProcessor {
  void process(Beans beans, String id, CtClass clazz) throws Exception;
}
