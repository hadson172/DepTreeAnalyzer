package app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderService {
    private static final String SOURCE_FILENAME = "sourceDependency.txt";
    private final File source;

    public ReaderService() throws IOException {
        this.source = Paths.get(SOURCE_FILENAME).toFile();

        if (!source.exists()) {
            source.createNewFile();
        }
    }

    public List<String> getSourceDependencies() {
        List<String> dependencies = new ArrayList<>();
        try (Stream<String> lines = Files.lines(source.toPath())) {
            return lines.collect(Collectors.toList());

        } catch (IOException ex) {
            ex.printStackTrace();
            return dependencies;
        }
    }

    public List<String> getTargetDependencies() {
        Scanner in = new Scanner(System.in);
        String line;
        List<String> targetDependencies = new ArrayList<>();

        while (!(line = in.nextLine()).equals("")) {
            targetDependencies.add(line);
        }

        return targetDependencies;
    }


}
