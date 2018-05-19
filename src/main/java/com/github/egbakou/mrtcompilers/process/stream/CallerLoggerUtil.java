package com.github.egbakou.mrtcompilers.process.stream;

/**
 * Constructs name for the caller logger.
 *
 */
public abstract class CallerLoggerUtil {

  /**
   * Returns full name for the caller class' logger.
   *
   * @param name name of the logger. In case of full name (it contains dots) same value is just returned.
   * In case of short names (no dots) the given name is prefixed by caller's class name and a dot.
   * In case of <code>null</code> the caller's class name is just returned.
   * @return full name for the caller class' logger.
   */
  public static String getName(String name) {
    return getName(name, 1);
  }

  /**
   * Returns full name for the caller class' logger.
   *
   * @param name name of the logger. In case of full name (it contains dots) same value is just returned.
   * In case of short names (no dots) the given name is prefixed by caller's class name and a dot.
   * In case of <code>null</code> the caller's class name is just returned.
   * @param level no of call stack levels to get the caller (0 means the caller of this method).
   * @return full name for the caller class' logger.
   */
  public static String getName(String name, int level) {
    level++;
    String fullName;
    if (name == null)
      fullName = getCallerClassName(level);
    else if (name.contains("."))
      fullName = name;
    else
      fullName = getCallerClassName(level) + "." + name;
    return fullName;
  }

  /**
   * @return caller class name of the given level.
   */
  private static String getCallerClassName(int level) {
    return Thread.currentThread().getStackTrace()[level + 2].getClassName();
  }

}
