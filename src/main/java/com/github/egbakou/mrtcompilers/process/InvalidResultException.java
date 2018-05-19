package com.github.egbakou.mrtcompilers.process;

/**
 * Process finished with an unexpected result.
 *
 * @since 1.8
 */
public class InvalidResultException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Actual exit value and process output.
   */
  private final ProcessResult result;

  /**
   * @param message the detail message of the exception
   * @param result result of execution (contains also the exit value)
   */
  public InvalidResultException(String message, ProcessResult result) {
    super(message);
    this.result = result;
  }

  /**
   * @return actual process result.
   */
  public ProcessResult getResult() {
    return result;
  }

  /**
   * @return the exit value of the finished process.
   */
  public int getExitValue() {
    return result.getExitValue();
  }

  /**
   * @return actual process result.
   * @deprecated use {@link #getResult()}
   */
  public ProcessResult result() {
    return getResult();
  }

  /**
   * @return the exit value of the finished process.
   * @deprecated use {@link #getExitValue()}
   */
  public int exitValue() {
    return getExitValue();
  }

}
