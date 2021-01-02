package testGenerator;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import testGenerator.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCaseWriter {
    private String configPath;

    public void writeTestCases(Configuration config) throws IOException{


        Map<String, String> mappedSeed = null;
        List<String[]> listOfStringArr = new ArrayList<>();
        String[] header = getHeaderAsArray(config.getSeedPath());
        try {
            mappedSeed = toMap(config.getSeedPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!mappedSeed.isEmpty()) {
            CSVWriter writer = new CSVWriter(new FileWriter(config.getOutputPath()));
            writer.writeNext(header);
            int count = 0;

            while (count != config.getNumberOfCases()){
                listOfStringArr.add(generateOneTestCase(header, mappedSeed, config));
                count++;
                if (count % 1000 == 0){
                    writer.writeAll(listOfStringArr);
                    listOfStringArr.clear();
                    //check what clear actually does
                }
            }
            if (config.getNumberOfCases() % 1000 != 0){
                writer.writeAll(listOfStringArr);
                listOfStringArr.clear();
            }
            writer.close();

        }
        else {
            System.out.println("Seed map is empty.");
        }
    }

    private String[] generateOneTestCase(String[] header, Map<String, String> mappedSeed, Configuration config){
        String[] arr = new String[header.length];
        Map<String, String> testCase = clone(mappedSeed);
        config.getInstructions().stream().forEach(instruction -> instruction.apply(testCase));
        for (int j = 0; j < header.length; j++){
            arr[j] = testCase.get(header[j]);
        }

        return arr;
    }


    private String[] getHeaderAsArray(String seedPath) {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(seedPath));
            try {
                List<String[]> list = readAll(reader);
                return list.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> toMap(String seedPath) throws IOException {
        Map<String, String> map = new HashMap<>();
        Reader reader = Files.newBufferedReader(Paths.get(seedPath));
        try {
            List<String[]> list = readAll(reader);
            for (int i = 0; i < list.get(0).length; i++){
                map.put(list.get(0)[i], list.get(1)[i]);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String[]> readAll(Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list;
        list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    private Map<String, String> clone(Map<String, String> m){
        return m;
    }

}
