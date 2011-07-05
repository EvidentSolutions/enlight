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

package fi.evident.enlight;

import org.junit.Test;

import java.util.Collections;

import static fi.evident.enlight.Language.CPLUSPLUS;
import static fi.evident.enlight.utils.CollectionUtils.asSet;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class LanguageTest {

    @Test
    public void provideNameAndExtensions() {
        assertThat(CPLUSPLUS.getName(), is("C++"));
        assertThat(CPLUSPLUS.getExtensions(), is(asSet("cpp", "cc", "hpp", "hh")));
    }

    @Test
    public void supportsLookupByCaseInsensitiveName() {
        assertThat(Language.forName("Scala"), is(Language.SCALA));
        assertThat(Language.forName("scala"), is(Language.SCALA));
    }

    @Test
    public void lookupWithUnknownNameReturnsNull() {
        assertThat(Language.forName("foo"), is(nullValue(Language.class)));
    }

    @Test
    public void supportsLookupByFilenameExtension() {
        assertThat(Language.forExtension("cpp"), hasItem(CPLUSPLUS));
    }

    @Test
    public void lookupWithUnknownExtensionReturnsEmptySet() {
        assertThat(Language.forExtension("foo"), is(Collections.<Language>emptySet()));
    }
}
