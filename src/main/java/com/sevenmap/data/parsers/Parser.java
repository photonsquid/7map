package com.sevenmap.data.parsers;

public abstract class Parser {

  public Parser() {
  }

  // TODO: javadoc
  @SuppressWarnings("unchecked")
  protected static <T> T createObjectFrom(Class<T> className) {
    Object obj = new Object();

    // Initialize it
    try {
      obj = className.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      // TODO: handle exception
    }

    return (T) obj;
  }

}
