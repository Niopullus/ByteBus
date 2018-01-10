package packageBuilder;

import java.util.List;

class DataSizeExtractor {

    private DataPackageBuilderContext context;
    private DataSizeBuilder dataSizeBuilder;
    private int sizeBytesLeft;

    DataSizeExtractor(final DataPackageBuilderContext context) {
        super();
        this.context = context;
        dataSizeBuilder = new DataSizeBuilder();
    }

    void extractDataSize() {
        while (!context.isDataSizeFound() && moreToAnalyze()) {
            analyzeByte();
        }
    }

    private boolean moreToAnalyze() {
        final List<Byte> data;
        final int byteCount;
        final int position;
        data = context.getData();
        byteCount = data.size();
        position = context.getReadPosition();
        return position < byteCount;
    }

    private void analyzeByte() {
        if (sizeBytesLeft == 0) {
            checkForRenewal();
        } else {
            addToSize();
        }
        incrementPosition();
    }

    private void checkForRenewal() {
        final byte currentByte;
        currentByte = getCurrentByte();
        if (currentByte > 0) {
            sizeBytesLeft += (int) currentByte;
        } else {
            declareDateSizeFound();
        }
    }

    private byte getCurrentByte() {
        final List<Byte> data;
        final int position;
        data = context.getData();
        position = context.getReadPosition();
        return data.get(position);
    }

    private void declareDateSizeFound() {
        final int dataSize;
        dataSize = dataSizeBuilder.createDataSize();
        context.setDataSize(dataSize);
        context.setDataSizeFound(true);
    }

    private void addToSize() {
        final byte currentByte;
        currentByte = getCurrentByte();
        dataSizeBuilder.add(currentByte);
    }

    private void incrementPosition() {
        final int position;
        position = context.getReadPosition();
        context.setReadPosition(position + 1);
    }

    public void reset() {
        dataSizeBuilder.reset();
        sizeBytesLeft = 0;
    }

}
