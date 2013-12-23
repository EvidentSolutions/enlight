/*
 * Copyright (c) 2011 Evident Solutions Oy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package fi.evident.enlight.testutils;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class TestFiles {

    private static File moduleDirectory;

    public static List<File> findExampleFiles() {
        File file = new File(getModuleDirectory(), "enlight/src/test/examples");
        return asList(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile();
            }
        }));
    }

    public static List<Object[]> findExampleFilesAsTestData() {
        List<File> files = findExampleFiles();
        List<Object[]> data = new ArrayList<Object[]>(files.size());
        for (File file : files)
            data.add(new Object[] { file } );
        return data;
    }

    private static File getModuleDirectory() {
        if (moduleDirectory == null)
            moduleDirectory = locateModuleDirectory();
        return moduleDirectory;
    }

    private static File locateModuleDirectory() {
        // To find out base directory for our module, we first try to find out 'test-resource-marker.txt' from
        // classpath. Depending on the runner, it might included in the classpath directory from src/test/resources
        // or it could be copied somewhere else. However, most probably it is somewhere below our module directory,
        // so we'll keep going up the directory hierarchy until we find out a directory with 'pom.xml'.

        URL url = TestFiles.class.getResource("/test-resource-marker.txt");
        if (url == null)
            throw new RuntimeException("could not find test-resource-marker.txt");

        for (File dir = new File(url.getFile()).getParentFile(); dir != null; dir = dir.getParentFile())
            if (Arrays.asList(dir.list()).contains("settings.gradle"))
                return dir;

        throw new RuntimeException("could not locate module root directory");
    }
}
