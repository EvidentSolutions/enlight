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

package fi.evident.enlight.highlighter.support;

import fi.evident.enlight.highlighter.Token;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static fi.evident.enlight.highlighter.Tokens.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LineSequenceBuilderTest {

    @Test
    public void emptyTokenListIsNormalizedToEmptyList() {
        assertThat(normalize().isEmpty(), is(true));
    }

    @Test
    public void tokensWithoutLineBreaksStayOnSingleLine() {
        List<List<Token>> result = normalize(unknown("foo"), space, comment("/* bar */"), operator("?"), space);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), isList(unknown("foo"), space, comment("/* bar */"), operator("?"), space));
    }

    @Test
    public void multiLineTokensAreBrokenIntoSeparateLines() {
        List<List<Token>> result = normalize(unknown("foo\nbar"), whitespace(" \n "), comment("/* baz \n quux */"));

        assertThat(result.size(), is(4));
        assertThat(result.get(0), isList(unknown("foo")));
        assertThat(result.get(1), isList(unknown("bar"), whitespace(" ")));
        assertThat(result.get(2), isList(whitespace(" "), comment("/* baz ")));
        assertThat(result.get(3), isList(comment(" quux */")));
    }

    private static List<List<Token>> normalize(Token... tokens) {
        return LineSequenceBuilder.normalizeTokensToLines(asList(tokens));
    }

    private static <T> Matcher<List<T>> isList(T... items) {
        return is(asList(items));
    }
}
