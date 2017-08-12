package app.service;

import app.model.Scope;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class ScopeTest {

    private String strScope;
    private Scope scope;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"compile", Scope.COMPILE},
                {"provided", Scope.PROVIDED},
                {"runtime", Scope.RUNTIME},
                {"test", Scope.TEST},
                {"system", Scope.SYSTEM},
                {"import", Scope.IMPORT}
        });
    }

    @Test
    public void shouldParseValidScope() {
        assertThat(Scope.of(strScope), equalTo(scope));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailParserIfGivenScopeDoesNotExists() {
        Scope.of("invalidScope");
    }
}