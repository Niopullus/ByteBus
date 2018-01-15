package com.gmail.niopullus.lib.bytebus.util;

public class IntegerDigitExtractor {

    private int number;
    private int[] digits;
    private int digitCount;
    private int digitPosition;

    public IntegerDigitExtractor() {
        super();
    }

    public int[] extractDigits(final int number) {
        this.number = number;
        digitCount = getDigitCount();
        digitPosition = digitCount;
        reset();
        iterateDigits();
        return digits;
    }

    private void reset() {
        digits = new int[digitCount];
    }

    private int getDigitCount() {
        final double result;
        result = Math.log10(number);
        return (int) Math.floor(result);
    }

    private void iterateDigits() {
        for (int i = 0; i < digitCount; i++) {
            extractDigit();
        }
    }

    private void extractDigit() {
        final int baseDigit;
        final int digit;
        baseDigit = (int) Math.pow(10, digitPosition - 1);
        digit = number / baseDigit;
        number %= baseDigit;
        addDigit(digit);
    }

    private void addDigit(final int digit) {
        digits[digitCount - digitPosition] = digit;
        digitPosition -= 1;
    }

}
