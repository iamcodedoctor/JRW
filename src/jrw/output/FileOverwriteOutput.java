package jrw.output;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * OutputTarget implementation that writes text to a file,
 * overwriting any existing content.
 */
public class FileOverwriteOutput implements OutputTarget {

    private final Path path;

    public FileOverwriteOutput(Path path) {
        this.path = path;
    }

    @Override
    public void write(String text) {
        try {
            Files.writeString(path, text, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write file: " + path, e);
        }
    }
}
