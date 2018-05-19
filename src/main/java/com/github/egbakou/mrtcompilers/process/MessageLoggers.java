package com.github.egbakou.mrtcompilers.process;

import com.github.egbakou.mrtcompilers.process.stream.slf4j.Level;
import org.slf4j.Logger;

/**
 * Contains {@link MessageLogger} instances for various log levels.
 */
public class MessageLoggers {

  public static final MessageLogger NOP = new MessageLogger() {
    public void message(Logger log, String format, Object... arguments) {
      // do nothing
    }
  };

  public static final MessageLogger TRACE = new MessageLogger() {
    public void message(Logger log, String format, Object... arguments) {
      log.trace(format, arguments);
    }
  };

  public static final MessageLogger DEBUG = new MessageLogger() {
    public void message(Logger log, String format, Object... arguments) {
      log.debug(format, arguments);
    }
  };

  public static final MessageLogger INFO = new MessageLogger() {
    public void message(Logger log, String format, Object... arguments) {
      log.info(format, arguments);
    }
  };

  public static final MessageLogger get(Level level) {
    switch (level) {
    case TRACE: return TRACE;
    case DEBUG: return DEBUG;
    case INFO: return INFO;
    default:
      throw new IllegalArgumentException("Invalid level " + level);
    }
  }

  private MessageLoggers() {
    // hide
  }

}
