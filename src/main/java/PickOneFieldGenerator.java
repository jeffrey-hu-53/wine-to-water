public class PickOneFieldGenerator implements FieldGenerator<String>{
    private String[] listToChooseFrom;

    public PickOneFieldGenerator(String[] values){
        listToChooseFrom = values;
    }

    @Override
    public String generate() {
        int len = listToChooseFrom.length;
        int index = (int) (Math.random() * len);
        return listToChooseFrom[index];
    }
}
