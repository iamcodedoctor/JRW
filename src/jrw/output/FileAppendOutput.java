package jrw.output;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * OutputTarget implementation that appends text to a file.
 * Creates the file if it does not exist.
 */
public class FileAppendOutput implements OutputTarget {

    private final Path path;

    public FileAppendOutput(Path path) {
        this.path = path;
    }

    @Override
    public void write(String text) {
        try {
            Files.writeString(
                    path,
                    text,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to append file: " + path, e);
        }
    }
}
