package jrw.output;

/**
 * This class represents a console output target.
 * It writes the given text to the standard output (console).
 */
public class ConsoleOutput implements OutputTarget {
    
    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
