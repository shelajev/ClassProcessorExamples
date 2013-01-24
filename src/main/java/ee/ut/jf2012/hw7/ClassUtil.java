package ee.ut.jf2012.hw7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ut.jf2012.hw7.processors.ActionDelegator;
import ee.ut.jf2012.hw7.processors.CtClassProcessor;
import ee.ut.jf2012.hw7.processors.DependencyCreator;
import ee.ut.jf2012.hw7.processors.Muter;
import ee.ut.jf2012.hw7.processors.Tracer;
import ee.ut.jf2012.hw7.xml.Bean;
import ee.ut.jf2012.hw7.xml.Beans;

public class ClassUtil {

  private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);

  // the order of these is kinda important
  // we want to mute, then add trace as inserBefore
  // then add delegation as insertBefore
  // to satisfy sample output
  private static final CtClassProcessor[] ctClassProcessors = { new DependencyCreator(), new Muter(), new Tracer(),
      new ActionDelegator() };

  public static void prepareClasses(Beans beans) throws Exception {
    ClassPool cp = ClassPool.getDefault();
    Map<Bean, CtClass> ctClasses = processBeanClasses(cp, beans);
    loadCtClasses(ctClasses.values());
  }

  private static void loadCtClasses(Collection<CtClass> values) throws CannotCompileException {
    for (CtClass ctClass : values) {
      ctClass.toClass();
    }
  }

  private static Map<Bean, CtClass> processBeanClasses(ClassPool cp, Beans beans) throws Exception {
    Map<Bean, CtClass> classes = new HashMap<>();
    for (Bean b : beans.beans) {
      CtClass ctClass = cp.getCtClass(b.className);
      log.info("Processing class {}", ctClass.getName());
      processClass(beans, b.id, ctClass);
      classes.put(b, ctClass);
    }
    return classes;
  }

  private static void processClass(Beans beans, String id, CtClass clazz) throws Exception {
    for (CtClassProcessor processor : ctClassProcessors) {
      processor.process(beans, id, clazz);
    }
  }

  public static void run(String stringToRun) throws Exception {
    // hack on a hack!
    ClassPool cp = ClassPool.getDefault();
    CtClass ctClass = cp.makeClass("jf2012HW7_runner");
    CtConstructor makeClassInitializer = ctClass.makeClassInitializer();
    makeClassInitializer.insertBefore("{" + stringToRun + "}");
    ctClass.toClass().newInstance();
  }

}
