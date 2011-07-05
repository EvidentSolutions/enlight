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

package fi.evident.enlight.highlighter;

import fi.evident.enlight.Language;

/**
 * Syntax highlighters convert source into a list of recognized tokens.
 * <p>
 * The tokens might not cover all source text, so the text without any
 * specific tokens should be left unhighlighted.
 */
public abstract class SyntaxHighlighter {

    public abstract HighlightedSource highlight(String source);

    /**
     * Highlights the program using the highlighter registered for given language.
     */
    public static HighlightedSource highlight(String source, Language language) {
        if (source == null) throw new IllegalArgumentException("null source");
        if (language == null) throw new IllegalArgumentException("null language");

        SyntaxHighlighter highlighter = language.getHighlighter();
        return highlighter.highlight(source);
    }
}
