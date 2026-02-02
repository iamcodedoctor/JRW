package jrw.output;

/**
 * Represents a destination for output text.
 *
 * Implementations may write to:
 * - console
 * - file (overwrite)
 * - file (append)
 */
public interface OutputTarget {

     /**
     * Write the given text to this output target.
     *
     * @param text the text to write
     */
    void write(String text);
}
