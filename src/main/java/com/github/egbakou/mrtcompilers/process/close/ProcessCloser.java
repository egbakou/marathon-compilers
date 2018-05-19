package com.github.egbakou.mrtcompilers.process.close;

import java.io.IOException;

/**
 * Abstraction for closing sub process' streams.
 */
public interface ProcessCloser {

  /**
   * Closes standard streams of a given sub process.
   *
   * @param process sub process (not <code>null</code>).
   * @throws IOException if I/O errors occur while closing the underlying stream
   * @throws InterruptedException if underlying throws a InterruptedException
   */
  void close(Process process) throws IOException, InterruptedException;

}
