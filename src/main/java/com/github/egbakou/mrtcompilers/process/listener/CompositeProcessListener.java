package com.github.egbakou.mrtcompilers.process.listener;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.github.egbakou.mrtcompilers.process.ProcessExecutor;
import com.github.egbakou.mrtcompilers.process.ProcessResult;


/**
 * Composite process event handler.
 *
 */
public class CompositeProcessListener extends ProcessListener implements Cloneable {

  private final List<ProcessListener> children = new CopyOnWriteArrayList<ProcessListener>();

  public CompositeProcessListener() {
    // no children
  }

  public CompositeProcessListener(List<ProcessListener> children) {
    this.children.addAll(children);
  }

  /**
   * Add new listener.
   *
   * @param listener listener to be added.
   */
  public void add(ProcessListener listener) {
    children.add(listener);
  }

  /**
   * Remove existing listener.
   *
   * @param listener listener to be removed.
   */
  public void remove(ProcessListener listener) {
    children.remove(listener);
  }

  /**
   * Remove existing listeners of given type or its sub-types.
   *
   * @param type listener type.
   */
  public void removeAll(Class<? extends ProcessListener> type) {
    for (Iterator<ProcessListener> it = children.iterator(); it.hasNext();) {
      if (type.isInstance(it.next()))
        it.remove();
    }
  }

  /**
   * Remove all existing listeners.
   */
  public void clear() {
    children.clear();
  }

  public CompositeProcessListener clone() {
    return new CompositeProcessListener(children);
  }

  @Override
  public void beforeStart(ProcessExecutor executor) {
    for (ProcessListener child : children) {
      child.beforeStart(executor);
    }
  }

  @Override
  public void afterStart(Process process, ProcessExecutor executor) {
    for (ProcessListener child : children) {
      child.afterStart(process, executor);
    }
  }

  @Override
  public void afterFinish(Process process, ProcessResult result) {
    for (ProcessListener child : children) {
      child.afterFinish(process, result);
    }
  }

  @Override
  public void afterStop(Process process) {
    for (ProcessListener child : children) {
      child.afterStop(process);
    }
  }

}
