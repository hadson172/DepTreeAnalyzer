package app.service;

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class DependencyServiceTest {
//
//    private static final String DEP_STR_1 = "[INFO] +- org.projectlombok:lombok:jar:1.16.18:provided";
//    private static final Dependency DEP_1 = Dependency.newInstance("org.projectlombok", "lombok", Version.newVersion("1.16.18"), Scope.PROVIDED);
//
//    private static final String DEP_STR_2 = "[INFO] |  \\- org.hamcrest:hamcrest-core:jar:1.3:compile";
//    private static final Dependency DEP_2 = Dependency.newInstance("org.hamcrest", "hamcrest-core", Version.newVersion("1.3"), Scope.COMPILE);
//
//    private static final String DEP_STR_3 = "[INFO]    +- (org.hamcrest:hamcrest-core:jar:1.1-RELEASE:runtime - omitted for conflict with 1.3)";
//    private static final Dependency DEP_3 = null;
//
//    private static final String DEP_STR_4 = "[INFO]    +- org.hamcrest:hamcrest-core:jar:1.1-SNAPSHOT:runtime";
//    private static final Dependency DEP_4 = Dependency.newInstance("org.hamcrest", "hamcrest-core", Version.newSnapshot("1.1"), Scope.RUNTIME);
//
//    private static final String DEP_STR_5 = "[INFO]    \\- org.hamcrest:hamcrest-core:jar:1.1-RELEASE:runtime";
//    private static final Dependency DEP_5 = Dependency.newInstance("org.hamcrest", "hamcrest-core", Version.newRelease("1.1"), Scope.RUNTIME);
//
//    private static final String INVALID_DEP = "INVALID_DEP-::-RELEASE";
//
//    @Mock
//    private static InputReaderService inputReaderService;
//    private static DependencyService dependencyService;
//
//    @Before
//    public void init() {
//        initMocks(this);
//        when(inputReaderService.getDependencyTree()).thenReturn(Arrays.asList(DEP_STR_1, DEP_STR_2, DEP_STR_3, DEP_STR_4, DEP_STR_5));
//        dependencyService = new DependencyService(inputReaderService);
//    }
//
//
//    @RunWith(Parameterized.class)
//    public static class DependencyParserTest {
//
//        private String dependencyStr;
//        private Dependency dependency;
//
//        public DependencyParserTest(String dependencyStr, Dependency dependency) {
//            this.dependencyStr = dependencyStr;
//            this.dependency = dependency;
//        }
//
//        @Parameters
//        public static Collection<Object[]> data() {
//            return Arrays.asList(new Object[][]{
//                    {DEP_STR_1, DEP_1},
//                    {DEP_STR_2, DEP_2},
//                    {DEP_STR_3, DEP_3},
//                    {DEP_STR_4, DEP_4},
//                    {DEP_STR_5, DEP_5}
//            });
//        }
//
//        @Before
//        public void init() {
//            dependencyService = new DependencyService(mock(InputReaderService.class));
//        }
//
//        @Test
//        public void shouldParseValidDependency() {
//            assertThat(dependencyService.dependencyOf(dependencyStr), equalTo(Optional.ofNullable(dependency)));
//        }
//    }
//
//    @RunWith(MockitoJUnitRunner.class)
//    public static class DependencyTest {
//
//        @Before
//        public void init() {
//            inputReaderService = mock(InputReaderService.class);
//            when(inputReaderService.getDependencyTree()).thenReturn(Arrays.asList(DEP_STR_1, DEP_STR_2, DEP_STR_3, DEP_STR_4, DEP_STR_5));
//            dependencyService = new DependencyService(inputReaderService);
//        }
//
//
//        @Test
//        public void shouldParseListFromFile() {
//            List<Dependency> dependencies = dependencyService.getSourceDependency();
//            assertThat(dependencies.size(), equalTo(4));
//            assertTrue(dependencies.containsAll(Arrays.asList(DEP_1, DEP_2, DEP_4, DEP_5)));
//        }
//
//        @Test(expected = IllegalArgumentException.class)
//        public void shouldThrowExceptionIsInvalidDependencySyntax() {
//            dependencyService.dependencyOf(INVALID_DEP);
//        }
//
//    }


}