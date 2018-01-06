package dataPackage.extractor;

import dataPackage.DataPackage;

class DataExtractor {

    private DataPackage dataPackage;

    DataExtractor(final DataPackage dataPackage) {
        super();
        this.dataPackage = dataPackage;
    }

    DataPackage getDataPackage() {
        return dataPackage;
    }

}
