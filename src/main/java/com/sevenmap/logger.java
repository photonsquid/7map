package com.sevenmap;
import java.io.IOException;
//import java.util.logging.FileHandler;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;
//import java.io.PrintStream;
//import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.util.*;
public class logger {

    //List<String> L = new ArrayList<String>();        
    public void log(String niveau ,String format ,Object ... args) throws IOException{
        if (niveau == "File"){
            FileWriter writer = new FileWriter("7map.txt");
            PrintWriter printer = new PrintWriter(writer);
            printer.printf(format, args);
            printer.close();
            writer.close();
        }else if(niveau == "Console"){
            System.out.printf(format, args);
        }else{
            System.out.println("no output as you are in message free mode");
        }
    }
     
}
