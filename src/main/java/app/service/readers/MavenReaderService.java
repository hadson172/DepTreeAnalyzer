package app.service.readers;

import app.config.AppProperties;
import org.apache.maven.shared.invoker.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class MavenReaderService implements ReaderService {
    private static final String POM_XML = "pom.xml";

    //Maven goals constant
    private static final String DEPENDENCY_TREE = "dependency:tree";
    private static final String DEPENDENCY_TREE_VERBOSE = "dependency:tree -Dverbose";

    public MavenReaderService() {
        System.setProperty("maven.home", AppProperties.getProperty("maven.home"));
    }

    @Override
    public Stream<String> getDependencyTree(Path path) {
        Path sourcePomPath = Paths.get(path.toString(), POM_XML);
        return executeMavenGoal(Collections.singletonList(DEPENDENCY_TREE), sourcePomPath);
    }

    public Stream<String> executeMavenGoal(List<String> goals, Path pathToPomFile) {
        InvocationRequest invocationRequest = new DefaultInvocationRequest();
        invocationRequest.setPomFile(pathToPomFile.toFile());
        invocationRequest.setGoals(goals);

        Stream.Builder<String> builder = Stream.builder();

        invocationRequest.setOutputHandler(builder::accept);

        Invoker invoker = new DefaultInvoker();

        try {
            invoker.execute(invocationRequest);
            return builder.build();
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }

        return Stream.empty();

    }
}
