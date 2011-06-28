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

import fi.evident.enlight.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static fi.evident.enlight.utils.CollectionUtils.inits;
import static java.util.Arrays.asList;

/**
 * Represents the type of the token.
 */
public final class TokenType {

    /** Type for whitespace */
    public static final TokenType WHITESPACE     = new TokenType("whitespace");
    public static final TokenType LITERAL_NUMBER = new TokenType("literal.number");
    public static final TokenType LITERAL_STRING = new TokenType("literal.string");
    public static final TokenType COMMENT        = new TokenType("comment");
    public static final TokenType KEYWORD        = new TokenType("keyword");
    public static final TokenType OPERATOR       = new TokenType("operator");
    public static final TokenType PUNCTUATION    = new TokenType("punctuation");

    /** Used for tokens that are not recognized */
    public static final TokenType UNKNOWN = new TokenType("unknown");

    /** Describes this type, e.g. "literal", or "literal.number.integer" */
    private final String path;

    TokenType(String path) {
        if (path == null) throw new IllegalArgumentException("null path");
        if (!path.matches("[a-zA-Z]+(\\.[a-zA-Z]+)*"))
            throw new IllegalArgumentException("invalid path: " + path);

        this.path = path;
    }

    /**
     * Returns non-empty sequence of paths from most specific to least specific.
     *
     * E.g. if the path is is "foo.bar.baz", this method returns.
     * the sequence ("foo.bar.baz", "foo.bar", "foo")
     */
    public List<String> getPaths() {
        List<String> paths = new ArrayList<String>();

        for (List<String> p : inits(asList(path.split("\\."))))
            paths.add(CollectionUtils.join(p, "."));

        return paths;
    }

    /**
     * Creates a new token-type that is a child of this type. E.g. from
     * type TokenType("foo.bar").child("baz") would produce TokenType("foo.bar.baz")
     */
    public TokenType child(String childPath) {
        return new TokenType(path + "." + childPath);
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof TokenType) {
            TokenType tt = (TokenType) obj;
            return path.equals(tt.path);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
