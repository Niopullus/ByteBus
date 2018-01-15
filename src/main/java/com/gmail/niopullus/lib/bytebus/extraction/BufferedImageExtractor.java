package com.gmail.niopullus.lib.bytebus.extraction;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;
import com.gmail.niopullus.lib.bytebus.extraction.DataExtractor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class BufferedImageExtractor extends DataExtractor {

    public BufferedImageExtractor() {
        super();
    }

    public BufferedImage extractImage(final DataPackage dataPackage) {
        try {
            final ByteArrayInputStream byteArrayInputStream;
            final byte[] data;
            data = dataPackage.getData();
            byteArrayInputStream = new ByteArrayInputStream(data);
            return ImageIO.read(byteArrayInputStream);
        } catch (final Exception e) {
            throwExtractorException(e, null);
        }
        return null;
    }

}
