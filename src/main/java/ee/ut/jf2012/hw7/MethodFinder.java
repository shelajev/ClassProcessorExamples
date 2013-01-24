package ee.ut.jf2012.hw7;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class MethodFinder {
  
  public static void main(String[] args) throws Throwable {
    ClassPool cp = ClassPool.getDefault();
    CtClass ctClass = cp.get("ee.ut.jf2012.hw7.MyClass");
    CtMethod method = ctClass.getDeclaredMethod("getItem1");
    method.instrument(
        new ExprEditor() {
            public void edit(MethodCall m)
                          throws CannotCompileException
            {
//                System.out.println(m.getClassName() + "." + m.getMethodName() + " " + m.getSignature());
                try {
                  System.out.println(m.getMethod().getLongName());
                }
                catch (NotFoundException e) {
                  e.printStackTrace();
                }
            }
        });
  }

}
