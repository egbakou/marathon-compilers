package com.github.egbakou.mrtcompilers.process.stream.slf4j;

import org.slf4j.Logger;

/**
 * Output stream that writes <code>info</code> level messages to a given {@link Logger}.
 *
 */
public class Slf4jInfoOutputStream extends Slf4jOutputStream {

  public Slf4jInfoOutputStream(Logger logger) {
    super(logger);
  }

  @Override
  protected void processLine(String line) {
    log.info(line);
  }

}
