import java.util.List;

public class Configuration {
    private String seedPath;
    private int numberOfCases;
    List<Instruction> instructions;

    public String getSeedPath(){
        return seedPath;
    }

    public int getNumberOfCases(){
        return numberOfCases;
    }

    public List<Instruction> getInstructions(){
        return instructions;
    }
}
