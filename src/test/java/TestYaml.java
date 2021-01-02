import org.junit.Test;
import testGenerator.TestCaseGenerator;

import java.io.IOException;

public class TestYaml {
    @Test
    public void testFromYaml (){

        TestCaseGenerator t = new TestCaseGenerator();
        try {
            t.generateTests("test.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
