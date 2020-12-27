import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Map;

public class TestYaml {
    @Test
    public void testFromYaml (){

        //File test = new File("src/test/resources/test.yml");

        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("test.yml");
        //place the yaml file in the main/resources folder
        Map<String, Object> map = (Map<String, Object>) yaml.load(inputStream);
        System.out.println("Seed path: " + map.get("seed"));
        System.out.println("Number of cases: " + map.get("number"));
        System.out.println("Instructions: " + map.get("instructions"));
    }

}
