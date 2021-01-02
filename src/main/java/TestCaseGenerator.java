import java.io.IOException;

public class TestCaseGenerator {
    public void generateTests(String configPath) throws IOException {
        ConfigurationBuilderByYAML builderByYAML = new ConfigurationBuilderByYAML(configPath);
        Configuration config = builderByYAML.build();
        TestCaseWriter TCWriter = new TestCaseWriter();
        TCWriter.writeTestCases(config);
    }
}
