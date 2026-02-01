package jrw.module.builtin;

import jrw.module.JrwModule;

/**
 * A built-in module that passes through the input without any modifications.
 */
public class PassThroughModule implements JrwModule {

    /**
     * Processes the given input by returning it unchanged.
     *
     * @param input The input string to be processed.
     * @return The same input string as provided.
     */
    @Override
    public String process(String input) {
        return input;
    }
}