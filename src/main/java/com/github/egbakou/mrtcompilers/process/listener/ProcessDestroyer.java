package com.github.egbakou.mrtcompilers.process.listener;

/**
 * Destroys all registered {@link java.lang.Process} after a certain event,
 * typically when the VM exits 
 * @see ShutdownHookProcessDestroyer
 */
public interface ProcessDestroyer {

  /**
   * Returns <code>true</code> if the specified 
   * {@link java.lang.Process} was
   * successfully added to the list of processes to be destroy.
   * 
   * @param process
   *      the process to add
   * @return <code>true</code> if the specified 
   * 		{@link java.lang.Process} was
   *      successfully added
   */
  boolean add(Process process);

  /**
   * Returns <code>true</code> if the specified 
   * {@link java.lang.Process} was
   * successfully removed from the list of processes to be destroy.
   * 
   * @param process
   *            the process to remove
   * @return <code>true</code> if the specified 
   * 		{@link java.lang.Process} was
   *      successfully removed
   */
  boolean remove(Process process);

  /**
   * Returns the number of registered processes.
   *
   * @return the number of register process
   */
  int size();
}
