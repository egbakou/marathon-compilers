package com.github.egbakou.mrtcompilers.process.stream.slf4j;

import org.slf4j.Logger;
import com.github.egbakou.mrtcompilers.process.stream.LogOutputStream;

/**
 * Output stream that writes to a given {@link Logger}.
 *
 */
public abstract class Slf4jOutputStream extends LogOutputStream {

  protected final Logger log;

  public Slf4jOutputStream(Logger logger) {
    this.log = logger;
  }

  public Logger getLogger() {
    return log;
  }

}
