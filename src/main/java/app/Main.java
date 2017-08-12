package app;

import app.service.DependencyService;
import app.service.ReaderService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ReaderService readerService = new ReaderService();
        DependencyService dependencyService = new DependencyService(readerService);
        dependencyService.findUpdatedDependenciesUsingIO();
    }
}
