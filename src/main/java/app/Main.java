package app;

import app.config.AppProperties;
import app.service.DependencyService;
import app.service.readers.MavenReaderService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        MavenReaderService mavenReaderService = new MavenReaderService();
        DependencyService dependencyService = new DependencyService(mavenReaderService);

        Map<String, List<String>> result = (dependencyService.findPrintableDepTreeDiff(Paths.get(AppProperties.getProperty("maven.source.project")),
                Paths.get(AppProperties.getProperty("maven.target.project"))));

        result.forEach((e, f) -> {
            System.out.println(e);
            System.out.println();
            f.forEach(System.out::println);
            System.out.println();
            System.out.println();
        });
    }
}
