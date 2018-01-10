package packageBuilder;

import dataPipe.DataPackage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DataPackageBuilder {

    private Queue<DataPackage> dataPackages;
    private DataPackageBuilderContext context;
    private DataSizeExtractor dataSizeExtractor;
    private DataPackageCompleter dataPackageCompleter;

    public DataPackageBuilder() {
        super();
        dataPackages = new ArrayDeque<>();
        context = new DataPackageBuilderContext();
        context.setDataPackages(dataPackages);
        context.setData(new ArrayList<>());
        dataSizeExtractor = new DataSizeExtractor(context);
        dataPackageCompleter = new DataPackageCompleter(context);
    }

    public void setDataPackages(final Queue<DataPackage> dataPackages) {
        this.dataPackages = dataPackages;
    }

    public void addData(final byte[] data, final int dataLength) {
        for (int i = 0; i < dataLength; i++) {
            final byte byteData;
            byteData = data[i];
            insertByte(byteData);
        }
        processData();
    }

    private void insertByte(final byte byteData) {
        final List<Byte> data;
        data = context.getData();
        data.add(byteData);
    }

    private void processData() {
        while (isDataToBeRead()) {
            if (context.isDataSizeFound()) {
                checkIfComplete();
            } else {
                dataSizeExtractor.extractDataSize();
            }
        }
    }

    private void checkIfComplete() {
        final int dataSize;
        if (isComplete()) {
            dataPackageCompleter.createDataPackage();
            dataSizeExtractor.reset();
        }
        dataSize = getDataAmount();
        context.setReadPosition(dataSize - 1);
    }

    private int getDataAmount() {
        final List<Byte> data;
        data = context.getData();
        return data.size();
    }

    private boolean isComplete() {
        final int amountOfData;
        final int contentStart;
        final int dataSize;
        amountOfData = getDataAmount();
        contentStart = context.getContentStartPosition();
        dataSize = context.getDataSize();
        return amountOfData - contentStart >= dataSize;
    }

    private boolean isDataToBeRead() {
        final int readPosition;
        readPosition = context.getReadPosition();
        return readPosition < getDataAmount();
    }

}
