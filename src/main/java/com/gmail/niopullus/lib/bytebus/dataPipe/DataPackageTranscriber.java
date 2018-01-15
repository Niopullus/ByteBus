package com.gmail.niopullus.lib.bytebus.dataPipe;

import com.gmail.niopullus.lib.bytebus.util.IntegerDigitExtractor;

import java.util.ArrayList;
import java.util.List;

public class DataPackageTranscriber {

    private byte[] inputData;
    private List<Byte> data;
    private int[] digits;
    private int digitPosition;
    private int frameSize;
    private byte[] transcribedData;
    private IntegerDigitExtractor integerDigitExtractor;

    private static final int DATA_SIZE_FRAME_LENGTH = 4;

    DataPackageTranscriber() {
        super();
        integerDigitExtractor = new IntegerDigitExtractor();
    }

    byte[] transcribe(final DataPackage dataPackage) {
        data = new ArrayList<>();
        inputData = dataPackage.getData();
        addSizeDescription();
        addData();
        convertData();
        return transcribedData;
    }

    private void addSizeDescription() {
        final int dataSize;
        dataSize = inputData.length;
        digits = integerDigitExtractor.extractDigits(dataSize);
        while (digitPosition < digits.length) {
            addSizeFrame();
        }
    }

    private void addSizeFrame() {
        inspectFrame();
        addFrameBytes();
    }

    private void inspectFrame() {
        final int digitsRemaining;
        digitsRemaining = getDigitsRemaining();
        if (digitsRemaining >= DATA_SIZE_FRAME_LENGTH) {
            frameSize = DATA_SIZE_FRAME_LENGTH;
        } else {
            frameSize = digitsRemaining;
        }
        data.add((byte) frameSize);
    }

    private int getDigitsRemaining() {
        final int totalDigits;
        totalDigits = digits.length;
        return totalDigits - digitPosition;
    }

    private void addFrameBytes() {
        for (int i = 0; i < frameSize; i++) {
            final int digit;
            digit = digits[digitPosition];
            data.add((byte) digit);
            digitPosition += 1;
        }
    }

    private void addData() {
        for (final Byte byteData : inputData) {
            data.add(byteData);
        }
    }

    private void convertData() {
        final int dataLength;
        dataLength = data.size();
        transcribedData = new byte[dataLength];
        for (int i = 0; i < dataLength; i++) {
            transcribedData[i] = data.get(i);
        }
    }

}
