package fi.evident.enlight.utils;

import java.io.File;

public final class PathUtils {

    public static String extensionFor(String path) {
        String file = new File(path).getName();
        int index = file.lastIndexOf('.');
        return index != -1 ? file.substring(index+1) : "";
    }
}
