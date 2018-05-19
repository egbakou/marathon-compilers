package com.github.egbakou.mrtcompilers.process;

/**
 * Exit value and output of a finished process.
 * @see ProcessExecutor
 */
public class ProcessResult {

  /**
   * Exit value of the finished process.
   */
  private final int exitValue;

  /**
   * Process output or <code>null</code> if it wasn't read.
   */
  private final ProcessOutput output;

  public ProcessResult(int exitCode, ProcessOutput output) {
    this.exitValue = exitCode;
    this.output = output;
  }

  /**
   * @return the exit value of the finished process.
   */
  public int getExitValue() {
    return exitValue;
  }

  /**
   * @return the exit value of the finished process.
   * @deprecated use {@link #getExitValue()}
   */
  public int exitValue() {
    return getExitValue();
  }

  /**
   * @return <code>true</code> if the process output was read.
   */
  public boolean hasOutput() {
    return output != null;
  }

  /**
   * @return output of the finished process.
   * You have to invoke {@link ProcessExecutor#readOutput(boolean)} to set the process output to be read.
   *
   * @throws IllegalStateException if the output was not read.
   */
  public ProcessOutput getOutput() {
    if (output == null)
      throw new IllegalStateException("Process output was not read. To enable output reading please call ProcessExecutor.readOutput(true) before starting the process.");
    return output;
  }

  /**
   * @return binary output of the finished process.
   * You have to invoke {@link ProcessExecutor#readOutput(boolean)} to set the process output to be read.
   *
   * @throws IllegalStateException if the output was not read.
   */
  public byte[] output() {
    return getOutput().getBytes();
  }

  /**
   * @return output of the finished process converted to a String using platform's default encoding.
   * You have to invoke {@link ProcessExecutor#readOutput(boolean)} to set the process output to be read.
   *
   * @throws IllegalStateException if the output was not read.
   */
  public String outputString() {
    return getOutput().getString();
  }

  /**
   * @return output of the finished process converted to UTF-8 String.
   * You have to invoke {@link ProcessExecutor#readOutput(boolean)} to set the process output to be read.
   *
   * @throws IllegalStateException if the output was not read.
   */
  public String outputUTF8() {
    return getOutput().getUTF8();
  }

  /**
   * @return output of the finished process converted to a String.
   * You have to invoke {@link ProcessExecutor#readOutput(boolean)} to set the process output to be read.
   *
   * @param charset The name of a supported char set.
   * @throws IllegalStateException if the output was not read or the char set was not supported.
   */
  public String outputString(String charset) {
    return getOutput().getString(charset);
  }

}
