package app.service.readers;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface ReaderService {

    Stream<String> getDependencyTree(Path path);
}
