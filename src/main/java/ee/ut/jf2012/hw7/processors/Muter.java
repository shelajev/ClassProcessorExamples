package ee.ut.jf2012.hw7.processors;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ut.Mute;
import ee.ut.jf2012.hw7.xml.Beans;

public class Muter implements CtClassProcessor {
  private static final Logger log = LoggerFactory.getLogger(Muter.class);

  @Override
  public void process(Beans beans, String id, CtClass clazz) throws CannotCompileException {
    CtMethod[] methods = clazz.getMethods();
    for (CtMethod method : methods) {
      if (method.hasAnnotation(Mute.class)) {
        mute(method);
      }
    }
  }

  private void mute(CtMethod method) throws CannotCompileException {
    log.info("Muting method {}.{}()", new Object[]{method.getDeclaringClass().getSimpleName(), method.getName()}); 
    method.instrument(new ExprEditor() {
      @Override
      public void edit(MethodCall m) throws CannotCompileException {
        if(m.getMethodName().equals("println") && m.getClassName().equals("java.io.PrintStream")) {
          m.replace(
              "{" +
              "  if($0 != System.out) {" +
              "    $proceed($$);" +
              "  }" +
              "}"
          );
        }
      }
    });

  }

}
