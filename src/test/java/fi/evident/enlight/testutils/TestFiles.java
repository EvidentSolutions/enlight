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
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class TestFiles {
    
    public static List<File> findExampleFiles() {
        // TODO: this assumes that we are executing the tests in the base directory of the project
        File file = new File("src/test/examples");
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
}
