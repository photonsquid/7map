package com.sevenmap.data.parsers;

import java.lang.reflect.Method;

public abstract class Parser {

  /**
   * Create an object whose class match className from its default constructor.
   * <br>
   * If there is no default constructor in {@code T}, throws an error (not
   * implemented yet).
   * 
   * @param <T>       type of the object that is created
   * @param className class of the object that has to be created
   * @return an empty object matching conditions.
   */
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

  /**
   * Return a default string if the first parameter is null
   * 
   * @param possibleNullValue string that might be empty
   * @param defaultValue      string that will be used if possibleNullValue is
   *                          empty
   * @return a non-empty string with either possibleNullValue or defaultValue
   */
  protected String getAnnotationValue(String possibleNullValue, String defaultValue) {
    String returnValue = possibleNullValue;
    if (returnValue.equals(""))
      returnValue = defaultValue;
    return returnValue;
  }

  /**
   * Retrieve a value from a property using
   * 
   * @param obj          The object who's property you want to fetch
   * @param property     The property name
   * @param defaultValue A default value to be returned if either a) The property
   *                     is not found or b) if the property is found but the value
   *                     is null
   * @return THe value of the property
   */
  @SuppressWarnings("unchecked")
  protected <T> T getProperty(Object obj, String property, T defaultValue) {

    T returnValue = (T) getProperty(obj, property);
    if (returnValue == null) {
      returnValue = defaultValue;
    }

    return returnValue;

  }

  /**
   * Fetch a property from an object. For example of you wanted to get the foo
   * property on a bar object you would normally call {@code bar.getFoo()}. This
   * method lets you call it like {@code BeanUtil.getProperty(bar, "foo")}
   * 
   * @param obj      The object who's property you want to fetch
   * @param property The property name
   * @return The value of the property or null if it does not exist.
   */
  protected Object getProperty(Object obj, String property) {
    Object returnValue = null;

    try {
      String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
      Class<?> clazz = obj.getClass();
      Method method = clazz.getMethod(methodName, null);
      returnValue = method.invoke(obj, null);
    } catch (Exception e) {
      // Do nothing, we'll return the default value
    }

    return returnValue;
  }

}
