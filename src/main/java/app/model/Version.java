package app.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Version {
    private final String version;
    private final Type type;

    public static Version newRelease(String version) {
        return new Version(version, Type.RELEASE);
    }

    public static Version newSnapshot(String version) {
        return new Version(version, Type.SNAPSHOT);
    }

    public static Version newVersion(String version) {
        return new Version(version, Type.NONE);
    }

    public enum Type {
        RELEASE("RELEASE"),
        SNAPSHOT("SNAPSHOT"),
        NONE("");

        @Getter
        private final String type;

        Type(String type) {
            this.type = type;
        }
    }
}
