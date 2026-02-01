package jrw.input;

/**
 * Represents a source of input text.
 *
 * Implementations may read from:
 * - a string
 * - a file
 * - standard input
 *
 * This interface does not define *how* the text is obtained,
 * only that text can be read.
 */
public interface InputSource {

    /**
     * Read all available input as text.
     *
     * @return the input text
     */
    String read();
}
