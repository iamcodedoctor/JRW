package jrw.input;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * InputSource implementation that reads from standard input (STDIN).
 *
 * Used when no explicit input source is provided.
 */
public class StdInInputSource implements InputSource {

    @Override
    public String read() {
        try {
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(System.in, StandardCharsets.UTF_8)
                    );

            return reader
                    .lines()
                    .collect(Collectors.joining("\n"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to read from standard input", e);
        }
    }
}
