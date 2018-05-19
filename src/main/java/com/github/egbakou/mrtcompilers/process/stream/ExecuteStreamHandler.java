package com.github.egbakou.mrtcompilers.process.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Used by <code>Execute</code> to handle input and output stream of
 * subprocesses.
 */
public interface ExecuteStreamHandler {

  /**
   * Install a handler for the input stream of the subprocess.
   *
   * @param os
   *            output stream to write to the standard input stream of the
   *            subprocess
   * @throws IOException throws a IO exception in case of IO issues of the underlying stream
   */
  void setProcessInputStream(OutputStream os) throws IOException;

  /**
   * Install a handler for the error stream of the subprocess.
   *
   * @param is
   *            input stream to read from the error stream from the subprocess
   * @throws IOException throws a IO exception in case of IO issues of the underlying stream
   */
  void setProcessErrorStream(InputStream is) throws IOException;

  /**
   * Install a handler for the output stream of the subprocess.
   *
   * @param is
   *            input stream to read from the error stream from the subprocess
   * @throws IOException throws a IO exception in case of IO issues of the underlying stream
   */
  void setProcessOutputStream(InputStream is) throws IOException;

  /**
   * Start handling of the streams.
   *
   * @throws IOException throws a IO exception in case of IO issues of the underlying stream
   */
  void start() throws IOException;

  /**
   * Stop handling of the streams - will not be restarted.
   * Will wait for pump threads to complete.
   */
  void stop();
}
