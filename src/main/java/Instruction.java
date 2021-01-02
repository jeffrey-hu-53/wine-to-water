import java.util.Map;

public class Instruction {
    private String fieldName;
    private FieldGenerator fg;
    public Instruction(String fieldName, FieldGenerator fg){
        this.fieldName = fieldName;
        this.fg = fg;
    }

    public void apply(Map<String, String> testCase){
        if (fg != null){
            testCase.put(fieldName, fg.generate());
        }
        else{
            System.out.println(fieldName + " instructions could not by applied. Field Generator is null.");
        }
    }
}
