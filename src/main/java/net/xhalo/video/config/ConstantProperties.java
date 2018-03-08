package net.xhalo.video.config;

public class ConstantProperties {
    public static final String AUTHORITY_USER = "user";
    public static final String AUTHORITY_ADMIN = "admin";
    public static final String DEFAULT_IMAGE_SIZE = "800x600";
    public static final String IMAGE_SIZE_BIG = "1000x400";
    public static final String VIDEO_FILE_FORMAT = ".mp4";
    public static final String IMAGE_FILE_FORMAT = ".jpg";
    public static final String USER_IN_SESSION = "user";
    public static final String VIDEO_DATE = "date";
    public static final String VIDEO_CLICK = "click";
    public static final String VIDEO_DURATION = "duration";
    public static final String VIDEO_DURATION_ALL = "all";
    public static final String VIDEO_DURATION_SHORT = "short";
    public static final String VIDEO_DURATION_SHORT_SQL = "AND a.duration <= '600'";
    public static final String VIDEO_DURATION_MEDIUM = "medium";
    public static final String VIDEO_DURATION_MEDIUM_SQL = "AND a.duration BETWEEN '600' AND '1800'";
    public static final String VIDEO_DURATION_LONG = "long";
    public static final String VIDEO_DURATION_LONG_SQL = "AND a.duration BETWEEN '1800' AND '5400'";
    public static final String VIDEO_DURATION_OTHER = "other";
    public static final String VIDEO_DURATION_OTHER_SQL = "AND a.duration >= '5400'";

}
