package packageBuilder;

import dataPipe.DataPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class DataPackageCompleter {

    private DataPackageBuilderContext context;
    private byte[] data2;
    private Queue<DataPackage> dataPackages;
    private List<Byte> remainingData;

    DataPackageCompleter(final DataPackageBuilderContext context) {
        super();
        this.context = context;
        dataPackages = context.getDataPackages();
    }

    void createDataPackage() {
        copyData();
        insertData();
        recycleRemainingData();
        reset();
    }

    private void insertData() {
        final DataPackage dataPackage;
        dataPackage = new DataPackage(data2);
        dataPackages.add(dataPackage);
    }

    private void copyData() {
        final int dataStart;
        final int dataSize;
        dataStart = context.getContentStartPosition();
        dataSize = context.getDataSize();
        data2 = new byte[dataSize];
        for (int i = 0; i < dataSize; i++) {
            data2[i] = getByteData(i + dataStart);
        }
    }

    private byte getByteData(final int index) {
        final List<Byte> data;
        data = context.getData();
        return data.get(index);
    }

    private void recycleRemainingData() {
        final int dataStart;
        final int dataSize;
        dataStart = context.getContentStartPosition();
        dataSize = context.getDataSize();
        remainingData = new ArrayList<>();
        copyRemainingData(dataStart + dataSize, getDataAmount());
        context.setData(remainingData);
    }

    private void copyRemainingData(final int start, final int end) {
        for (int i = start; i < end; i++) {
            final byte byteData;
            byteData = getByteData(i);
            remainingData.add(byteData);
        }
    }

    private int getDataAmount() {
        final List<Byte> data;
        data = context.getData();
        return data.size();
    }

    private void reset() {
        final List<Byte> data;
        data = new ArrayList<>();
        context.setData(data);
        context.setReadPosition(0);
        context.setDataSizeFound(false);
    }

}
