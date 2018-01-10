package packageBuilder;

import util.IntegerConcatonator;

class DataSizeBuilder {

    private int digitPosition;
    private IntegerConcatonator integerConcatonator;

    DataSizeBuilder() {
        super();
        digitPosition = 0;
        integerConcatonator = new IntegerConcatonator();
    }

    void add(final byte byteData) {
        integerConcatonator.setDigit(digitPosition, (int) byteData);
        digitPosition += 1;
    }

    int createDataSize() {
        return integerConcatonator.getNumber();
    }

    void reset() {
        integerConcatonator.reset();
        digitPosition = 0;
    }

}
