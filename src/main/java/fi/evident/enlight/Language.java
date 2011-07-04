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

import fi.evident.enlight.highlighter.SyntaxHighlighter;
import fi.evident.enlight.highlighter.languages.*;

import java.util.EnumSet;
import java.util.Set;

import static fi.evident.enlight.utils.CollectionUtils.asSet;
import static java.util.Collections.unmodifiableSet;

public enum Language {
    
    // ActionScript
    // Beta
    // Boo
    BRAINFUCK("Brainfuck",     new BrainfuckSyntax(),     "bf"),
    C("C",                     new CSyntax(),             "c", "h"),
    CSHARP("C#",               new CSharpSyntax(),        "cs"),
    CPLUSPLUS("C++",           new CPlusPlusSyntax(),     "cpp", "hpp", "cc", "hh"),
    CLOJURE("Clojure",         new ClojureSyntax(),       "clj"),
    COMMON_LISP("Common Lisp", new CommonLispSyntax(),    "cl"),
    CSS("CSS",                 new CSSSyntax(),           "css"),
    // Factor
    // Go
    // Groovy
    HASKELL("Haskell",         new HaskellSyntax(),       "hs"),
    HTML("HTML",               new HTMLSyntax(),          "html", "xhtml"),
    // J
    JAVA("Java",               new JavaSyntax(),          "java"),
    JAVASCRIPT("Javascript",   new JavascriptSyntax(),    "js"),
    // Mathematica
    // Perl
    PHP("PHP",                 new PHPSyntax(),           "php"),
    // Prolog
    OBJECTIVEC("Objective-C",  new ObjectiveCSyntax(),    "m", "h"),
    PYTHON("Python",           new PythonSyntax(),        "py"),
    // R
    RUBY("Ruby",               new RubySyntax(),          "rb"),
    SCALA("Scala",             new ScalaSyntax(),         "scala"),
    SCHEME("Scheme",           new SchemeSyntax(),        "ss", "scm"),
    SHELL("Shell",             new ShellSyntax(),         "sh"),
    // Smalltalk
    // SQL
    // TeX
    // VisualBasic
    XML("XML",                 new XMLSyntax(),           "xml"),
    OTHER("Other",             new OtherSyntax());

    private final String name;
    private SyntaxHighlighter highlighter;
    private final Set<String> extensions;

    private Language(String name, SyntaxHighlighter highlighter, String... extensions) {
        this.name = name;
        this.extensions = unmodifiableSet(asSet(extensions));
        this.highlighter = highlighter;
    }

    public SyntaxHighlighter getHighlighter() {
        return highlighter;
    }

    public static Language forName(String name) {
        for (Language language : values())
            if (name.equalsIgnoreCase(language.getName()))
                return language;

        return null;
    }

    /**
     * Returns languages that use given extension.
     */
    public static Set<Language> forExtension(String extension) {
        Set<Language> result = EnumSet.noneOf(Language.class);
        
        for (Language language : values())
            if (language.getExtensions().contains(extension))
                result.add(language);

        return result;
    }

    public String getName() {
        return name;
    }

    public Set<String> getExtensions() {
        return extensions;
    }

    @Override
    public String toString() {
        return name;
    }
}
