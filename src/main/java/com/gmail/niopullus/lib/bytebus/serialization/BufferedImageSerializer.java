package com.gmail.niopullus.lib.bytebus.serialization;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BufferedImageSerializer {

    private BufferedImage image;
    private ByteArrayOutputStream byteArrayOutputStream;

    private static final String IMAGE_FORMAT = "jpg";

    public BufferedImageSerializer() {
        super();
    }

    public DataPackage serialize(final BufferedImage bufferedImage) {
        final byte[] data;
        image = bufferedImage;
        byteArrayOutputStream = new ByteArrayOutputStream();
        writeImage();
        data = byteArrayOutputStream.toByteArray();
        return new DataPackage(data);
    }

    private void writeImage() {
        try {
            ImageIO.write(image, IMAGE_FORMAT, byteArrayOutputStream);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
