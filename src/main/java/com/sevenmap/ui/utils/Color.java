package com.sevenmap.ui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sevenmap.ui.math.Vector3f;

public class Color {
   private int r;
   private int g;
   private int b;

   /**
    * Create a new color from RGB colors (0-255 values)
    * @param r 0-255 Red color
    * @param g 0-255 Green color
    * @param b 0-255 Blue color
    */
   public Color(Integer r,Integer g,Integer b){
      affectRGB(r, g, b);
   }
   
   /**
    * Create a new color from RGB colors (0-1 values)
    * @param r 0-1 Red color
    * @param g 0-1 Green color
    * @param b 0-1 Blue color
    */
   public Color(Double r,Double g,Double b){
      affectRgbFloat(r, g, b);
   }
   
   /**
    * Create a new color : 
    * - Hexa color: begins with a '#', case insensitive. 
    *    Ex: #8E44AD
    *        #1abc9c
    * - rgb color: begins with a 'rgb', then 3 values in parenthesis, separated with coma.
    *    Ex: rgb(41, 128, 185)
    *        rgb(0.75, 0.22, 0.17)
    * - hsl color: begins with a 'hsl', then 3 values in parenthesis, separated with coma:
    *    - hue from 0 to 360
    *    - saturation : from 0 to 100 with % symbol at the end
    *    - lightness : from 0 to 100 with % symbol at the end
    *    Ex: hsl(37, 90%, 51%)
    * @param str String that contains color data
    */
   public Color(String str){
      str = str.replace(" ","");
      String hexaPattern = "#\\w{6}";
      String rgbPattern = "rgb\\([0-9,.]+\\)";
      String hslPattern = "hsl\\(.+\\)";

      if (Pattern.matches(hexaPattern, str)) {
         this.r = parseHex(str, 1);
         this.g = parseHex(str, 3);
         this.b = parseHex(str, 5);
      } else if(Pattern.matches(rgbPattern, str)){
         // s'il y a un point, c'est du rgb en float
         Integer start = 4;
         Integer end = str.length()-1;
         String[] RGB = str.substring(start, end).split(",");
         if (str.contains(".")){
            Double r = Double.parseDouble(RGB[0]);
            Double g = Double.parseDouble(RGB[1]);
            Double b = Double.parseDouble(RGB[2]);
            affectRgbFloat(r,g,b);
         } else {
            Integer r = Integer.parseInt(RGB[0]);
            Integer g = Integer.parseInt(RGB[1]);
            Integer b = Integer.parseInt(RGB[2]);
            affectRGB(r, g, b);
         }
      } else if(Pattern.matches(hslPattern, str)){
         // Biblio :
         // https://stackoverflow.com/questions/2353211/hsl-to-rgb-color-conversion
         Matcher mat = Pattern.compile("hsl\\((\\d+),(\\d+)%,(\\d+)%\\)").matcher(str);
         if (mat.find()) {
            Integer h = Integer.parseInt(mat.group(1));
            Integer s = Integer.parseInt(mat.group(2));
            Integer l = Integer.parseInt(mat.group(3));
            affectHsl(h, s, l);
            
         } else {
            // TODO: return an error
            System.out.println("NO MATCH");
         }

      }
   }
   private void affectRgbFloat(Double r,Double g,Double b){
      affectRGB((int) Math.ceil(r*255),(int) Math.ceil(g*255), (int) Math.ceil(b*255));
   }
   private void affectHsl(Integer hi, Integer si, Integer li){
   Double r;
   Double g;
   Double b;

   Double h = Double.valueOf(hi)/ 360;
   Double s = Double.valueOf(si)/ 100;
   Double l = Double.valueOf(li)/ 100;

    if(s == 0){
        r = g = b = l; // achromatic
    }else{
        Double q = l < 0.5 ? l * (1 + s) : l + s - l * s;
        Double p = 2 * l - q;
        r = hue2rgb(p, q, h + 1f/3f);
        g = hue2rgb(p, q, h);
        b = hue2rgb(p, q, h - 1f/3f);
    }
    affectRGB((int)Math.round(r * 255), (int)Math.round(g * 255), (int)Math.round(b * 255));
   }
   private void affectRGB(Integer r,Integer g,Integer b){
      this.r = r;
      this.g = g;
      this.b = b;
   }
   private int parseHex(String str, Integer start){
      return Integer.parseInt(str.substring(start,start+2),16);
   }
   private Double hue2rgb(Double p, Double q, Double t){
            if(t < 0) t += 1f;
            if(t > 1) t -= 1f;
            if(t < 1f/6f) return p + (q - p) * 6f * t;
            if(t < 1f/2f) return q;
            if(t < 2f/3f) return p + (q - p) * (2f/3f - t) * 6f;
            return p;
   }
   
   /** 
    * Convert a color to a Vector3F with the following properties:
    *  - x: red double color from 0 to 1
    *  - y: green double color from 0 to 1
    *  - z: blue double color from 0 to 1
    * @return Vector3F of colors (r, g, b)
    */ 
   public Vector3f toVector3f(){
      return new Vector3f(this.r/255f, this.g/255f, this.b/255f);
   }

   /**
    * Create a color object from hsl values
    * @param h hue value (0-360)
    * @param s saturation value (0-100)
    * @param l lightness value (0-100)
    * @return a Color object with given color as property
    */
   public static Color fromHsl(Integer h,Integer s,Integer l){
      Color a = new Color(h, s, l);
      a.affectHsl(h, s, l);
      return a;
   }

   /**
    * Return the color as a String
    * @return Color to the following format : rgb(<0-255>, <0-255>, <0-255>)
    */
   @Override
   public String toString(){
      return "rgb("+this.r+","+this.g+","+this.b+")";
   }

   
   /**
    * Test function awaiting a great Test class from @Mmzhk21
    */
   public static void main(String[] args) {
      Color hexaMajColor = new Color("#8E44AD");
      Color hexaMinColor = new Color("#1abc9c");
      Color rgbStringColor = new Color("rgb(41, 128, 185)");
      Color rgbColor = new Color(41, 128, 185); 
      Color rgbfStringColor = new Color("rgb(0.75, 0.22, 0.17)"); // excpected: rgb(192, 57, 43)
      Color rgbfColor = new Color(0.75, 0.22, 0.17); // excpected: rgb(192, 57, 43)
      Color hslStringColor = new Color("hsl(37, 90%, 51%)");
      Color hslColor = Color.fromHsl(37, 90, 51);


      System.out.println("\"#8E44AD\"                 => " + hexaMajColor.toString());
      System.out.println("\"#1abc9c\"                 => " + hexaMinColor.toString());
      System.out.println("\"rgb(41, 128, 185)\"       => " + rgbStringColor.toString());
      System.out.println("41, 128, 185              => " + rgbColor.toString());
      System.out.println("\"rgb(0.75, 0.22, 0.17)\"   => " + rgbfStringColor.toString());
      System.out.println("0.75, 0.22, 0.17          => " + rgbfColor.toString());
      System.out.println("\"hsl(37, 90%, 51%)\"       => " + hslStringColor.toString());
      System.out.println("fromHsl(37, 90, 51)       => " + hslColor.toString());
   }

}
