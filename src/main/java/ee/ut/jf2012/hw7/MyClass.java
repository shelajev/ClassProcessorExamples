package ee.ut.jf2012.hw7;

import java.io.UnsupportedEncodingException;

public class MyClass {
  public String getItem1() throws UnsupportedEncodingException{
    String a = "2";
    a.getBytes();
    a.getBytes("we");
    System.out.println(a);
    int t = Integer.parseInt(a);
    return a;
  }
}