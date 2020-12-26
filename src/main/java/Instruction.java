import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;


public class Instruction {
    private Map<String, Object> instructions;
    public Instruction(){
        instructions = generateInstructions();
    }
    private Map<String, Object> generateInstructions(){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("instructions.yaml");
        //place the yaml file in the main/resources folder
        Map<String, Object> map = (Map<String, Object>) yaml.load(inputStream);
        System.out.println(map);
        /**
         * try reading YAML file here and converting it into a map.
         */
        return null;
    }
    //Yaml yaml = new Yaml();
}
