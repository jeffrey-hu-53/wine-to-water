public class DoubleFieldGenerator implements FieldGenerator{
    double low, high;
    int roundPlaces;
    public DoubleFieldGenerator(double low, double high, int roundPlaces){
        this.low = low;
        this.high = high;
        this.roundPlaces = roundPlaces;
    }

    @Override
    public String generate(){
        return String.format("%." + roundPlaces + "f",(Math.random() * (high - low) + low));
    }
}
