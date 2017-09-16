package app.service.readers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InputReaderService implements ReaderService {
    private static final String SOURCE_FILENAME = "sourceDependency.txt";
    private final File source;

    public InputReaderService() throws IOException {
        this.source = Paths.get(SOURCE_FILENAME).toFile();

        if (!source.exists()) {
            source.createNewFile();
        }
    }

    public Stream<String> getDependencyTree(Path path) {
        Stream<String> dependencies = Stream.empty();
        try (Stream<String> lines = Files.lines(Paths.get(path.toString(), SOURCE_FILENAME))) {
            return lines;

        } catch (IOException ex) {
            ex.printStackTrace();
            return dependencies;
        }
    }


}
