package com.sevenmap.data_handler;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {
   public static void main(String args[]) throws IOException{
      Shape shape = new JacksonTester.Circle("CustomCircle", 1);
      String result = new ObjectMapper()
         .writerWithDefaultPrettyPrinter()
         .writeValueAsString(shape);
      System.out.println(result);   
      String json = "{\"name\":\"CustomCircle\",\"radius\":1.0, \"type\":\"circle\"}";
      Circle circle = new ObjectMapper().readerFor(Shape.class).readValue(json);
      System.out.println(circle.name);
   }
   
   @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
   @JsonSubTypes(
      {         
         @JsonSubTypes.Type(value = Square.class, name = "square"),
         @JsonSubTypes.Type(value = Circle.class, name = "circle")
      }
   )
   static class Shape {
      public String name;    
      Shape(String name) {
         this.name = name;
      }
   }
   
   @JsonTypeName("square")
   static class Square extends Shape {
      public double length;
      Square(){
         this(null,0.0);
      }
      Square(String name, double length){
         super(name);
         this.length = length;
      }
   }

   @JsonTypeName("circle")
   static class Circle extends Shape {
      public double radius;  
      Circle(){
         this(null,0.0);
      }
      Circle(String name, double radius){
         super(name);
         this.radius = radius;
      }
   }   
}