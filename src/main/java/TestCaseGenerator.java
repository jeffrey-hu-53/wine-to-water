import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestCaseGenerator {
    public File generateTests(String path){
        /**
         * create a configuration object by reading in the yaml file
         */
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        Map<String, Object> configMap = (Map<String, Object>) yaml.load(inputStream);
        String seedPath = (String) configMap.get("seed");
        int numberOfCases = (int) configMap.get("number");
        List<Map<String, String>> instructionsAsMap = (List<Map<String, String>>) configMap.get("instructions");
        List<Instruction> instructions = convertToListOfInstruction(instructionsAsMap);

        Map<String, String> mappedSeed = toMap(seedPath);
        for (int i = 0; i < numberOfCases; i++){
            Map<String, String> testCase = clone(mappedSeed);
            instructions.stream().forEach(instruction -> instruction.apply(testCase));
            /**
             * write to the output file here.
             */
        }

        return null;
    }

    private Map<String, String> toMap(String seedPath){
        /**
         * take in path to find csv file. Read the csv file and create a map of the seed test case.
         */
        return null;
    }

    private Map<String, String> clone(Map<String, String> m){
        return m;
    }

    private List<Instruction> convertToListOfInstruction(List<Map<String, String>> listOfMap){
        List<Instruction> instructionList = new ArrayList<Instruction>();
        for (Map<String, String> item : listOfMap){
            FieldGenerator fg = null;
            if (item.get("fieldType").equalsIgnoreCase("double")){
                String[] values = item.get("values")
                        .replaceAll("\\s", "")
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .split(",");
                double low = Double.parseDouble(values[0]);
                double high = Double.parseDouble(values[1]);
                fg = new DoubleFieldGenerator(low, high);
            }
            else if (item.get("fieldType").equalsIgnoreCase( "integer")){
                String[] values = item.get("values")
                        .replaceAll("\\s", "")
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .split(",");
                int low = Integer.parseInt(values[0]);
                int high = Integer.parseInt(values[1]);
                fg = new IntegerFieldGenerator(low, high);

            }
            else if (item.get("fieldType").equalsIgnoreCase("pickOne")){
                String[] values = item.get("values")
                        .replaceAll("\\s", "")
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .split(",");
                fg = new PickOneFieldGenerator(values);
            }
            else if (item.get("fieldType").equalsIgnoreCase("string")){
                String[] values = item.get("values")
                        .replaceAll("\\s", "")
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .split(",");
                int low = Integer.parseInt(values[0]);
                int high = Integer.parseInt(values[1]);
                fg = new StringFieldGenerator(low, high);
            }
            instructionList.add(new Instruction(item.get("fieldName"), fg));
        }
        return instructionList;
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
