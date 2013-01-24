package ee.ut.jf2012.hw7.processors;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ut.jf2012.hw7.xml.Bean;
import ee.ut.jf2012.hw7.xml.Beans;

/**
 * Add dependency instantiation in the default constructor.
 * Circular dependencies will blow everything up.
 * 
 * @author shelajev
 * 
 */
public class DependencyCreator implements CtClassProcessor {
  private static final Logger log = LoggerFactory.getLogger(DependencyCreator.class);

  @Override
  public void process(Beans beans, String id, CtClass clazz) throws NotFoundException, CannotCompileException {
    Bean bean = beans.get(id);
    if (bean.field == null) {
      return;
    }
    Bean dependency = beans.get(bean.field.ref);

    log.info("Changing consctuctor of class {}, injecting field {}, dependency bean class = {}",
        new Object[] { clazz.getName(), bean.field.name, dependency.className });
    CtConstructor constructor = clazz.getConstructor("()V");
    constructor.insertAfter("{" + "  $0." + bean.field.name + " = new " + dependency.className + "();" + "}");
  }

}
