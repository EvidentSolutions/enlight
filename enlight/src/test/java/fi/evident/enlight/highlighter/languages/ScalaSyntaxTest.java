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

package fi.evident.enlight.highlighter.languages;

import fi.evident.enlight.highlighter.SyntaxHighlighter;
import org.junit.Test;

import static fi.evident.enlight.highlighter.Tokens.*;

public class ScalaSyntaxTest extends SyntaxTest {

    @Override
    protected SyntaxHighlighter highlighter() {
        return new ScalaSyntax();
    }

    @Test
    public void supportTextWhichIsNotValidScala() {
        assertThatHighlighting("foo bar", producesTokens(unknown("foo"), space, unknown("bar")));
    }

    @Test
    public void highlightOperators() {
        assertThatHighlighting("foo + bar", producesTokens(unknown("foo"), space, operator("+"), space, unknown("bar")));
    }

    @Test
    public void highlightNumericLiterals() {
        assertThatHighlighting("foo 123 bar", producesTokens(unknown("foo"), space, number("123"), space, unknown("bar")));
    }

    @Test
    public void highlightKeywords() {
        assertThatHighlighting("foo if bar", producesTokens(unknown("foo"), space, keyword("if"), space, unknown("bar")));
    }

    @Test
    public void highlightStrings() {
        assertThatHighlighting("foo \"bar\" baz", producesTokens(unknown("foo"), space, string("\"bar\""), space, unknown("baz")));
    }

    @Test
    public void highlightSingleLineComments() {
        assertThatHighlighting("foo // bar", producesTokens(unknown("foo"), space, comment("// bar")));
    }

    @Test
    public void highlightMultiLineComments() {
        assertThatHighlighting("foo /* bar */ baz",
                producesTokens(unknown("foo"), space, comment("/* bar */"), space, unknown("baz")));
    }

    @Test
    public void multiLineCommentsAreNormalized() {
        assertThatHighlighting("foo /* bar \n bar \n */ baz",
                producesTokens(unknown("foo"), space, comment("/* bar "), comment(" bar "), comment(" */"), space, unknown("baz")));
    }

    @Test
    public void tokenHandlingInNewLines() {
        assertThatHighlighting("foo)\n bar", producesTokens(unknown("foo"), punctuation(")"), space, unknown("bar")));
    }
}
