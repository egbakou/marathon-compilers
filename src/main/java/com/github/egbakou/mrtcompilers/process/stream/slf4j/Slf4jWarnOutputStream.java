package com.github.egbakou.mrtcompilers.process.stream.slf4j;

import org.slf4j.Logger;

/**
 * Output stream that writes <code>warn</code> level messages to a given {@link Logger}.
 *
 */
public class Slf4jWarnOutputStream extends Slf4jOutputStream {

  public Slf4jWarnOutputStream(Logger logger) {
    super(logger);
  }

  @Override
  protected void processLine(String line) {
    log.warn(line);
  }

}
