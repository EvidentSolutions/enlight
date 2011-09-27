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
import fi.evident.enlight.utils.PeekableIterator;

import java.util.Iterator;

/**
 * A stream of token that wraps given stream but merges the unknown tokens
 * of the underlying stream.
 */
final class UnknownMergingTokenStream implements Iterable<Token> {

    private final Iterable<Token> tokens;

    UnknownMergingTokenStream(Iterable<Token> tokens) {
        if (tokens == null) throw new NullPointerException();

        this.tokens = tokens;
    }

    public Iterator<Token> iterator() {
        return new UnknownMergingIterator(tokens.iterator());
    }

    private static final class UnknownMergingIterator implements Iterator<Token> {

        private final PeekableIterator<Token> iterator;

        UnknownMergingIterator(Iterator<Token> iterator) {
            this.iterator = new PeekableIterator<Token>(iterator);
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Token next() {
            if (!hasNext()) throw new IllegalStateException();

            Token token = iterator.next();
            if (token.getTokenType() != TokenType.UNKNOWN)
                return token;

            StringBuilder unknown = new StringBuilder();
            unknown.append(token.getText());
            while (iterator.hasNext() && iterator.peek().getTokenType() == TokenType.UNKNOWN)
                unknown.append(iterator.next().getText());

            return new Token(unknown.toString(), TokenType.UNKNOWN);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
