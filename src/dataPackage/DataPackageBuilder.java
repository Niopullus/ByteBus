package dataPackage;

import java.util.ArrayList;
import java.util.List;

public class DataPackageBuilder {

    private List<Byte> data;

    public DataPackageBuilder() {
        super();
        data = new ArrayList<>();
    }

    public void addData(final byte[] data) {
        for (final Byte byteData : data) {
            this.data.add(byteData);
        }
    }

    public DataPackage createDataPackage() {

    }

    

}
