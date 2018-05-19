package com.github.egbakou.mrtcompilers.process.listener;

import com.github.egbakou.mrtcompilers.process.ProcessExecutor;

/**
 * Process event handler that wraps a process destroyer.
 *
 * @see ProcessDestroyer
 */
public class DestroyerListenerAdapter extends ProcessListener {

  private final ProcessDestroyer destroyer;

  public DestroyerListenerAdapter(ProcessDestroyer destroyer) {
    if (destroyer == null)
      throw new IllegalArgumentException("Process destroyer must be provided.");
    this.destroyer = destroyer;
  }

  @Override
  public void afterStart(Process process, ProcessExecutor executor) {
    destroyer.add(process);
  }

  @Override
  public void afterStop(Process process) {
    destroyer.remove(process);
  }

}
