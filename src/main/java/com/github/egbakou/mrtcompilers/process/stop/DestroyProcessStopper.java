package com.github.egbakou.mrtcompilers.process.stop;

/**
 * Default {@link ProcessStopper} implementation that just invokes {@link Process#destroy()}.
 */
public class DestroyProcessStopper implements ProcessStopper {

  /**
   * Singleton instance of the {@link DestroyProcessStopper}.
   */
  public static final ProcessStopper INSTANCE = new DestroyProcessStopper();

  public void stop(Process process) {
    process.destroy();
  }

}
