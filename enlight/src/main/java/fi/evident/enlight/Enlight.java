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

import fi.evident.enlight.highlighter.HighlightedSource;
import fi.evident.enlight.highlighter.SyntaxHighlighter;
import fi.evident.enlight.recognizer.LanguageRecognizer;

/**
 * Facade for common functionality.
 */
public final class Enlight {

    private Enlight() { }

    private static final LanguageRecognizer recognizer = new LanguageRecognizer();

    /**
     * Highlights given source code as given language.
     */
    public static HighlightedSource highlight(String source, Language language) {
        return SyntaxHighlighter.highlight(source, language);
    }

    /**
     * Tries to detect the language according to various heuristics.
     *
     * @return Recognized language, or null if language source could not be recognized.
     */
    public static Language recognizeLanguage(String source) {
        return recognizer.recognizeLanguage(source);
    }
}