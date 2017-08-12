package app.model;


import lombok.Data;


@Data(staticConstructor = "newInstance")
public class Dependency {
    private final String groupId;
    private final String artifactId;
    private final Version version;
    private final Scope scope;

}
