package util;

public class IntegerConcatonator {

    private int number;

    public IntegerConcatonator() {
        super();
    }

    public void setDigit(final int position, final int value) {
        number += Math.pow(10, position) * value;
    }

    public int getNumber() {
        return number;
    }

    public void reset() {
        number = 0;
    }

}
