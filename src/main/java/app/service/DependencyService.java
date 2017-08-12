package app.service;

import app.model.Dependency;
import app.model.Scope;
import app.model.Version;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DependencyService {
    private static final Pattern DEP_PATTERN = Pattern.compile("([^:]+):([^:]+):([^:]+):([^:]+):([^:]+)");
    private static final Pattern RELEASE_PATTERN = Pattern.compile("([^-]+)-RELEASE");
    private static final Pattern SNAPSHOT_PATTERN = Pattern.compile("([^-]+)-SNAPSHOT");
    //private static final Pattern REDUNDANT_CHARS = Pattern.compile("\\[INFO]|\\s*|\\+-|");

    private final ReaderService readerService;

    public DependencyService(ReaderService readerService) {
        this.readerService = readerService;
    }

    public List<Dependency> getSourceDependency() {
        return readerService.getSourceDependencies().stream()
                .map(this::dependencyOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void findUpdatedDependenciesUsingIO() {
        findUpdatedDependencies(readerService.getTargetDependencies())
                .forEach(System.out::println);
    }

    public List<String> findUpdatedDependencies(List<String> targetDependenciesStr) {
        List<Dependency> targetDependencies = targetDependenciesStr.stream()
                .map(this::dependencyOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());


        return getSourceDependency().stream()
                .map(source -> targetDependencies.stream().filter(target -> isUpdated(source, target)).map(target -> mapToUpdatedDependencies(source, target)).findAny().orElse(""))
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());
    }

    public Optional<Dependency> dependencyOf(String dependency) {
        dependency = cleanDependenciesString(dependency);
        if (dependency.isEmpty()) return Optional.empty();

        Matcher matcher = DEP_PATTERN.matcher(dependency);
        if (!matcher.matches()) throw new IllegalArgumentException("Invalid dependency format");

        return Optional.of(Dependency.newInstance(matcher.group(1), matcher.group(2), versionOf(matcher.group(4)), Scope.of(matcher.group(5))));
    }


    private String cleanDependenciesString(String dependency) {
        //TODO Replace these nasty expressions with some kind regex
        return dependency.replaceAll("\\[INFO]","")
                .replaceAll(" ", "")
                .replaceAll(Pattern.quote("\\-"),"")
                .replaceAll("\\|","")
                .replaceAll("\\+-","")
                .replaceAll("(.*omitted.*)","");
    }

    public Version versionOf(String version) {
        Matcher releaseMatcher = RELEASE_PATTERN.matcher(version);
        Matcher snapshotMatcher = SNAPSHOT_PATTERN.matcher(version);

        if (releaseMatcher.matches()) return Version.newRelease(releaseMatcher.group(1));
        else if (snapshotMatcher.matches()) {
            return Version.newSnapshot(snapshotMatcher.group(1));
        }

        return Version.newVersion(version);
    }


    public boolean isUpdated(Dependency sourceDependency, Dependency targetDependency) {
        return sourceDependency.getGroupId().equals(targetDependency.getGroupId()) &&
                sourceDependency.getArtifactId().equals(targetDependency.getArtifactId()) &&
                (!sourceDependency.getVersion().equals(targetDependency.getVersion()) || !sourceDependency.getScope().equals(targetDependency.getScope()));
    }

    private String mapToUpdatedDependencies(Dependency sourceDependency, Dependency targetDependency) {
        StringBuilder resultBuilder = new StringBuilder(sourceDependency.getGroupId())
                .append(":")
                .append(sourceDependency.getArtifactId())
                .append(":")
                .append(sourceDependency.getVersion().getVersion().equals(targetDependency.getVersion().getVersion()) ? sourceDependency.getVersion().getVersion() : "[" + sourceDependency.getVersion().getVersion() + "----->>" + targetDependency.getVersion().getVersion() + "]")
                .append("-")
                .append(sourceDependency.getVersion().getType().equals(targetDependency.getVersion().getType()) ? sourceDependency.getVersion().getType() : "[" + sourceDependency.getVersion().getType() + "----->>" + targetDependency.getVersion().getType() + "]");

        if (!sourceDependency.getScope().equals(targetDependency.getScope())) {
            return resultBuilder.append("[" + sourceDependency.getScope().getScope() + "----->>" + targetDependency.getScope().getScope() + "]").toString();
        }

        return resultBuilder.append(":")
                .append(sourceDependency.getScope().getScope()).toString();
    }

}
