package app.model;

import lombok.Getter;

public enum Scope {
    COMPILE("compile"),
    PROVIDED("provided"),
    RUNTIME("runtime"),
    TEST("test"),
    SYSTEM("system"),
    IMPORT("import");

    @Getter
    private final String scope;

    Scope(String scope) {
        this.scope = scope;
    }

    public static Scope of(String scope) {
        return Scope.valueOf(scope.toUpperCase());
    }
}
