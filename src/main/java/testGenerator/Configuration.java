package testGenerator;

import java.util.List;

public class Configuration {
    private String seedPath, outputPath;
    private int numberOfCases;
    List<Instruction> instructions;

    public String getSeedPath(){
        return seedPath;
    }

    public String getOutputPath() { return outputPath; }

    public int getNumberOfCases(){
        return numberOfCases;
    }

    public List<Instruction> getInstructions() { return instructions; }

    public void setSeedPath(String seedPath) {
        this.seedPath = seedPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setNumberOfCases(int numberOfCases) {
        this.numberOfCases = numberOfCases;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}
