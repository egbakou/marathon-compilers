package com.github.egbakou.mrtcompilers.process;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Standard output of a finished process.
 *
 * @see ProcessExecutor
 */
public class ProcessOutput {

  /**
   * Process output (not <code>null</code>).
   */
  private final byte[] data;

  public ProcessOutput(byte[] data) {
    this.data = data;
  }

  /**
   * @return binary output of the finished process.
   */
  public byte[] getBytes() {
    return data;
  }

  /**
   * @return output of the finished process converted to a String using platform's default encoding.
   */
  public String getString() {
    return new String(getBytes());
  }

  /**
   * @return output of the finished process converted to UTF-8 String.
   */
  public String getUTF8() {
    return getString("UTF-8");
  }

  /**
   * @return output of the finished process converted to a String.
   *
   * @param charset The name of a supported char set.
   */
  public String getString(String charset) {
    try {
      return new String(getBytes(), charset);
    }
    catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * @return output lines of the finished process converted using platform's default encoding.
   */
  public List<String> getLines() {
    return getLinesFrom(getString());
  }

  /**
   * @return output lines of the finished process converted using UTF-8.
   */
  public List<String> getLinesAsUTF8() {
    return getLinesFrom(getUTF8());
  }

  /**
   * @return output lines of the finished process converted using a given char set.
   *
   * @param charset The name of a supported char set.
   */
  public List<String> getLines(String charset) {
    return getLinesFrom(getString(charset));
  }

  static List<String> getLinesFrom(String output) {
    // Split using both Windows and UNIX line separators
    List<String> result = new ArrayList<String>();
    StringTokenizer st = new StringTokenizer(output, "\n\r");
    while (st.hasMoreTokens()) {
      result.add(st.nextToken());
    }
    return result;
  }

}
