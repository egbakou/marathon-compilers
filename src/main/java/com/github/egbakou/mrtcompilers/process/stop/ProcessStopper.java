package com.github.egbakou.mrtcompilers.process.stop;

import java.util.concurrent.Future;

/**
 * Abstraction for stopping sub processes.
 * <p>
 * This is used in case a process runs too long (timeout is reached) or it's cancelled via {@link Future#cancel(boolean)}.
 */
public interface ProcessStopper {

  /**
   * Stops a given sub process.
   * It does not wait for the process to actually stop and it has no guarantee that the process terminates.
   *
   * @param process sub process being stopped (not <code>null</code>).
   */
  void stop(Process process);

}
