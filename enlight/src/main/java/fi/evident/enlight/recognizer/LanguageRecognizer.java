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

import static fi.evident.enlight.utils.PathUtils.extensionFor;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import fi.evident.enlight.Language;
import fi.evident.enlight.utils.PathUtils;

public final class LanguageRecognizer {

    private final List<LanguageMatcher> matchers = asList(
        regexpMatcher("^#!.*python", Language.PYTHON),
        regexpMatcher("^#!.*ruby", Language.RUBY),
        stringMatcher("import scala.", Language.SCALA),
        stringMatcher("public static void main(", Language.JAVA),
        stringMatcher("static void Main()", Language.CSHARP),
        regexpMatcher("(?m)^using System.*;\\s*$", Language.CSHARP),
        regexpMatcher("(?m)^package .+;$", Language.JAVA),
        regexpMatcher("(?m)^package .+$", Language.SCALA),
        regexpMatcher("#!.*python", Language.PYTHON),
        regexpMatcher("def [a-zA-Z_][a-zA-Z_0-9]*\\(self[\\),]", Language.PYTHON),
        stringMatcher("System.out.println(", Language.JAVA),
        stringMatcher("import java.", Language.JAVA),
        stringMatcher("#import ", Language.OBJECTIVEC),
        stringMatcher("using std", Language.CPLUSPLUS),
        stringMatcher("#include <stdio.h>", Language.C),
        stringMatcher("<!DOCTYPE html", Language.HTML),
        stringMatcher("<html", Language.HTML),
        stringMatcher("<a href=", Language.HTML),
        regexpMatcher("<(div|span|p|table|tr|h\\d)>", Language.HTML),
        stringMatcher("<?xml ", Language.XML),
        regexpMatcher("[a-zA-Z]\\s*:\\s*\\d+(px|em|pt|ex|%|in|pc|mm|cm)", Language.CSS),
        regexpMatcher("\\$\\(function\\(\\)\\s*\\{", Language.JAVASCRIPT),
        stringMatcher("jQuery", Language.JAVASCRIPT),
        stringMatcher("<?php", Language.PHP),
        stringMatcher("(defun ", Language.COMMON_LISP),
        stringMatcher("(define (", Language.SCHEME),
        regexpMatcher("class \\w+(\\[[,\\s\\w]+\\])\\((var|val)\\)?", Language.SCALA),
        regexpMatcher("trait \\w+", Language.SCALA),
        regexpMatcher("(?m)^\\w+\\s*::\\s*\\w -> \\w", Language.HASKELL));

    public Language recognizeLanguage(String source) {
        if (source == null) throw new IllegalArgumentException("null source");

        for (LanguageMatcher matcher : matchers)
            if (matcher.matches(source))
                return matcher.language;

        return null;
    }

    public Language recognizeLanguage(String fileName, String source) {
        if (source == null) throw new IllegalArgumentException("null source");

        Set<Language> candidates = Language.forExtension(extensionFor(fileName));

        if (candidates.size() == 1) {
            return candidates.iterator().next();
        } else {
            for (LanguageMatcher matcher : matchers)
                if (candidates.contains(matcher.language) && matcher.matches(source))
                    return matcher.language;

            return null;
        }
    }

    private static LanguageMatcher stringMatcher(String regex, Language language) {
        return regexpMatcher(Pattern.quote(regex), language);
    }

    private static LanguageMatcher regexpMatcher(String regex, Language language) {
        return new LanguageMatcher(Pattern.compile(regex), language);
    }

    private static class LanguageMatcher {
        private final Pattern pattern;
        private final Language language;

        public LanguageMatcher(final Pattern pattern, final Language language) {
            this.pattern = pattern;
            this.language = language;
        }

        boolean matches(String source) {
            return pattern.matcher(source).find();
        }
    }
}
