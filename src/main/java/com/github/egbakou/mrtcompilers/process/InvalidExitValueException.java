package com.github.egbakou.mrtcompilers.process;

/**
 * Process finished with a forbidden exit value.
 *
 * @see ProcessExecutor#exitValues(Integer...)
 */
public class InvalidExitValueException extends InvalidResultException {

  private static final long serialVersionUID = 1L;

  /**
   * @param message the detail message of the exception
   * @param result result of execution (contains also the exit value)
   */
  public InvalidExitValueException(String message, ProcessResult result) {
    super(message, result);
  }

}
