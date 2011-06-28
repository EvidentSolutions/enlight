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

import java.util.*;

import static java.util.Arrays.asList;

public final class CollectionUtils {

    private CollectionUtils() { }
    
    public static <T> Set<T> asSet(T... elements) {
        return new HashSet<T>(asList(elements));
    }

    public static String join(Iterable<?> items) {
        return join(items, "");
    }

    public static String join(Iterable<?> items, String separator) {
        StringBuilder sb = new StringBuilder();

        for (Iterator<?> it = items.iterator(); it.hasNext(); ) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append(separator);
        }

        return sb.toString();
    }

    public static <T> List<List<T>> inits(List<T> items) {
        List<List<T>> result = new ArrayList<List<T>>(items.size());

        for (int i = items.size(); i > 0; i--)
            result.add(items.subList(0, i));

        return result;
    }
}
