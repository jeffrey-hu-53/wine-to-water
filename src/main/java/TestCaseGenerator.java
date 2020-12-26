import java.util.ArrayList;
import java.util.Map;

public class TestCaseGenerator {
    public ArrayList<Map<String, String>> generateTests(Instruction instruction, int size){
        return null;
    }

    /**
     * User will input a configuration file which needs to be in YAML format. I will use a YAML parser
     * like snakeYAML to read in the YAML file and create a map. The map will have the field names as
     * the key and the instructions / rules / restrictions as the values. This will be the instruction
     * map. I will then create multiple maps that contain the field name and a proper value that fits
     * the instructions. Each one of these maps is a test case. I will make an array list to store
     * each of these maps or test cases. At the end, the test case generator should return an array list
     * of maps.
     */
}
