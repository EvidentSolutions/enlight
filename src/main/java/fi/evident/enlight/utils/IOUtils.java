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

package fi.evident.enlight.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public final class IOUtils {

    private IOUtils() { }

    public static String readFile(File file) throws IOException {
        Reader reader = new FileReader(file);
        try {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[512];
            int n;

            while ((n = reader.read(buffer)) != -1)
                sb.append(buffer, 0, n);

            return sb.toString();
        } finally {
            reader.close();
        }
    }

    public static String extensionOf(File file) {
        String name = file.getName();
        int n = name.lastIndexOf('.');
        return n != -1 ? name.substring(n + 1) : "";
    }
}
