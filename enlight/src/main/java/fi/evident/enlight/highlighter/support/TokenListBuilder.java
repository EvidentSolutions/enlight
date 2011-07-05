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

/**
 * Helper class used to build token lists so that unknown characters form
 * sequences of unknown tokens.
 */
final class TokenListBuilder {
    private final List<Token> tokens = new ArrayList<Token>();
    private final StringBuilder currentUnknown = new StringBuilder();

    void addToken(Token token) {
        finishUnknown();
        tokens.add(token);
    }

    void addToUnknown(String s) {
        currentUnknown.append(s);
    }

    List<Token> toList() {
        finishUnknown();
        return tokens;
    }

    private void finishUnknown() {
        if (currentUnknown.length() != 0) {
            tokens.add(new Token(currentUnknown.toString(), TokenType.UNKNOWN));
            currentUnknown.setLength(0);
        }
    }
}
