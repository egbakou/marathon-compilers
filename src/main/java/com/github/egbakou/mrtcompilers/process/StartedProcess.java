package com.github.egbakou.mrtcompilers.process;

import java.util.concurrent.Future;

/**
 * Represents a process that has started. It may or may not have finished.
 *
 * @author Rein Raudjärv
 */
public class StartedProcess {

  /**
   * The sub process started.
   */
  private final Process process;

  /**
   * The asynchronous result of the started process.
   */
  private final Future<ProcessResult> future;

  public StartedProcess(Process process, Future<ProcessResult> future) {
    this.process = process;
    this.future = future;
  }

  /**
   * @return the started process.
   */
  public Process getProcess() {
    return process;
  }

  /**
   * @return asynchronous result of the started process.
   */
  public Future<ProcessResult> getFuture() {
    return future;
  }

  /**
   * @return the started process.
   * @deprecated use {@link #getProcess()} instead.
   */
  public Process process() {
    return getProcess();
  }

  /**
   * @return asynchronous result of the started process.
   * @deprecated use {@link #getFuture()} instead.
   */
  public Future<ProcessResult> future() {
    return getFuture();
  }

}
