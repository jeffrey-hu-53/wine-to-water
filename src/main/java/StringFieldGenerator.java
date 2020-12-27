import java.util.Random;

public class StringFieldGenerator implements FieldGenerator<String>{
    private int min, max;
    public StringFieldGenerator(int min, int max){
        this.min = min;
        this.max = max;
    }

    @Override
    public String generate(){
        int len = (int)(Math.random() * (max - min) + min);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }


}
