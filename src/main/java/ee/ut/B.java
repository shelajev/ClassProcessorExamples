package ee.ut;


public class B {
  private C c;

  @Mute
  public void bar() {
    System.out.println("B.bar");
  }
}
