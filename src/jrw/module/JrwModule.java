package jrw.module;

/**
 * Represents a module that processes input and returns a string.
 */
public interface JrwModule {
    /**
     * Processes the given input and returns the processed result as a string.
     *
     * @param input The input string to be processed.
     * @return The processed result as a string.
     */
    String process(String input);
}
