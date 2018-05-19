package com.github.egbakou.mrtcompilers.process.listener;

import com.github.egbakou.mrtcompilers.process.ProcessExecutor;
import com.github.egbakou.mrtcompilers.process.ProcessResult;

/**
 * Event handler for process events.
 * <p>
 * This is a class instead of interface in order to add new methods without updating all implementations.
 *
 * @see ProcessExecutor#addListener(ProcessListener)
 */
public abstract class ProcessListener {

  /**
   * Invoked before a process is started.
   *
   * @param executor executor used for starting a process.
   *    Any changes made here apply to the starting process.
   *    Once the process has started it is not affected by the {@link ProcessExecutor} any more.
   */
  public void beforeStart(ProcessExecutor executor) {
    // do nothing
  }

  /**
   * Invoked after a process has started.
   *
   * @param process the process started.
   * @param executor executor used for starting the process.
   *    Modifying the {@link ProcessExecutor} only affects the following processes
   *    not the one just started.
   */
  public void afterStart(Process process, ProcessExecutor executor) {
    // do nothing
  }

  /**
   * Invoked after a process has finished successfully.
   *
   * @param process process just finished.
   * @param result result of the finished process.
   * @since 1.8
   */
  public void afterFinish(Process process, ProcessResult result) {
    // do nothing
  }

  /**
   * Invoked after a process has exited (whether finished or cancelled).
   *
   * @param process process just stopped.
   */
  public void afterStop(Process process) {
    // do nothing
  }

}
