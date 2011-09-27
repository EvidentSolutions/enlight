package fi.evident.enlight.utils;

import java.io.File;

public final class PathUtils {

    public static String extensionFor(String path) {
        return extensionFor(new File(path));
    }

    public static String extensionFor(File file) {
        String name = file.getName();
        int index = name.lastIndexOf('.');
        return index != -1 ? name.substring(index+1) : "";
    }
}
