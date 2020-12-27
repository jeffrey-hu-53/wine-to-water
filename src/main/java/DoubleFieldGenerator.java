public class DoubleFieldGenerator implements NumberFieldGenerator{
    double low, high;
    public DoubleFieldGenerator(double low, double high){
        this.low = low;
        this.high = high;
    }

    @Override
    public Double generate(){
        return (Math.random() * (high - low) + low);
    }
}
