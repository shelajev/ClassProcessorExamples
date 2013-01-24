package ee.ut.jf2012.hw7;

public class Main {
  public static void main(String[] args) throws Throwable {
    // java -jar target/homework.jar beans.xml A
    if(args.length < 2) {
      new HW().run("beans.xml", "A");
      return;
    }
    try {
       new HW().run(args[0], args[1]);
    }
    catch (Throwable t) {
      System.out.println("Oups! Something went wrong: " + t.getMessage());
      t.printStackTrace(System.out);
    }
  }
}
