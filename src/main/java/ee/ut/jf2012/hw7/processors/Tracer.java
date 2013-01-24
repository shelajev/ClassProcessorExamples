package ee.ut.jf2012.hw7.processors;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ut.Trace;
import ee.ut.jf2012.hw7.xml.Beans;

public class Tracer implements CtClassProcessor {
  private static final Logger log = LoggerFactory.getLogger(Tracer.class);

  @Override
  public void process(Beans beans, String id, CtClass clazz) throws CannotCompileException {
    CtMethod[] methods = clazz.getMethods();
    for(CtMethod method : methods) {
      if(method.hasAnnotation(Trace.class)) {
        trace(method);
      }
    }
  }

  private void trace(CtMethod method) throws CannotCompileException {
    log.info("Adding trace to method {}.{}()", new Object[]{method.getDeclaringClass().getSimpleName(), method.getName()}); 
    method.insertBefore(
        "{" +
        "  System.out.println(\"Calling " +  method.getDeclaringClass().getSimpleName() + "." + method.getName() + "\");" +
        "}"
    );
  }

}
