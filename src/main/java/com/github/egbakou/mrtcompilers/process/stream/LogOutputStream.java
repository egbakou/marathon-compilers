package com.github.egbakou.mrtcompilers.process.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Base class to connect a logging system to the output and/or
 * error stream of then external process. The implementation
 * parses the incoming data to construct a line and passes
 * the complete line to an user-defined implementation.
 */
public abstract class LogOutputStream extends OutputStream {

  /** Initial buffer size. */
  private static final int INTIAL_SIZE = 132;

  /** Carriage return */
  private static final int CR = 0x0d;

  /** Linefeed */
  private static final int LF = 0x0a;

  /** the internal buffer */
  private final ByteArrayOutputStream buffer = new ByteArrayOutputStream(
      INTIAL_SIZE);

  byte lastReceivedByte;

  /**
   * Write the data to the buffer and flush the buffer, if a line separator is
   * detected.
   *
   * @param cc data to log (byte).
   * @see java.io.OutputStream#write(int)
   */
  public void write(final int cc) throws IOException {
    final byte c = (byte) cc;
    if ((c == '\n') || (c == '\r')) {
      // new line is started in case of
      // - CR (regardless of previous character)
      // - LF if previous character was not CR and not LF
      if (c == '\r' || (c == '\n' && (lastReceivedByte != '\r' && lastReceivedByte != '\n'))) {
        processBuffer();
      }
    } else {
      buffer.write(cc);
    }
    lastReceivedByte = c;
  }

  /**
   * Flush this log stream.
   *
   * @see java.io.OutputStream#flush()
   */
  public void flush() {
    if (buffer.size() > 0) {
      processBuffer();
    }
  }

  /**
   * Writes all remaining data from the buffer.
   *
   * @see java.io.OutputStream#close()
   */
  public void close() throws IOException {
    if (buffer.size() > 0) {
      processBuffer();
    }
    super.close();
  }

  /**
   * Write a block of characters to the output stream
   *
   * @param b the array containing the data
   * @param off the offset into the array where data starts
   * @param len the length of block
   * @throws java.io.IOException if the data cannot be written into the stream.
   * @see java.io.OutputStream#write(byte[], int, int)
   */
  public void write(final byte[] b, final int off, final int len)
      throws IOException {
    // find the line breaks and pass other chars through in blocks
    int offset = off;
    int blockStartOffset = offset;
    int remaining = len;
    while (remaining > 0) {
      while (remaining > 0 && b[offset] != LF && b[offset] != CR) {
        offset++;
        remaining--;
      }
      // either end of buffer or a line separator char
      int blockLength = offset - blockStartOffset;
      if (blockLength > 0) {
        buffer.write(b, blockStartOffset, blockLength);
        lastReceivedByte = 0;
      }
      while (remaining > 0 && (b[offset] == LF || b[offset] == CR)) {
        write(b[offset]);
        offset++;
        remaining--;
      }
      blockStartOffset = offset;
    }
  }

  /**
   * Converts the buffer to a string and sends it to <code>processLine</code>.
   */
  protected void processBuffer() {
    processLine(buffer.toString());
    buffer.reset();
  }

  /**
   * Logs a line to the log system of the user.
   *
   * @param line
   *            the line to log.
   */
  protected abstract void processLine(String line);

}
