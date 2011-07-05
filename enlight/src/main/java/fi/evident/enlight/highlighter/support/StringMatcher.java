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
import fi.evident.enlight.highlighter.TokenType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringMatcher {

    public abstract String match(String input);

    public TokenMatcher toTokenMatcher(final TokenType type) {
        return new TokenMatcher() {
            @Override
            public Token match(final String input) {
                String match = StringMatcher.this.match(input);
                return match != null ? new Token(match, type) : null;
            }
        };
    }

    public StringMatcher or(final StringMatcher right) {
        final StringMatcher left = this;
        return new StringMatcher() {
            @Override
            public String match(String input) {
                String match = left.match(input);
                return (match != null) ? match : right.match(input);
            }
        };
    }

    public static StringMatcher string(final String prefix) {
        return new StringMatcher() {
            @Override
            public String match(String input) {
                return input.startsWith(prefix) ? prefix : null;
            }
        };
    }

    public static StringMatcher regex(String regex) {
        return regex(Pattern.compile(regex));
    }

    public static StringMatcher regex(final Pattern regex) {
        return new StringMatcher() {
            @Override
            public String match(String input) {
                Matcher m = regex.matcher(input);
                return m.lookingAt() ? m.group() : null;
            }
        };
    }

    public static final StringMatcher NO_MATCH = new StringMatcher() {
        @Override
        public String match(final String input) {
            return null;
        }
    };
}
