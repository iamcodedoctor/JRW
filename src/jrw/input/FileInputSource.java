package jrw.input;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * InputSource implementation that reads text from a file.
 */
public class FileInputSource implements InputSource {
    private final Path path;

    public FileInputSource(Path path) {
        this.path = path;
    }

    @Override
    public String read(){
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (Exception e){
            throw new RuntimeException("Failed to read file: " + path, e);
        }
    }
    
}
