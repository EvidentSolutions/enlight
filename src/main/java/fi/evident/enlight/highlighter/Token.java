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

public final class Token {

    private final String text;
    private final TokenType tokenType;

    public Token(String text, TokenType tokenType) {
        if (text.isEmpty()) throw new IllegalArgumentException("empty text");
        if (tokenType == null) throw new IllegalArgumentException("null tokenType");
        
        this.text = text;
        this.tokenType = tokenType;
    }

    public String getText() {
        return text;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return tokenType + ":" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Token) {
            Token t = (Token) o;

            return text.equals(t.text)
                && tokenType.equals(t.tokenType);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return text.hashCode() * 79 + tokenType.hashCode();
    }
}
