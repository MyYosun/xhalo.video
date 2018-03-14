package net.xhalo.video.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FilePathProperties {

    private static Logger logger = LoggerFactory.getLogger(FilePathProperties.class);

    static {
        loadProperties();
    }

    public static String VIDEO_SAVE_PATH;
    public static String IMAGE_SAVE_PATH;
    public static String HEAD_IMAGE_SAVE_PATH;
    public static String BIG_IMAGE_SAVE_PATH;
    public static String FFMPEG_PATH;

    synchronized private static void loadProperties() {
        Properties properties = new Properties();
        InputStream in = FilePathProperties.class.getResourceAsStream("/config/properties/path.properties");
        try {
            properties.load(in);
            VIDEO_SAVE_PATH = properties.getProperty("VIDEO_SAVE_PATH");
            IMAGE_SAVE_PATH = properties.getProperty("IMAGE_SAVE_PATH");
            HEAD_IMAGE_SAVE_PATH = properties.getProperty("HEAD_IMAGE_SAVE_PATH");
            BIG_IMAGE_SAVE_PATH = properties.getProperty("BIG_IMAGE_SAVE_PATH");
            FFMPEG_PATH = properties.getProperty("FFMPEG_PATH");
        } catch (IOException e) {
            logger.error("ERROR WHEN LOAD PROPERTY:", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("ERROR WHEN CLOSE INPUT STREAM:", e);

            }
        }
    }

}
