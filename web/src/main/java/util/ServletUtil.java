package util;

/**
 * @author a.shestovsky
 */
public final class ServletUtil {

    private static final String PREFIX = "/WEB-INF/html/";
    private static final String SUFFIX = ".jsp";

    private ServletUtil() {}

    public static String getPath(String viewName) {
        return String.format("%s%s%s", PREFIX, viewName, SUFFIX);
    }
}
