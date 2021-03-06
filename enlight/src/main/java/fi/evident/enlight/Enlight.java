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

import java.io.File;
import java.io.IOException;

/**
 * Facade for common functionality.
 */
public final class Enlight {

    private Enlight() { }

    private static final LanguageRecognizer recognizer = new LanguageRecognizer();

    /**
     * Highlights given source code as given language.
     *
     * @param source code to highlight
     * @param language to highlight the source as
     * @return Highlighted representation of the source code
     */
    public static HighlightedSource highlight(String source, Language language) {
        return SyntaxHighlighter.highlight(source, language);
    }

    /**
     * Tries to detect the language according to various heuristics.
     *
     * @param source Source code to recognize
     * @return Recognized language, or null if language could not be recognized.
     */
    public static Language recognizeLanguage(String source) {
        return recognizer.recognizeLanguage(source);
    }

    /**
     * Tries to detect language first based on the fileName and source-code. The source
     * code is only used to disambiguate potentially conflicting extensions.
     *
     * @param fileName name of the source file
     * @param source code to recognize
     * @return Recognized language, or null if language could not be recognized.
     */
    public static Language recognizeLanguage(String fileName, String source) {
        return recognizer.recognizeLanguage(fileName, source);
    }

    /**
     * Tries to detect the language of given file, first based on the name and
     * then by reading the file if file name is ambiguous.
     *
     * @param file to recognize
     * @return Recognized language, or null if language could not be recognized.
     * @throws IOException if reading the file fails
     */
    public static Language recognizeLanguage(File file) throws IOException {
        return recognizer.recognizeLanguage(file);
    }
}
