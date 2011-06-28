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

import fi.evident.enlight.highlighter.support.StringMatcher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fi.evident.enlight.utils.CollectionUtils.asSet;
import static java.util.Arrays.asList;

public class CSSSyntax extends CommonSyntax {

    @Override
    protected StringMatcher symbol() {
        return regex("[-a-zA-Z_][-a-zA-Z0-9_]*");
    }

    @Override
    protected Set<String> keywords() {
        Set<String> words = new HashSet<String>();

        words.addAll(asList(
                "display", "position", "top", "right", "bottom", "left", "float", "clear", "z-index", "direction",
                "unicode-bidi", "overlfow", "clip", "visibility", "vertical-align", "color", "background-repeat",
                "background", "background-image", "background-color", "background-position", "background-attachment",
                "text-indent", "word-spacing", "text-align", "text-transform", "text-decoration", "white-space",
                "text-shadow", "line-height", "letter-spacing", "font", "font-weight", "font-family", "font-stretch",
                "font-style", "font-size", "font-variant", "font-size-adjust", "caption-side", "border-spacing", "table-layout",
                "empty-cells", "border-collapse", "speak-header", "size", "page-break-inside", "marks", "page", "page-break-before",
                "orphans", "page-break-after", "windows", "cursor", "outline-style", "outline", "outline-color",
                "outline-width", "volume", "elevation", "speak", "speech-rate", "pause", "voice-family", "pause-before",
                "pitch", "pause-after", "cue", "cue-before", "cue-after", "play-during", "azimuth", "pith-range", "stress",
                "richness", "speak-punctuation", "speak-numeral", "content", "quotes", "counter-reset", "counter-increment",
                "list-style", "list-style-type", "list-style-image", "list-style-position", "marker-offset"));
        
        words.addAll(createMinMax("height"));
        words.addAll(createMinMax("width"));

        words.addAll(createAllSides("margin"));
        words.addAll(createAllSides("padding"));
        words.addAll(createAllSides("border", "-color"));
        words.addAll(createAllSides("border", "-style"));
        words.addAll(createAllSides("border", "-width"));
        
        return words;
    }

    private static List<String> createMinMax(String keyword) {
        return asList(keyword, "min-" + keyword, "max-" + keyword);
    }

    private static Set<String> createAllSides(String keyword) {
        return createAllSides(keyword, "");
    }

    private static Set<String> createAllSides(String keyword, String suffix) {
        return asSet(
                keyword + suffix,
                keyword + "-top" + suffix,
                keyword + "-right" + suffix,
                keyword + "-bottom" + suffix,
                keyword + "-left" + suffix);
    }

    // selectorit luokka/ID

    @Override
    protected StringMatcher multiLineComment() {
        return regex("(?sm)/\\*.*?\\*/");
    }

    @Override
    protected StringMatcher operator() {
        return regex(":");
    }
}
