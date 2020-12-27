public class IntegerFieldGenerator implements NumberFieldGenerator{
    double low, high;
    public IntegerFieldGenerator(double low, double high){
        this.low = low;
        this.high = high;
    }

    @Override
    public Integer generate(){
        return (int)(Math.random() * (high - low) + low);
    }
}