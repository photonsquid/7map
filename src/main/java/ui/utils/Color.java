package ui.utils;

public class Color {
   private int r;
   private int g;
   private int b;
   
   
   public Color(String hexaColor){
      final String EXAMPLE_TEST = hexaColor;
      System.out.println(EXAMPLE_TEST.matches("\\w.*"));
      // String[] splitString = (EXAMPLE_TEST.split("\\s+"));
      // System.out.println(splitString.length);// should be 14
      // for (String string : splitString) {
      //    System.out.println(string);
      // }
      // // replace all whitespace with tabs
      // System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "\t"));
   }
}
