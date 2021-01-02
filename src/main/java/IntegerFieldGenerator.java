public class IntegerFieldGenerator implements FieldGenerator{
    double low, high;
    public IntegerFieldGenerator(double low, double high){
        this.low = low;
        this.high = high;
    }

    @Override
    public String generate(){
        return String.valueOf((int)(Math.random() * (high - low) + low));
    }
}