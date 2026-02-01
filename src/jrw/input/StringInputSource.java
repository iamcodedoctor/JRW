package jrw.input;

/**
 * InputSource implementation backed by an in-memory string.
 *
 * Used for direct string input (e.g. CLI -s option).
 */
public class StringInputSource implements InputSource {
    private final String text;

     /**
     * Constructs a new StringInputSource with the given text.
     *
     * @param text The text to be read by this input source.
     */
    public StringInputSource(String text) {
        this.text = text;
    }

    /**
     * Reads the text from this input source.
     *
     * @return The text content.
     */
    @Override
    public String read(){
        return text;
    }

}
