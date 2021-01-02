package builder;

import fieldGenerator.*;
import testGenerator.Configuration;
import org.yaml.snakeyaml.Yaml;
import testGenerator.Instruction;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigurationBuilderByYAML implements ConfigurationBuilder {
    private String configPath;
    public ConfigurationBuilderByYAML(String configPath){
        this.configPath = configPath;
    }
    @Override
    public Configuration build(){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(configPath);
        Map<String, Object> configMap = (Map<String, Object>) yaml.load(inputStream);
        Configuration config = new Configuration();
        config.setSeedPath((String) configMap.get("seed"));
        config.setOutputPath((String) configMap.get("output"));
        config.setNumberOfCases((int) configMap.get("number"));
        List<Map<String, String>> instructionsAsMap = (List<Map<String, String>>) configMap.get("instructions");
        config.setInstructions(convertToListOfInstruction(instructionsAsMap));

        return config;
    }

    private List<Instruction> convertToListOfInstruction(List<Map<String, String>> listOfMap){
        List<Instruction> instructionList = new ArrayList<>();
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
                int roundPlaces = Integer.parseInt(values[2]);
                fg = new DoubleFieldGenerator(low, high, roundPlaces);
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
}
