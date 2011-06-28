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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class TokenMatcherBasedSyntaxHighlighter extends SyntaxHighlighterBase {

    private TokenMatcher tokenMatcher;

    protected TokenMatcher getTokenMatcher() {
        if (tokenMatcher == null)
            tokenMatcher = createTokenMatcher();
        return tokenMatcher;
    }

    protected abstract TokenMatcher createTokenMatcher();

    @Override
    protected Iterable<Token> tokensForSource(String source) {
        TokenMatcher matcher = getTokenMatcher();

        List<Token> tokens = new ArrayList<Token>();

        while (!source.isEmpty()) {
            Token token = matcher.match(source);
            if (token == null)
                token = new Token(source.substring(0, 1), TokenType.UNKNOWN);

            tokens.add(token);
            source = source.substring(token.getText().length());
        }

        return tokens;
    }

    protected static StringMatcher oneOf(String... values) {
        if (values.length == 0)
            return StringMatcher.NO_MATCH;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i != 0)
                sb.append('|');
            sb.append(Pattern.quote(values[i]));
        }

        return regex(sb.toString());
    }

    protected static StringMatcher string(String string) {
        return StringMatcher.string(string);
    }

    protected static StringMatcher regex(String regex) {
        return StringMatcher.regex(regex);
    }
}
