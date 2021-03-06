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

package fi.evident.enlight.recognizer;

import fi.evident.enlight.Language;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class LanguageRecognizerTest {

    private LanguageRecognizer recognizer = new LanguageRecognizer();

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionForNullSource() {
        recognizer.recognizeLanguage((String) null);
    }

    @Test
    public void emptySourceShouldNotBeRecognized() {
        assertNull(recognizer.recognizeLanguage(""));
    }

    @Test
    public void recognizeJavaCode() {
        String code = "package foo; public class Foo { public static void main(String[] args) { } }";

        assertThat(recognizer.recognizeLanguage(code), is(Language.JAVA));
    }

    @Test
    public void recognizeCodeBasedOnFileName() {
        assertThat(recognizer.recognizeLanguage("foo.java", ""), is(Language.JAVA));
    }

    @Test
    public void recognizeCodeBasedOnFileNameAndContents() {
        assertThat(recognizer.recognizeLanguage("foo.h", "#import <bar.h>"), is(Language.OBJECTIVEC));
    }
}
