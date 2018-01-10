package extraction;

import dataPipe.DataPackage;

public class DataExtractor {

    private DataPackage dataPackage;

    public DataExtractor(final DataPackage dataPackage) {
        super();
        this.dataPackage = dataPackage;
    }

    public DataPackage getDataPackage() {
        return dataPackage;
    }

}
