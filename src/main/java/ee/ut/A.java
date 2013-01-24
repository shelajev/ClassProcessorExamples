package ee.ut;

public class A {

  private B b;

  @Trace
  public void test() {
    System.out.println("A.test");
  }
}