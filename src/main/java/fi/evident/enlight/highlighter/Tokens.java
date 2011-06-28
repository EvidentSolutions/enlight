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

public final class Tokens {

    private Tokens() { }

    public static final Token space = whitespace(" ");

    public static Token unknown(String value) {
        return new Token(value, TokenType.UNKNOWN);
    }

    public static Token operator(String value) {
        return new Token(value, TokenType.OPERATOR);
    }

    public static Token whitespace(String value) {
        return new Token(value, TokenType.WHITESPACE);
    }

    public static Token number(String value) {
        return new Token(value, TokenType.LITERAL_NUMBER);
    }

    public static Token keyword(String value) {
        return new Token(value, TokenType.KEYWORD);
    }

    public static Token string(String value) {
        return new Token(value, TokenType.LITERAL_STRING);
    }

    public static Token comment(String value) {
        return new Token(value, TokenType.COMMENT);
    }

    public static Token punctuation(String value) {
        return new Token(value, TokenType.PUNCTUATION);
    }    
}
