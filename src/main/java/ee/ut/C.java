package ee.ut;

public class C {

  @Trace
  @Mute
  public void foo() {
    System.out.println("C.foo");
  }
}