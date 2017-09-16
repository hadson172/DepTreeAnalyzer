package app;

import app.config.AppProperties;
import app.service.DependencyService;
import app.service.readers.MavenReaderService;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        MavenReaderService mavenReaderService = new MavenReaderService();
        DependencyService dependencyService = new DependencyService(mavenReaderService);
        dependencyService.findUpdatedDependencies(Paths.get(AppProperties.getProperty("maven.source.project")),
                Paths.get(AppProperties.getProperty("maven.target.project"))).forEach(System.out::println);
    }
}
